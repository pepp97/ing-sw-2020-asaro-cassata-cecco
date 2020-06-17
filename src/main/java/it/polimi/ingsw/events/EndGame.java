package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the event send to the players when the match finishes
 */

public class EndGame implements Event {

    private String result;

    /**
     * Default constructor
     * @param result is the message of end game that is of win or lose
     */

    public EndGame(String result) {
        this.result = result;
    }

    /**
     * @return the message of end game
     */

    public String getResult() {
        return result;
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
