package org.bossadapt.ant_colony_sim.entities.ants.friendly;

import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.entities.ants.Ant;
import org.bossadapt.ant_colony_sim.model.Cord;

public abstract class FriendlyAnt extends Ant{
    protected FriendlyAnt(int id,Colony colony){
        super(id, colony);
        this.position = new Cord(Colony.ENTERANCE_X, Colony.ENTERANCE_Y);
    }
    @Override
    protected void move(Cord newCord) {
        Cord oldPosition = this.getPosition();
        this.colony.grid[oldPosition.y][oldPosition.x].removeFriendlyAnt(this);
        this.setPosition(newCord);
        this.colony.grid[newCord.y][newCord.x].addFriendlyAnt(this);
    }
    @Override
    public void kill() {
        Cord oldPosition = this.getPosition();
        this.colony.grid[oldPosition.y][oldPosition.x].removeFriendlyAnt(this);
        this.colony.removeAnt(this);
        
    }

    protected ArrayList<Cord> generatePossibleRevealedMovementList(){
        ArrayList<Cord> movementsPossible = generatePossibleRevealedMovementList();
        movementsPossible.removeIf(cord -> (!this.colony.grid[cord.y][cord.x].isRevealed()));
        return movementsPossible;
    }
}
