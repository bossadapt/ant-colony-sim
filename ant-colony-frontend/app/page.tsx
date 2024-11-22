"use client"
import Image from "next/image";
import styles from "./page.module.css";
import { useState } from "react";
import { defaultSimulationResponse, SimulationResponse } from "./interfaces";
import ColonyDisplay from "./ColonyDisplay";
export default function Home() {
  const [buttonsDisabled, setButtonDisabled] = useState(false);
  const [currentSim, setCurrentSim] = useState(defaultSimulationResponse);
  async function callApiOnce(command:string){
    setButtonDisabled(true);
    let url: string = "http://localhost:8080/";
    switch(command){
      case "step":{
        url+= "step";
        break;
      }
      case "normal":{
        url+= "normalSetup";
        break;
      }
      case "queen":{
        url+= "queenTest";
        break;
      }
      case "scout":{
        url+= "scoutTest";
        break;
      }
      case "forager":{
        url+= "foragerTest";
        break;
      }
      default:{
        url+= "soldierTest";
        break;
      }
    }
    await fetch(url,
      {
      credentials: 'include',
      headers: { 
        "Content-Type": "application/json",
      }}).then(async response => {
      if(response.ok){
        let sim:SimulationResponse = await response.json();
        setCurrentSim(sim);
      }
    })
    setButtonDisabled(false);
  }
  return (
    <div>
      <div className= {styles.header_container}>
        <h3 className={styles.header_text}>SETUP OPTIONS:</h3>
        <button onClick={()=>{callApiOnce("normal")}} disabled={buttonsDisabled} className={styles.header_button}>Normal</button>
        <button onClick={()=>{callApiOnce("queen")}} disabled={buttonsDisabled} className={styles.header_button}>Queen</button>
        <button onClick={()=>{callApiOnce("scout")}} disabled={buttonsDisabled} className={styles.header_button}>Scout</button>
        <button onClick={()=>{callApiOnce("forager")}} disabled={buttonsDisabled} className={styles.header_button}>Forager</button>
        <button onClick={()=>{callApiOnce("soldier")}} disabled={buttonsDisabled} className={styles.header_button}>Soldier</button>

        <h3 className={styles.header_text}>Time Advancements:</h3>
        <button onClick={()=>{callApiOnce("step")}} disabled={buttonsDisabled} className={styles.header_button}>Step</button>
        <button disabled={buttonsDisabled} className={styles.header_button}>Continuous</button>
        <h3 className={styles.header_text}>Year: {Math.floor(currentSim.turnCount/3650)} Day: {Math.floor((currentSim.turnCount%3650)/10)} Turn:{currentSim.turnCount%10}</h3>
      </div>
      <ColonyDisplay locRs={currentSim.locations}></ColonyDisplay>
    </div>
  );
}
