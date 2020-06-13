package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * is the event send to the players to verify if are connect
 */

public class Pong  implements Event{

    public Pong() {
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
