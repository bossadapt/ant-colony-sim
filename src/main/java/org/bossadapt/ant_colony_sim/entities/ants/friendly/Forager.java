package org.bossadapt.ant_colony_sim.entities.ants.friendly;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
public class Forager extends FriendlyAnt{
    private boolean hasFood =  false;
    //i kept upping the recent path used to get ideal paths built, so this will likely not be used in most cases but hey, why not
    private Stack<Cord> oldPathUsed; 
    //to save runtime, just keep a short buffer queue that i can iterate through to ensure we are not using
    //(caps at 10 length then pushes into old path)
    //front will be the oldest
    //back will be the newest
    private LinkedList<Cord> recentPathUsed;
    public Forager(int id,Colony colony){        
        super(id,colony);
        this.oldPathUsed = new Stack<>();
        this.recentPathUsed = new LinkedList<Cord>();
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
            //reseting path length after revisiting home base so it does not waste time going past it and drop pheromone on it
            if(newCord.x == Colony.ENTERANCE_X && newCord.y == Colony.ENTERANCE_Y){
                oldPathUsed.clear();
            }else{
                recentPathUsed.add(this.position);
                if(recentPathUsed.size()>10) {
                    oldPathUsed.add(recentPathUsed.pop());
                }
            }
        }
        super.move(newCord);
    }
    private void returnToNest(){
        this.getCurrentLocation().addPheromone();
        if(!recentPathUsed.isEmpty()){
            this.move(recentPathUsed.pollLast());
        }else if(!oldPathUsed.isEmpty()){
            this.move(oldPathUsed.pop());
        }
        if(isAtEnterance()){
            depositFood();
            return;
        }
        
    }
    private boolean isAtEnterance(){
        return this.position.x == Colony.ENTERANCE_X && this.position.y == Colony.ENTERANCE_Y;
    }
    private void forage(){
        ArrayList<Cord> possibleMovements= this.generatePossibleRevealedMovementList();
        //removing the most recent movents from the possibilities as long as there are other options
        //should not be that taxing since the list at all times is <10 and <5 for the recents, so at most 50 checks
        Iterator<Cord> recents = recentPathUsed.iterator();
        while(recents.hasNext()&&possibleMovements.size()>1){
            possibleMovements.remove(recents.next());    
        }
        if(possibleMovements.size()>1){
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
        if(!this.isAtEnterance() && this.getCurrentLocation().attemptToTakeFood()){
            this.hasFood =true;
        }
    }

    private void depositFood(){
        this.getCurrentLocation().depositFood();
        //just to be safe of some weird bug, should not be used and should already be empty
        this.recentPathUsed.clear();
        this.hasFood = false;
    }
}
