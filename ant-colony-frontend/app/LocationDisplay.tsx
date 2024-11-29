import { LocationResponse } from "./interfaces";

interface LocationDisplayProps{
    locR:LocationResponse,
    pos:[number,number]
}
export const LocationDisplay: React.FC<LocationDisplayProps> = ({locR,pos}) => {
    const pheromoneCapacityPercent = locR.pheromoneAmount/1000
    const r = Math.floor(191*pheromoneCapacityPercent);
    const g = Math.floor(64*pheromoneCapacityPercent);
    const b = Math.floor(191*pheromoneCapacityPercent);
    const pheromoneAmountRGB = `rgb(${r}, ${g}, ${b})`;
    const homeRGB = "rgb(170, 107, 57)";
    const backgroundColor = pos[0] === 13&&pos[1] === 13 ? homeRGB : pheromoneAmountRGB;
    return(
        <div>
            {locR.revealed ? (
            <div style={{backgroundColor:backgroundColor}}>
                <h3 style={{color: locR.foragerCount>0? "cyan":"white"}}>Forager:{locR.foragerCount}</h3>
                <h3 style={{color: locR.scoutCount>0? "blue":"white"}}>Scout:{locR.scoutCount}</h3>
                <h3 style={{color: locR.soldierCount>0? "rgb(75, 94, 12)":"white"}}>Soldier:{locR.soldierCount}</h3>
                <h3 style={{color: locR.balaCount>0? "red":"white"}}>Bala:{locR.balaCount}</h3>
                <h3 style={{color: locR.foodAmount>0? "green":"white"}}>Food:{locR.foodAmount}</h3>
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