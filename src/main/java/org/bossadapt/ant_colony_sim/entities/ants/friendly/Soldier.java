package org.bossadapt.ant_colony_sim.entities.ants.friendly;

import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

public class Soldier extends FriendlyAnt{
    public Soldier(int id,Colony colony){        
        super(id,colony);
    }
    @Override
    public void passTurn() {
        //attempt to attack enemies in the square
        if(this.getCurrentLocation().containsEnemyAnts()){
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
            this.getCurrentLocation().getRandomEnemyAnt().kill();
        }
    }
    private void scout(){
        ArrayList<Cord> possibleMovements = this.generatePossibleRevealedMovementList();
        if(!possibleMovements.isEmpty()){
            //attempt to move into a space with a bala
            for(Cord possibleMovement :possibleMovements){
                if(this.getCurrentLocation().containsEnemyAnts()){
                    this.move(possibleMovement);
                    return;
                }
            }
            //just move randomly
            this.move(possibleMovements.get(Colony.rand.nextInt(possibleMovements.size())));
         }

    }
}
