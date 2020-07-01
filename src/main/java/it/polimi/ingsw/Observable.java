package it.polimi.ingsw;

import it.polimi.ingsw.events.Event;

/**
 * This interface is used to implement the Observable pattern in the context of a distributed MVC.
 */
public interface Observable {
    /**
     * This metod is used to send notifications to the observers
     * @param event is the event sent to the user
     */
    void notifyObservers(Event event);

    /**
     * This method aims to add an observer to the observable
     * @param observer is the observer added
     */
    void register(Observer observer);

    /**
     * This method remove from the observers list the object in input
     * @param observer to remove.
     */
    void unregister(Observer observer);
}
