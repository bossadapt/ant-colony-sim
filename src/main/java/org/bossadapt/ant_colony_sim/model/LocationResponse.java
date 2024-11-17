package org.bossadapt.ant_colony_sim.model;

import lombok.Builder;

@Builder
public class LocationResponse {
    //Ant Counts
    int foragerCount;
    int scoutCount;
    int soldierCount;
    int balaCount;
    //Resource Amounts
    int foodAmount;
    int pheromoneAmount;
}
