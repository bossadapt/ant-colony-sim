package org.bossadapt.ant_colony_sim.controller;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.CustomSetupRequest;
import org.bossadapt.ant_colony_sim.model.SimulationResponse;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpSession;


@EntityScan
@RestController
@SessionScope
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
public class SimulationController {

    SimulationController(){
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/normalSetup")
    public SimulationResponse normalSetup(HttpSession session) {
        Colony newColony = new Colony(true,1,50,10,4,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/queenTest")
    public SimulationResponse queenTest(HttpSession session) {
        Colony newColony = new Colony(true,0,0,0,0,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/scoutTest")
    public SimulationResponse scoutTest(HttpSession session) {
        Colony newColony = new Colony(false,0,0,0,1,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/foragerTest")
    public SimulationResponse foragerTest(HttpSession session) {
        Colony newColony = new Colony(false,5,1,0,0,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/soldierTest")
    public SimulationResponse soldierTest(HttpSession session) {
        Colony newColony =  new Colony(false,5,0,1,0,1);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @PostMapping("/custom")
    public SimulationResponse soldierTest(HttpSession session,@RequestBody CustomSetupRequest custom) {
        Colony newColony =  new Colony(custom.isHatchingActive(),custom.getCustomRadius(),custom.getForagerCount(),custom.getSoldierCount(),custom.getScoutCount(),custom.getBalaCount());
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    //would call it on repeat from front end and call again after displaying the last step
    @CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
    @GetMapping("/step")
    public SimulationResponse step(HttpSession session) {
        Colony currentColony = (Colony) session.getAttribute("currentColony");
        currentColony.passTurn();
        return currentColony.generateSimulationResponse();
    }
    
}
