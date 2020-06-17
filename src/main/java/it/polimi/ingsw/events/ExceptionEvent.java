package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the event sends to the player when is catch an exception
 */

public class ExceptionEvent implements Event {
    private String exception;

    /**
     * Default constructor
     * @param exception is the message of exception
     */

    public ExceptionEvent(String exception) {
        this.exception = exception;
    }

    /**
     * @return the message of exception
     */
    public String getException() {
        return exception;
    }

    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */

    @Override
    public void send(Gui view) {
        view.update(this);
    }

    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }
}
