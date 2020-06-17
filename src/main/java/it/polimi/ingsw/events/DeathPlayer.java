package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;
/**
 * This Class is the event sends to the players when a player lose
 */

public class DeathPlayer implements Event {

    private String nickname;

    /**
     * Default constructor
     * @param nickname is the nickname of the player that had lost
     */

    public DeathPlayer(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the nickname of player that had lost
     */

    public String getNickname() {
        return nickname;
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
