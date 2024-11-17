package org.bossadapt.ant_colony_sim.model;

import lombok.Builder;

@Builder
public class SimulationResponse {
    boolean queenAlive;
    int turnCount;
    LocationResponse[][] locations;
}
