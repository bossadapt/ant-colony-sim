package org.bossadapt.ant_colony_sim.controller;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

 
@CrossOrigin
@EntityScan
@Controller
@SessionScope
public class SimulationController {

    SimulationController(){

    }
    @GetMapping("/normalSetup")
    public String normalSetup(@RequestParam String param) {
        Colony currentColony = new Colony(true,1,50,10,4,0);
        //https://stackoverflow.com/questions/65711200/how-to-add-object-to-session-in-spring-app
        return new String();
    }
    @GetMapping("/queenTest")
    public String queenTest(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/scoutTest")
    public String scoutTest(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/foragerTest")
    public String foragerTest(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/soldierTest")
    public String soldierTest(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/step")
    public String step(@RequestParam String param) {
        return new String();
    }
    @GetMapping("/continuous")
    public String continuous(@RequestParam String param) {
        return new String();
    }
    
}
