package it.polimi.ingsw;

import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;
/**
 * This interface is used to implement the Observable pattern in the context of a distributed MVC.
 */
public interface Observer {

    /**
     * This method is used to update the observer's state after an update from the observable
     * @param event is the event verified
     */
    void update(Event event);

    /**
     * This method is used to retrive the owner of the observer
     * @return the Player owner
     */
    Player getOwner();

    
}
