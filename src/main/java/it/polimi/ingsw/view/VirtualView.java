package it.polimi.ingsw.view;

import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

public class VirtualView implements View  {

    private Player owner;

    public void setOwner(Player owner) {
        this.owner = owner;
    }




    public void update(Event event) {
        //prepara JSon e manda
    }

    @Override
    public Player getOwner() {
        return owner;
    }


}
