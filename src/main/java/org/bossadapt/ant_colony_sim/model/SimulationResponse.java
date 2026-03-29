package org.bossadapt.ant_colony_sim.model;

public class SimulationResponse {
    private boolean queenAlive;
    private int turnCount;
    private LocationResponse[][] locations;

    public boolean isQueenAlive() {
        return queenAlive;
    }

    public void setQueenAlive(boolean queenAlive) {
        this.queenAlive = queenAlive;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public LocationResponse[][] getLocations() {
        return locations;
    }

    public void setLocations(LocationResponse[][] locations) {
        this.locations = locations;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SimulationResponse response = new SimulationResponse();

        public Builder queenAlive(boolean queenAlive) {
            response.setQueenAlive(queenAlive);
            return this;
        }

        public Builder turnCount(int turnCount) {
            response.setTurnCount(turnCount);
            return this;
        }

        public Builder locations(LocationResponse[][] locations) {
            response.setLocations(locations);
            return this;
        }

        public SimulationResponse build() {
            return response;
        }
    }
}
