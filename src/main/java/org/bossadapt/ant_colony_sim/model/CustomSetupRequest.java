package org.bossadapt.ant_colony_sim.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomSetupRequest {
    private boolean hatchingActive;
    private int customRadius;
    private int foragerCount;
    private int soldierCount;
    private int scoutCount;
    private int balaCount;
}
