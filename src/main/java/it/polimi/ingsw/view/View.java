package it.polimi.ingsw.view;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

public interface View extends Observer {
    public Player getOwner();
}
