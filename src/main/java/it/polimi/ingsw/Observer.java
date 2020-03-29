package it.polimi.ingsw;

import it.polimi.ingsw.events.Event;

public interface Observer {
    void update(Event event);
}
