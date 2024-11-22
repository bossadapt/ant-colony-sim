export interface SimulationResponse{
    queenAlive:boolean;
    turnCount:number;
    locations:LocationResponse[][];
  }
 export interface LocationResponse{
    //Attributes
    revealed:boolean;
    //Ant Counts
     foragerCount:number;
     scoutCount:number;
     soldierCount:number;
     balaCount:number;
    //Resource Amounts
     foodAmount:number;
     pheromoneAmount:number;
  }
  const defaultLocationResponse:LocationResponse = {
      revealed: false,
      foragerCount: 0,
      scoutCount: 0,
      soldierCount: 0,
      balaCount: 0,
      foodAmount: 0,
      pheromoneAmount: 0
  };
  export const defaultSimulationResponse:SimulationResponse = {
      queenAlive: false,
      turnCount: 0,
      locations: Array<LocationResponse[]>(27).fill(Array<LocationResponse>(27).fill(defaultLocationResponse))
  };