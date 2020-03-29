package it.polimi.ingsw;

import it.polimi.ingsw.events.Event;

public interface Observable {
    void notifyObservers(Event event);
    void register(Observer observer);
    void unregister(Observer observer);
}
