package org.bossadapt.ant_colony_sim.entities;

import org.bossadapt.ant_colony_sim.model.SimulationResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("session")
public class Simulation {
    Colony currentColony;
    Simulation(){
        normalSetup();
    }
    public SimulationResponse normalSetup() {
        currentColony = new Colony(true,1,50,10,4,0);
        return currentColony.generateSimulationResponse();
    }
    public SimulationResponse queenTest() {
        currentColony = new Colony(true,0,0,0,0,0);
        return currentColony.generateSimulationResponse();
    }
    public SimulationResponse scoutTest() {
        currentColony = new Colony(false,0,0,0,1,0);
        return currentColony.generateSimulationResponse();
    }
    public SimulationResponse foragerTest() {
        currentColony = new Colony(false,5,1,0,0,0);
        return currentColony.generateSimulationResponse();
    }
    public SimulationResponse soldierTest() {
        currentColony = new Colony(false,5,0,1,0,1);
        return currentColony.generateSimulationResponse(); 
    }
    public SimulationResponse step() {
        this.currentColony.passTurn();
        return this.currentColony.generateSimulationResponse();
    }
    
}
