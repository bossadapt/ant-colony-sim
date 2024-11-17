package org.bossadapt.ant_colony_sim.entities.ants.enemy;

import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

public class Bala extends EnemyAnt{
    public Bala(int id, Colony colony){    
        super(id,colony);
    }
    @Override
    public void passTurn() {
        //attempt to attack enemies in the square
        if(this.getCurrentLocation().containsFriendlyAnts()){
            attack();
        }else{
            scout();
        }
        super.passTurn();
    }
     private void attack(){
        //gamble if it would hit before wasting resources gambling which ant it would hit
        if(Colony.rand.nextInt(2)==0){
            //it hit, time to gamble who dies(i could just make it kill the oldest one but where is the fun in that)
            this.getCurrentLocation().getRandomFriendlyAnt().kill();
        }
    }
    private void scout(){
        ArrayList<Cord> possibleMovements = this.generatePossibleMovementList();
        this.move(possibleMovements.get(Colony.rand.nextInt(possibleMovements.size())));
    }
}
