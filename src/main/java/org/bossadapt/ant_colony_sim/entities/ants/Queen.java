package org.bossadapt.ant_colony_sim.entities.ants;

import org.bossadapt.ant_colony_sim.entities.Colony;

public class Queen extends Ant{
    //10 turns a day for 20 years == 703050
    Queen(Colony colony){        
        super(0, colony, Colony.ENTERANCE_X, Colony.ENTERANCE_Y, 73050);
    }
    @Override
    public boolean passTurn() {
        this.turnsUntilDeath -=1;
        if(this.turnsUntilDeath <= 0){
            return false;
        }
        if(turnsUntilDeath %10 == 0){
            hatchFriendlyAnt();
        }
        return eat();
    }
    private boolean eat(){
        return this.colony.getGrid()[Colony.ENTERANCE_Y][Colony.ENTERANCE_X].attemptToTakeFood();
    }
    private void hatchFriendlyAnt(){
        int antChoice = Colony.rand.nextInt(4);
        Ant newAnt;
        switch(antChoice){
            case 0:
                newAnt = new Scout(this.colony.incrementAndGetAntId(),this.colony);
                break;
            case 1:
                newAnt = new Soldier(this.colony.incrementAndGetAntId(),this.colony);
                break;
            default:
                newAnt = new Forager(this.colony.incrementAndGetAntId(),this.colony);
                break;

        }
        this.colony.addFriendlyAnt(newAnt);
    }
}
