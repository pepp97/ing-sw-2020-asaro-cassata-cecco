package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

/**
 * is the event sends to the players to start the game
 */

public class StartGameEvent implements Event {

    private List<String> gods;
    private int numPlayers;

    /**
     * Default constructor
     * @param gods is the list of all Gods
     * @param numPlayers is the number of player
     */
    public StartGameEvent(List<String> gods , int numPlayers) {
        this.gods = gods;
        this.numPlayers = numPlayers;
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

    /**
     * @return the list of all gods
     */

    public List<String> getGods() {
        return gods;
    }

    /**
     * @return the num of players
     */

    public int getNumPlayers() {
        return numPlayers;
    }
}
