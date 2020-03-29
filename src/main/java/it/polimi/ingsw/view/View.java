package it.polimi.ingsw.view;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

public interface View extends Observer {
    void update(Event event);
    public Player getOwner();
}
