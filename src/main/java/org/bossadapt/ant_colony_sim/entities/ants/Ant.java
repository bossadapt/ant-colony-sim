package org.bossadapt.ant_colony_sim.entities.ants;


import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;
import org.bossadapt.ant_colony_sim.entities.Location;
public abstract class Ant {
    private int id;
    protected Colony colony;
    protected Cord position;
    protected int turnsUntilDeath;
    private static int[][] movements = {
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, -1},
        {0, 1},
        {1, -1},
        {1, 0},
        {1, 1},
    }; 
    protected Ant(int id,Colony colony){
        this.id = id;
        this.colony = colony;
        this.turnsUntilDeath = 3650;
    }
    public int getId(){
        return id;
    }
    public Cord getPosition(){
        return position;
    }
    protected Location getCurrentLocation(){
        return this.colony.grid[this.position.y][this.position.x];
    }
    public void setPosition(Cord newPosition){
        this.position = newPosition;
    }
    ///Delete from old location, add to the new location, update this.position on ant
    protected abstract void move(Cord newCord);
    ///Removed from both colony list and location list
    public abstract void kill();
    public void passTurn(){
        this.turnsUntilDeath -=1;
        if(this.turnsUntilDeath == 0){
            this.kill();
        }
    };
    //could be further optimized with a bunch of lazy possibilities checking if  its at the limit but would look ugly
    protected ArrayList<Cord> generatePossibleMovementList(){
        ArrayList<Cord> possibleMovementsArrayList=  new ArrayList<>();
        for(int[] movement: movements){
            if(movement[0]+this.position.x>0 && movement[0]+ this.position.x<27){
                if(movement[1]+this.position.y>0 && movement[1]+this.position.y<27){
                    possibleMovementsArrayList.add(new Cord(movement[0]+this.position.x,movement[1]+this.position.y));
                }
            }
        }
        return possibleMovementsArrayList;
    }
}
