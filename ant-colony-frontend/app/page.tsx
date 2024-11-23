"use client"
import styles from "./page.module.css";
import { useState } from "react";
import { defaultSimulationResponse, SimulationResponse } from "./interfaces";
import ColonyDisplay from "./ColonyDisplay";
interface CustomSetupRequest{
  hatchingActive:boolean;
  customRadius:number;
  foragerCount:number;
  soldierCount:number;
  scoutCount:number;
  balaCount:number;
}
export default function Home() {
  const [buttonsDisabled, setButtonDisabled] = useState(false);
  const [currentSim, setCurrentSim] = useState(defaultSimulationResponse);
  //customStatupState
  //TODO: bug where queen does not hatch in custom
  const [hatchingActive,setHatchingActive] = useState(true);
  const [customRadius,setCustomRadius] = useState(0);
  const [customForagerCount,setCustomForagerCount] = useState(0);
  const [customSoldierCount,setCustomSoldierCount] = useState(0);
  const [customScoutCount,setCustomScoutCount] = useState(0);
  const [customBalaCount,setCustomBalaCount] = useState(0);
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
  async function callUntilOver(){
    setButtonDisabled(true);
    let queenAliveReactive: boolean|undefined = currentSim.queenAlive;
    while(queenAliveReactive){
      queenAliveReactive = await fetch("http://localhost:8080/step",
        {
        credentials: 'include',
        headers: { 
          "Content-Type": "application/json",
        }}).then(async response => {
        if(response.ok){
          let sim:SimulationResponse = await response.json();
          setCurrentSim(sim);
          return sim.queenAlive;
        }
      });
    }   
    setButtonDisabled(false);
  }
  async function customSetup(){
    let newRequest:CustomSetupRequest = {
      hatchingActive: hatchingActive,
      customRadius: customRadius,
      foragerCount: customForagerCount,
      soldierCount: customSoldierCount,
      scoutCount: customScoutCount,
      balaCount: customBalaCount
    };
    await fetch("http://localhost:8080/custom",
      {
      credentials: 'include',
      headers: { 
        "Content-Type": "application/json",
      },
      method: "POST",
      body:JSON.stringify(newRequest)}).then(async response => {
      if(response.ok){
        let sim:SimulationResponse = await response.json();
        setCurrentSim(sim);
      }
    });
  }
  return (
    <div className= {styles.root}>
      <div className= {styles.header_container_default}>
        <h3 className={styles.header_text}>Default Setup Options:</h3>
        <button onClick={()=>{callApiOnce("normal")}} disabled={buttonsDisabled} className={styles.header_button}>Normal</button>
        <button onClick={()=>{callApiOnce("queen")}} disabled={buttonsDisabled} className={styles.header_button}>Queen</button>
        <button onClick={()=>{callApiOnce("scout")}} disabled={buttonsDisabled} className={styles.header_button}>Scout</button>
        <button onClick={()=>{callApiOnce("forager")}} disabled={buttonsDisabled} className={styles.header_button}>Forager</button>
        <button onClick={()=>{callApiOnce("soldier")}} disabled={buttonsDisabled} className={styles.header_button}>Soldier</button>
        <h3 className={styles.header_text}>Time Advancements:</h3>
        <button onClick={()=>{callApiOnce("step")}} disabled={buttonsDisabled||!currentSim.queenAlive} className={styles.header_button}>Step</button>
        <button onClick={()=>{callUntilOver()}}disabled={buttonsDisabled||!currentSim.queenAlive} className={styles.header_button}>Continuous</button>
        <h3 className={styles.header_text}>Year: {Math.floor(currentSim.turnCount/3650)} Day: {Math.floor((currentSim.turnCount%3650)/10)} Turn:{currentSim.turnCount%10}</h3>
      </div>
      <hr />
      <div className= {styles.header_container_custom} style={{marginTop:"20px"}}>
        <h3 className={styles.header_text}>Custom Setup:</h3>
        <div>
          <h4>hatchingActive</h4>
          <input checked={hatchingActive} onChange={(ev)=>{setHatchingActive((prev)=>{return !prev;})}} type="checkbox"></input>
        </div>
        <div>
          <h4>startingRadius#</h4>
          <input value={customRadius} onChange={(ev)=>{Number(ev.target.value) >= 0 ?setCustomRadius(Number(ev.target.value)):0}} type="number"></input>
        </div>
        <div>
          <h4>forager#</h4>
          <input value={customForagerCount} onChange={(ev)=>{Number(ev.target.value) >= 0 ?setCustomForagerCount(Number(ev.target.value)):0}} type="number"></input>
        </div>
        <div>
          <h4>soldier#</h4>
          <input value={customSoldierCount} onChange={(ev)=>{Number(ev.target.value) >= 0 ?setCustomSoldierCount(Number(ev.target.value)):0}} type="number"></input>
        </div>
        <div>
          <h4>scout#</h4>
          <input value={customScoutCount} onChange={(ev)=>{Number(ev.target.value) >= 0 ?setCustomScoutCount(Number(ev.target.value)):0}} type="number"></input>
        </div>
        <div>
          <h4>bala#</h4>
          <input value={customBalaCount} onChange={(ev)=>{Number(ev.target.value) >= 0 ?setCustomBalaCount(Number(ev.target.value)):0}} type="number"></input>
        </div>
        <button className={styles.header_button} onClick={()=>{customSetup()}}>custom</button>
        </div>
        <hr />
      <div className={styles.body_container}>
        <ColonyDisplay locRs={currentSim.locations}></ColonyDisplay>
      </div>
    </div>
  );
}
