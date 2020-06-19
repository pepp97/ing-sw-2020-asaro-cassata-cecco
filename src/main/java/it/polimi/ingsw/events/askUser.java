package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the event sends to the current player for choose if want use the effect
 */

public class askUser implements Event {

    public askUser() {

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