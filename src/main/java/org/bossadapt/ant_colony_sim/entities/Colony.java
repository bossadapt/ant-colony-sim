package org.bossadapt.ant_colony_sim.entities;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.bossadapt.ant_colony_sim.entities.ants.Ant;
import org.bossadapt.ant_colony_sim.entities.ants.enemy.Bala;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Forager;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Queen;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Scout;
import org.bossadapt.ant_colony_sim.entities.ants.friendly.Soldier;
import org.bossadapt.ant_colony_sim.model.Cord;
import org.bossadapt.ant_colony_sim.model.LocationResponse;
import org.bossadapt.ant_colony_sim.model.SimulationResponse;

public class Colony {
    public static Random rand = new Random();
    public Location[][] grid;
    private HashMap<Integer,Ant> ants;
    //to handle the aftermath of an iterator
    private HashSet<Integer> bufferedDeadAnts;
    private HashMap<Integer,Ant> bufferedBornAnts;
    private int currentAntId;
    private int turnsSinceStart =0;
    private boolean isQueenAlive = true;
    protected boolean queenHatchingActive;
    public static final int QUEEN_ID = 0;
    public static final int ENTERANCE_X = 13;
    public static final int ENTERANCE_Y = 13;
    public Colony(boolean queenHatchingActive,int initialRevealedRadius, int initialForager, int initialSoldier,int initialScout,int initialBala){
        this.queenHatchingActive = queenHatchingActive;
        this.ants = new HashMap<>();
        this.bufferedBornAnts = new HashMap<>();
        this.bufferedDeadAnts = new HashSet<>();

        //build initial enviorment
        this.grid = new Location[27][27];
        for(int y =0; y<27;y++){
            for(int x=0;  x<27;x++){
                boolean revealed = false;
                if(y>=ENTERANCE_Y-initialRevealedRadius && y<=ENTERANCE_Y+initialRevealedRadius){
                    if(x>=ENTERANCE_X-initialRevealedRadius && x<=ENTERANCE_X+initialRevealedRadius){
                        revealed = true;
                    }
                }
                int foodAmount;
                if(x==ENTERANCE_X && y==ENTERANCE_Y){
                    //colony home
                    foodAmount =1000;
                }else{
                    boolean hasFood = rand.nextInt(4) == 0;
                    foodAmount = hasFood ? rand.nextInt(500)+500: 0;
                }
                grid[y][x] = new Location(revealed,foodAmount);
            }
        }
        //spawn all the inital ants
        this.spawnFriendlyAnt(false,new Queen(this,queenHatchingActive));
        for(int i = 0; i< initialForager;i++){
            this.spawnForager(false);
        }
        for(int i = 0; i< initialSoldier;i++){
            this.spawnSoldier(false);
        }
        for(int i = 0; i< initialScout;i++){
            this.spawnScout(false);
        }
        for(int i = 0; i< initialBala;i++){
            this.spawnBala();
        }
    }
    public Location[][] getGrid(){
        return grid;
    }
    public Colony passTurn(){
        if(rand.nextInt(100)<3){
            spawnBala();
        }
        Iterator<Ant>  antIterator = ants.values().iterator();
        while(isQueenAlive && antIterator.hasNext()){
            Ant current = antIterator.next();
            if(!bufferedDeadAnts.contains(current.getId())){
                current.passTurn(); 
            }
        }
        //kill the things out of the iteration cycle
        for(Integer antID : bufferedDeadAnts){
            ants.remove(antID);
        }
        bufferedDeadAnts.clear();
        //spawn the things out of the iteration cycle
        for(Ant ant:bufferedBornAnts.values()){
            this.spawnFriendlyAnt(false, ant);
        }
        bufferedBornAnts.clear();
        if(isQueenAlive){
            for(int y =0; y<grid.length;y++){
                for(int x =0; x<grid.length;x++){
                    grid[y][x].passTurn(turnsSinceStart);
                }
            }
        }
        this.turnsSinceStart +=1;
        return this;
    }
    private int incrementAndGetAntId(){
        this.currentAntId++;
        return currentAntId;
    }
    public void spawnScout(boolean needsBuffer){
        this.spawnFriendlyAnt(needsBuffer,new Scout(incrementAndGetAntId(),this));
    }
    public void spawnSoldier(boolean needsBuffer){
        this.spawnFriendlyAnt(needsBuffer,new Soldier(incrementAndGetAntId(),this));
    }
    public void spawnForager(boolean needsBuffer){
        this.spawnFriendlyAnt(needsBuffer,new Forager(incrementAndGetAntId(),this));
    }
    private void spawnFriendlyAnt(boolean needsBuffer,Ant newAnt){
        //needs buffer is for whether the addition is happening during an iteration cycle in the hashmap
        if(!needsBuffer){
            ants.put(newAnt.getId(),newAnt);

        grid[ENTERANCE_Y][ENTERANCE_X].addFriendlyAnt(newAnt);
        }else{
            bufferedBornAnts.put(newAnt.getId(),newAnt);
        }
    }
    private void spawnBala(){
        Ant newAnt = new Bala(this.incrementAndGetAntId(), this);
        ants.put(newAnt.getId(),newAnt);
        Cord spawnPosition = newAnt.getPosition();
        grid[spawnPosition.y][spawnPosition.x].addEnemyAnt(newAnt);
    }
    public void setQueenIsDead(){
        this.isQueenAlive = false;
    }
    public void removeAnt(Ant ant){
        //cant make changes to the iterator
        this.bufferedDeadAnts.add(ant.getId());
    }
    public SimulationResponse generateSimulationResponse(){
        LocationResponse[][] locations = new LocationResponse[27][27];
        for(int y =0; y< locations.length;y++){
            for(int x =0; x< locations.length;x++){
                locations[y][x] = grid[y][x].generateLocationResponse();
            }   
        } 
        return SimulationResponse.builder().queenAlive(isQueenAlive).turnCount(turnsSinceStart).locations(locations).build();
    }
}
