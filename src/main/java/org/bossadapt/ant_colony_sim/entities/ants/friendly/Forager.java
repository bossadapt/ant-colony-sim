package org.bossadapt.ant_colony_sim.entities.ants.friendly;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

import java.util.ArrayList;
import java.util.Stack;
public class Forager extends FriendlyAnt{
    private boolean hasFood =  false;
    private Stack<Cord> pathUsed; 
    public Forager(int id,Colony colony){        
        super(id,colony);
        this.pathUsed = new Stack<>();
    }
    @Override
    public void passTurn() {
        if(this.hasFood){
            returnToNest();
        }else{
            forage();
        }
        super.passTurn();
    }
    @Override
    public void kill(){
        super.kill();
        if(this.hasFood){
            this.getCurrentLocation().depositFood();
        }
    }
    @Override
    protected void move(Cord newCord) {
        if(!this.hasFood){
            pathUsed.push(this.position);
        }
        super.move(newCord);
    }
    private void returnToNest(){
        this.getCurrentLocation().addPheromone();
        this.move(pathUsed.pop());
        if(pathUsed.isEmpty()){
            depositFood();
            return;
        }
        
    }

    private void forage(){
        ArrayList<Cord> possibleMovements= this.generatePossibleRevealedMovementList();
        //remove the path that it came from
        if(possibleMovements.size()>1){
            possibleMovements.remove(pathUsed.peek());
            int highestPheromone =0;
            ArrayList<Cord> bestMovementOptions = new ArrayList<>();
            for(Cord movement : possibleMovements){
                int currentPheromoneAmmount = this.colony.grid[movement.y][movement.x].getPheromoneAmmount();
                if(currentPheromoneAmmount == highestPheromone){
                    bestMovementOptions.add(movement);
                }else if(currentPheromoneAmmount > highestPheromone){
                    bestMovementOptions.clear();
                    bestMovementOptions.add(movement);
                    highestPheromone = currentPheromoneAmmount;
                }
            }
            this.move(bestMovementOptions.get(Colony.rand.nextInt(bestMovementOptions.size())));
        }else if(possibleMovements.size() ==1){
            this.move(possibleMovements.get(0));
        }
        if(this.position.x != Colony.ENTERANCE_X && this.position.y != Colony.ENTERANCE_Y && this.getCurrentLocation().attemptToTakeFood()){
            this.hasFood =true;
        }
    }

    private void depositFood(){
        this.getCurrentLocation().depositFood();
        this.hasFood = false;
    }
}
