package org.bossadapt.ant_colony_sim.controller;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

 
@CrossOrigin
@RestController
@EntityScan
public class SimulationController {
    SimulationController(){

    }
    @GetMapping("/normalSetup")
    public String normalSetup(@RequestParam String param) {
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
    //there is no need for a run with a websocket,
    //thats overkill devin do not do it
    //TODO: let frontend have the run button call step on repeat or send a list of all of the actions in an array
    @GetMapping("/step")
    public String run(@RequestParam String param) {
        return new String();
    }
    
}
