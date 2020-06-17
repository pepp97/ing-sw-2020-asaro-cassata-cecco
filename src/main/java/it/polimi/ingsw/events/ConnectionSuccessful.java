package it.polimi.ingsw.events;


import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;
/**
 * This Class is the event sends to the current player for response that connection has been established
 */

public class ConnectionSuccessful implements Event {

    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(Gui view) {
        view.update( this);
        System.out.println(this);
        System.out.println(this.getClass());
    }
    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(VirtualView view) {
        view.update(this);
        System.out.println(this.getClass());
    }
}
