package org.bossadapt.ant_colony_sim.entities;
import java.util.Random;
import java.util.HashMap;
import org.bossadapt.ant_colony_sim.entities.ants.Ant;
import org.bossadapt.ant_colony_sim.model.Cord;

public class Colony {
    public static Random rand = new Random();
    public Location[][] grid;
    private HashMap<Integer,Ant> ants;
    private int currentAntId;
    private int totalTurnCount =0;
    public static final int ENTERANCE_X = 13;
    public static final int ENTERANCE_Y = 13;
    Colony(Ant[] initialAnts){
        this.grid = new Location[27][27];
        this.ants = new HashMap<>();
        for(int y =0; y<27;y++){
            for(int x=0;  x<27;x++){
                int foodAmount;
                if(x==ENTERANCE_X && y==ENTERANCE_Y){
                    //colony home
                    foodAmount =1000;
                }else{
                    boolean hasFood = rand.nextInt(4) == 0;
                    foodAmount = hasFood ? rand.nextInt(500)+500: 0;
                }
                grid[y][x] = new Location(foodAmount);
            }
        }
        for(Ant ant:initialAnts){
            this.addFriendlyAnt(ant);
        }
        currentAntId = initialAnts.length-1;
    }
    public Location[][] getGrid(){
        return grid;
    }
    public boolean passTurn(){
        this.totalTurnCount +=1;
    }
    public void moveFriendlyAnt(Ant ant,Cord newPosition){
        Cord oldPosition = ant.getPosition();
        this.grid[oldPosition.y][oldPosition.x].removeFriendlyAnt(ant);
        ant.setPosition(newPosition);
        this.grid[newPosition.y][oldPosition.x].addFriendlyAnt(ant);
    }
    public void moveEnemyAnt(Ant ant,Cord newPosition){
        Cord oldPosition = ant.getPosition();
        this.grid[oldPosition.y][oldPosition.x].removeEnemyAnt(ant);
        ant.setPosition(newPosition);
        this.grid[newPosition.y][oldPosition.x].addEnemyAnt(ant);
    }
    public int incrementAndGetAntId(){
        this.currentAntId++;
        return currentAntId;
    }
    public void addFriendlyAnt(Ant newAnt){
        ants.put(newAnt.getId(),newAnt);
        grid[ENTERANCE_Y][ENTERANCE_X].addFriendlyAnt(newAnt);
    }
    private void addEnemyAnt(Ant newAnt){
        ants.put(newAnt.getId(),newAnt);
        grid[13][13].addEnemyAnt(newAnt);
    }
    public void removeFriendlyAnt(Ant ant){
        ants.remove(ant.getId());
        Cord position = ant.getPosition();
        grid[position.y][position.x].removeFriendlyAnt(ant);
    }
    public void removeEnemyAnt(Ant ant){
        ants.remove(ant.getId());
        Cord position = ant.getPosition();
        grid[position.y][position.x].removeFriendlyAnt(ant);
    }

}
