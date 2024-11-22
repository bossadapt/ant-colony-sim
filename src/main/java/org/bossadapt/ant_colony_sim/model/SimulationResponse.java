package org.bossadapt.ant_colony_sim.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SimulationResponse {
    boolean queenAlive;
    int turnCount;
    LocationResponse[][] locations;
}
