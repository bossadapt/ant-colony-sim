package org.bossadapt.ant_colony_sim.model;

public class CustomSetupRequest {
    private boolean hatchingActive;
    private int customRadius;
    private int foragerCount;
    private int soldierCount;
    private int scoutCount;
    private int balaCount;

    public boolean isHatchingActive() {
        return hatchingActive;
    }

    public void setHatchingActive(boolean hatchingActive) {
        this.hatchingActive = hatchingActive;
    }

    public int getCustomRadius() {
        return customRadius;
    }

    public void setCustomRadius(int customRadius) {
        this.customRadius = customRadius;
    }

    public int getForagerCount() {
        return foragerCount;
    }

    public void setForagerCount(int foragerCount) {
        this.foragerCount = foragerCount;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }

    public int getScoutCount() {
        return scoutCount;
    }

    public void setScoutCount(int scoutCount) {
        this.scoutCount = scoutCount;
    }

    public int getBalaCount() {
        return balaCount;
    }

    public void setBalaCount(int balaCount) {
        this.balaCount = balaCount;
    }
}
