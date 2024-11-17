package org.bossadapt.ant_colony_sim.entities.ants.enemy;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.entities.ants.Ant;
import org.bossadapt.ant_colony_sim.model.Cord;

public abstract class EnemyAnt extends Ant{
    protected EnemyAnt(int id,Colony colony){
        super(id, colony);
         //correcting the position to be of the outer boundary
         this.position = new Cord(0, 0);
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
    protected void move(Cord newCord) {
        Cord oldPosition = this.getPosition();
        this.colony.grid[oldPosition.y][oldPosition.x].removeEnemyAnt(this);
        this.setPosition(newCord);
        this.colony.grid[newCord.y][newCord.x].addEnemyAnt(this);
    }
    @Override
    public void kill() {
        Cord oldPosition = this.getPosition();
        this.colony.grid[oldPosition.y][oldPosition.x].removeEnemyAnt(this);
        this.colony.removeAnt(this);
        
    }
}
