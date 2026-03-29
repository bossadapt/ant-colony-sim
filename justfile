set shell := ["bash", "-cu"]

host := "personal"
frontend_dir := "ant-colony-frontend"
frontend_site_root := "/var/www/bossadapt.org/antsim"
frontend_archive_name := "dist.tar.gz"
backend_dir := "/srv/antsim"
backend_service_name := "antsim"
backend_service_file := "deploy/antsim.service"
backend_local_jar := "app.jar"
backend_remote_jar := "app.jar"

default:
    @just --list

build-frontend:
    cd {{frontend_dir}} && npm run build

pack-frontend: build-frontend
    rm -f {{frontend_archive_name}}
    tar -czf {{frontend_archive_name}} -C {{frontend_dir}}/out .

upload-frontend: pack-frontend
    scp {{frontend_archive_name}} {{host}}:/tmp/{{frontend_archive_name}}

build-backend:
    ./mvnw -DskipTests package

pack-backend: build-backend
    jar_path="$(find target -maxdepth 1 -type f -name '*.jar' ! -name '*.jar.original' | head -n 1)"; \
    [[ -n "$jar_path" ]] || { printf 'No built jar found in target/\n' >&2; exit 1; }; \
    install -m 644 "$jar_path" {{backend_local_jar}}

upload-backend: pack-backend
    scp {{backend_local_jar}} {{host}}:/tmp/{{backend_remote_jar}}

ssh:
    ssh {{host}}

deploy-frontend: upload-frontend
    ssh {{host}} "mkdir -p {{frontend_site_root}}"
    ssh {{host}} "find {{frontend_site_root}} -mindepth 1 -maxdepth 1 -exec rm -rf -- {} +"
    ssh {{host}} "tar -xzf /tmp/{{frontend_archive_name}} -C {{frontend_site_root}}"
    ssh {{host}} "rm -f /tmp/{{frontend_archive_name}}"

    rm -f {{frontend_archive_name}}
    printf 'Deployed frontend to %s:%s\n' "{{host}}" "{{frontend_site_root}}"

install-backend-service:
    scp {{backend_service_file}} {{host}}:/tmp/{{backend_service_name}}.service
    ssh {{host}} "sudo install -d -m 755 {{backend_dir}}"
    ssh {{host}} "sudo install -m 644 /tmp/{{backend_service_name}}.service /etc/systemd/system/{{backend_service_name}}.service"
    ssh {{host}} "rm -f /tmp/{{backend_service_name}}.service"
    ssh {{host}} "sudo systemctl daemon-reload"
    ssh {{host}} "sudo systemctl enable {{backend_service_name}}"

    printf 'Installed service %s on %s\n' "{{backend_service_name}}" "{{host}}"

deploy-backend: upload-backend
    ssh {{host}} "sudo install -d -m 755 {{backend_dir}}"
    ssh {{host}} "sudo install -m 644 /tmp/{{backend_remote_jar}} {{backend_dir}}/app.jar"
    ssh {{host}} "rm -f /tmp/{{backend_remote_jar}}"
    ssh {{host}} "sudo systemctl restart {{backend_service_name}}"

    rm -f {{backend_local_jar}}
    printf 'Deployed backend to %s:%s\n' "{{host}}" "{{backend_dir}}"

status-backend:
    ssh {{host}} "sudo systemctl --no-pager --full status {{backend_service_name}}"

logs-backend:
    ssh {{host}} "sudo journalctl -u {{backend_service_name}} -n 100 --no-pager"

deploy-all: deploy-frontend deploy-backend
