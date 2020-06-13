package it.polimi.ingsw.events;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

/**
 * is the event sends to the players when there is a new player login successful
 */

public class LoginSuccessful implements Event {

    private List<String> nickname;

    /**
     * default constructor
     * @param nickname is the List of player that is logged in the match
     */

    public LoginSuccessful(List<String> nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the List of player that is logged in the match
     */

    public List<String> getNickname() {
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
