import { LocationResponse } from "./interfaces";

interface LocationDisplayProps{
    locR:LocationResponse
}
export const LocationDisplay: React.FC<LocationDisplayProps> = ({locR}) => {
    let pheromoneCapacityPercent = (1000/locR.pheromoneAmount)
    let r = 191*pheromoneCapacityPercent;
    let g = 64*pheromoneCapacityPercent;
    let b = 191*pheromoneCapacityPercent;
    return(
        <div>
            {locR.revealed ? (
            <div style={{color:`rgb(${r}, ${g}, ${b})`}}>
                <h3>Forger:{locR.foragerCount}</h3>
                <h3>Scout:{locR.scoutCount}</h3>
                <h3>Soldier:{locR.soldierCount}</h3>
                <h3>Bala:{locR.balaCount}</h3>
                <h3>Food:{locR.foodAmount}</h3>
                <h3>Pheromone:{locR.pheromoneAmount}</h3>
            </div>
        ) : (
            <div>
            </div>
        )}
        </div>

    );
}
export default LocationDisplay;