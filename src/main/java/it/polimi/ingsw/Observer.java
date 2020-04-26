package it.polimi.ingsw;

import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

public interface Observer {
    void update(Event event);
    Player getOwner();
}
