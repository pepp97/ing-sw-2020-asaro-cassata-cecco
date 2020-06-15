package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * is the event sends to the first player logged to choose the number of player of the match
 */

public class SettingsEvent implements Event {
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
