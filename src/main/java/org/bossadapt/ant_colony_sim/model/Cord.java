package org.bossadapt.ant_colony_sim.model;

//slightly pissy that tuples do not exist in java
public class Cord {
    public int x;
    public int y;
    public Cord(int x,int y){
        this.x =x;
        this.y =y;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cord){
            Cord objCord = (Cord)obj;
            return (objCord.x == this.x && objCord.y == this.y);
        }
        return false;
    }
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
