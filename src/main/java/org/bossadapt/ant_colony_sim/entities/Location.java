package org.bossadapt.ant_colony_sim.entities;
import java.util.LinkedList;

import org.bossadapt.ant_colony_sim.entities.ants.Ant;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Forager;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Scout;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Soldier;
import org.bossadapt.ant_colony_sim.model.LocationResponse;

public class Location {
    private boolean revealed;
    private LinkedList<Ant> friendlyAnts;
    private LinkedList<Ant> enemyAnts;
    private int pheromoneAmount;
    private int foodAmount;
    //used for the home base
    Location(boolean revealed, int foodAmount){
        this.revealed = revealed;
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
    public void depositFood(){
        this.foodAmount++;
    }
    //to see if nonscout/ enemy ants can enter the space
    public boolean isRevealed(){
        return this.revealed;
    }
    //for scouts only
    public void setRevealed(){
        if(!revealed)
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
    public LocationResponse generateLocationResponse(){
        //Ant Counts
        int foragerCount =0;
        int scoutCount=0;
        int soldierCount=0;
        for(Ant ant:friendlyAnts){
            if(ant instanceof Forager){
                foragerCount++;
            }else if(ant instanceof Scout){
                scoutCount++;
            }else if(ant instanceof Soldier){
                soldierCount++;
            }
        }
        return LocationResponse.builder()
        .revealed(this.revealed)
        .foragerCount(foragerCount).scoutCount(scoutCount).soldierCount(soldierCount)
        .balaCount(enemyAnts.size())
        .foodAmount(foodAmount).pheromoneAmount(pheromoneAmount).build();
    }
}
