package org.bossadapt.ant_colony_sim.entities.ants;

import org.bossadapt.ant_colony_sim.entities.Colony;

public class Bala extends Ant{
    Bala(int id, Colony colony){    
        super(id,colony, 0, 0, 3650);
        //correcting the position to be of the outer boundary
        int whichSideOfSquareColony = Colony.rand.nextInt(4);
        switch(whichSideOfSquareColony){
            case 0:
                position.x=0;
                position.y=Colony.rand.nextInt(27);
                break;
            case 1:
                position.x=26;
                position.y=Colony.rand.nextInt(27);
                break;
            case 2:
                position.x=Colony.rand.nextInt(27);
                position.y=0;
                break;
            default:
                position.x=Colony.rand.nextInt(27);
                position.y=26;
                break;
        }
    }

    @Override
    public boolean passTurn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'passTurn'");
    }
}
