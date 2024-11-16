package org.bossadapt.ant_colony_sim.entities.ants;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

public abstract class Ant {
    private int id;
    protected Colony colony;
    protected Cord position;
    protected int turnsUntilDeath;
    public Ant(int id,Colony colony, int xCord, int yCord, int turnsUntilDeath){
        this.id = id;
        this.colony = colony;
        this.position = new Cord(xCord,yCord);
        this.turnsUntilDeath = turnsUntilDeath;
    }
    public int getId(){
        return id;
    }
    public Cord getPosition(){
        return position;
    }
    public void setPosition(Cord newPosition){
        this.position = newPosition;
    }
    public abstract boolean passTurn();
}
