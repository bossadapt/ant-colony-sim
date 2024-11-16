package org.bossadapt.ant_colony_sim.entities.ants;

import org.bossadapt.ant_colony_sim.entities.Colony;

public class Soldier extends Ant{
    Soldier(int id,Colony colony){        
        super(id,colony, Colony.ENTERANCE_X, Colony.ENTERANCE_Y, 3650);
    }
    @Override
    public boolean passTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'passTurn'");
    }
    
}
