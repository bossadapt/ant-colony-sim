package org.bossadapt.ant_colony_sim.entities.ants.friendly;
import java.util.ArrayList;

import org.bossadapt.ant_colony_sim.entities.Colony;
import org.bossadapt.ant_colony_sim.model.Cord;

public class Scout extends FriendlyAnt{

    public Scout(int id,Colony colony){        
        super(id,colony);
    } 
    @Override
    public void passTurn() {
        //taking care of lifetime
        scout();
        super.passTurn();
    }
    private void scout(){
        ArrayList<Cord> usableMovements = generatePossibleMovementList();
        Cord newPostition = usableMovements.get(Colony.rand.nextInt(usableMovements.size()));
        this.move(newPostition);
        this.getCurrentLocation().setRevealed();
    }
    
}
