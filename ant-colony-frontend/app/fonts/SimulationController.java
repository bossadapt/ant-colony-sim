package org.bossadapt.ant_colony_sim.controller;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.SimulationResponse;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import jakarta.servlet.http.HttpSession;

 
@CrossOrigin
@EntityScan
@RestController
@SessionScope
public class SimulationController {

    SimulationController(){
    }

    @GetMapping("/normalSetup")
    public SimulationResponse normalSetup(HttpSession session) {
        Colony newColony = new Colony(true,1,50,10,4,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @GetMapping("/queenTest")
    public SimulationResponse queenTest(HttpSession session) {
        Colony newColony = new Colony(true,0,0,0,0,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @GetMapping("/scoutTest")
    public SimulationResponse scoutTest(HttpSession session) {
        Colony newColony = new Colony(false,0,0,0,1,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @GetMapping("/foragerTest")
    public SimulationResponse foragerTest(HttpSession session) {
        Colony newColony = new Colony(false,5,1,0,0,0);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    @GetMapping("/soldierTest")
    public SimulationResponse soldierTest(HttpSession session) {
        Colony newColony =  new Colony(false,5,0,1,0,1);
        session.setAttribute("currentColony", newColony);
        return newColony.generateSimulationResponse();
    }
    //would call it on repeat from front end and call again after displaying the last step
    @GetMapping("/step")
    public SimulationResponse step(HttpSession session) {
        Colony currentColony = (Colony) session.getAttribute("currentColony");
        currentColony.passTurn();
        return currentColony.generateSimulationResponse();
    }
    
}