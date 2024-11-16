package org.bossadapt.ant_colony_sim.entities.ants;
import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

public class Scout extends Ant{

    Scout(int id,Colony colony){        
        super(id,colony, Colony.ENTERANCE_X, Colony.ENTERANCE_Y, 3650);
    }
    @Override
    public boolean passTurn() {
        scout();
        return true;
    }
    private void scout(){
        Cord[] movements = Cord.generateMovementList(this.position.x, this.position.y);
        ArrayList<Cord> usableMovements = new ArrayList<>();
        for(Cord movementOption: movements){
            if(movementOption.y >= 0 && movementOption.y<27){
                if(movementOption.x >= 0 && movementOption.x<27){
                    usableMovements.add(movementOption);
                }
            }
        }
        Cord newPostition = usableMovements.get(Colony.rand.nextInt(usableMovements.size()));
        this.colony.moveFriendlyAnt(this, newPostition);
        this.colony.grid[newPostition.y][newPostition.x].setRevealed();
    }
    
}
