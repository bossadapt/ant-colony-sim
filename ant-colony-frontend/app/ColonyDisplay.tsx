import { LocationResponse } from "./interfaces";
import styles from "./ColonyDisplay.module.css";
import LocationDisplay from "./LocationDisplay";
interface ColonyDisplayProps{
    locRs:LocationResponse[][]
}
export const ColonyDisplay: React.FC<ColonyDisplayProps> = ({locRs}) => {
    return(
        <table className={styles.ColonyGridTable}>
            <tbody>
            {locRs.map((row,index)=>{
                return(<tr key={index}>{row.map((loc,index2)=>{return <th key={index2}><LocationDisplay locR={loc} pos={[index,index2]}></LocationDisplay></th>})}</tr>)
            })}
            </tbody>
        </table>
    );
}
export default ColonyDisplay;