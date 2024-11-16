package org.bossadapt.ant_colony_sim.entities;
import java.util.LinkedList;

import org.bossadapt.ant_colony_sim.entities.ants.Ant;

public class Location {
    private boolean revealed;
    private LinkedList<Ant> friendlyAnts;
    private LinkedList<Ant> enemyAnts;
    private int pheromoneAmount;
    private int foodAmount;
    //used for the home base
    Location(int foodAmount){
        this.revealed = false;
        this.friendlyAnts = new LinkedList<>();
        this.enemyAnts = new LinkedList<>();
        this.pheromoneAmount =0;
        this.foodAmount=foodAmount;
    }
    public void passTurn(int turnsSinceStart){
        if(turnsSinceStart%10==0){
            halfPheromoneAmmount();
        }
    }
    private void halfPheromoneAmmount(){
        if(pheromoneAmount >0){
            this.pheromoneAmount /=2;
        }
    }
    public int getPheromoneAmmount(){
        return pheromoneAmount;
    }
    public void addPheromone(){
        if(this.pheromoneAmount<1000){
            pheromoneAmount +=10;
        }
    }
    //consolidated the checking for food and taking food for both queen and foragers
    public boolean attemptToTakeFood(){
        if(this.foodAmount>0){
            this.foodAmount -=1;
            return true;
        }
        return false;
    }
    //to see if nonscout/ enemy ants can enter the space
    public boolean isRevealed(){
        return this.revealed;
    }
    //for scouts only
    public void setRevealed(){
        this.revealed =true;
    }
    //For soldier and enemy ants
    public boolean containsFriendlyAnts(){
        return !this.friendlyAnts.isEmpty();
    }
    public boolean containsEnemyAnts(){
        return !this.enemyAnts.isEmpty();
    }
    public Ant getRandomFriendlyAnt(){
        if(this.friendlyAnts.isEmpty()){
            return null;
        }
        return this.friendlyAnts.get(Colony.rand.nextInt(friendlyAnts.size()));
    }
    public Ant getRandomEnemyAnt(){
        if(this.friendlyAnts.isEmpty()){
            return null;
        }
        return this.friendlyAnts.get(Colony.rand.nextInt(friendlyAnts.size()));
    }

    //ant management
    public void addFriendlyAnt(Ant newAnt){
        this.friendlyAnts.add(newAnt);
    }
    public void addEnemyAnt(Ant newAnt){
        this.enemyAnts.add(newAnt);
    }

    public void removeFriendlyAnt(Ant ant){
        this.friendlyAnts.remove(ant);
    }
    public void removeEnemyAnt(Ant ant){
        this.enemyAnts.remove(ant);
    }

}
