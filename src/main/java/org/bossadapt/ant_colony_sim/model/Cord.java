package org.bossadapt.ant_colony_sim.model;

//slightly pissy that tuples do not exist in java
public class Cord {
    public int x;
    public int y;
    public Cord(int x,int y){
        this.x =x;
        this.y =y;
    }
    //May be a bit suboptimal to use instead of 2 sized arrays, but those are terrible for readability
    public static Cord[] generateMovementList(int x, int y){
         Cord[] movements = {
            new Cord(x-1, y-1),
            new Cord(x-1, y),
            new Cord(x-1, y+1),
            new Cord(x,   y-1),
            new Cord(x,   y+1),
            new Cord(x+1, y-1),
            new Cord(x+1, y),
            new Cord(x+1, y+1),
        };
        return movements;
    }
}
