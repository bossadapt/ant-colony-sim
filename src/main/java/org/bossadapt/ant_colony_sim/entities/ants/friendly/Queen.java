package org.bossadapt.ant_colony_sim.entities.ants.friendly;

import org.bossadapt.ant_colony_sim.entities.Colony;

public class Queen extends FriendlyAnt{
    private boolean hatchingActive;
    //10 turns a day for 20 years == 703050
    public Queen(Colony colony, boolean hatchingActive){        
        super(Colony.QUEEN_ID, colony);
        this.turnsUntilDeath = 73050;
        this.hatchingActive = hatchingActive;
    }
    @Override
    public void passTurn() {
        if( hatchingActive && turnsUntilDeath %10 == 0){
            hatchFriendlyAnt();
        }
        if(!this.eat()){
            this.kill();
        }
        super.passTurn();
    }
    @Override
    public void kill() {
        super.kill();
        this.colony.setQueenIsDead();
    }
    private boolean eat(){
        return this.colony.getGrid()[Colony.ENTERANCE_Y][Colony.ENTERANCE_X].attemptToTakeFood();
    }
    private void hatchFriendlyAnt(){
        int antChoice = Colony.rand.nextInt(4);
        switch(antChoice){
            case 0:
                this.colony.spawnScout(true);
                break;
            case 1:
                this.colony.spawnSoldier(true);
                break;
            default:
                this.colony.spawnForager(true);
                break;

        }
    }
}
