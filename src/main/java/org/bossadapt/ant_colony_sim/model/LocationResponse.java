package org.bossadapt.ant_colony_sim.model;

public class LocationResponse {
    private boolean revealed;
    private int foragerCount;
    private int scoutCount;
    private int soldierCount;
    private int balaCount;
    private int foodAmount;
    private int pheromoneAmount;

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public int getForagerCount() {
        return foragerCount;
    }

    public void setForagerCount(int foragerCount) {
        this.foragerCount = foragerCount;
    }

    public int getScoutCount() {
        return scoutCount;
    }

    public void setScoutCount(int scoutCount) {
        this.scoutCount = scoutCount;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }

    public int getBalaCount() {
        return balaCount;
    }

    public void setBalaCount(int balaCount) {
        this.balaCount = balaCount;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getPheromoneAmount() {
        return pheromoneAmount;
    }

    public void setPheromoneAmount(int pheromoneAmount) {
        this.pheromoneAmount = pheromoneAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final LocationResponse response = new LocationResponse();

        public Builder revealed(boolean revealed) {
            response.setRevealed(revealed);
            return this;
        }

        public Builder foragerCount(int foragerCount) {
            response.setForagerCount(foragerCount);
            return this;
        }

        public Builder scoutCount(int scoutCount) {
            response.setScoutCount(scoutCount);
            return this;
        }

        public Builder soldierCount(int soldierCount) {
            response.setSoldierCount(soldierCount);
            return this;
        }

        public Builder balaCount(int balaCount) {
            response.setBalaCount(balaCount);
            return this;
        }

        public Builder foodAmount(int foodAmount) {
            response.setFoodAmount(foodAmount);
            return this;
        }

        public Builder pheromoneAmount(int pheromoneAmount) {
            response.setPheromoneAmount(pheromoneAmount);
            return this;
        }

        public LocationResponse build() {
            return response;
        }
    }
}
