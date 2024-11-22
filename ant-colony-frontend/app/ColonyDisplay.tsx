import { LocationResponse, SimulationResponse } from "./interfaces";
import LocationDisplay from "./LocationDisplay";
interface ColonyDisplayProps{
    locRs:LocationResponse[][]
}
export const ColonyDisplay: React.FC<ColonyDisplayProps> = ({locRs}) => {
    return(
        <table>
            <tbody>
            {locRs.map((row)=>{
                return(<tr>{row.map((loc)=>{return <th><LocationDisplay locR={loc}></LocationDisplay></th>})}</tr>)
            })}
            </tbody>
        </table>
    );
}
export default ColonyDisplay;