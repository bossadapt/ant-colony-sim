package org.bossadapt.ant_colony_sim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LocationResponse {
    //Attributes
    boolean revealed;
    //Ant Counts
    int foragerCount;
    int scoutCount;
    int soldierCount;
    int balaCount;
    //Resource Amounts
    int foodAmount;
    int pheromoneAmount;
}
