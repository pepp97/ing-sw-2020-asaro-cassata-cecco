package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.view.VirtualView;

/**
 * Is the command that send to the model the request of login in the game
 */
public class LoginCommand implements Command{
    private String nickname;
    private Color color;

    /**
     * Default constructor
     * @param nickname is the nickname choose
     * @param color is the color selected
     */

    public LoginCommand(String nickname, Color color) {
        this.nickname = nickname;
        this.color = color;
    }

    /**
     *
     * @return the nickname of the player logged
     */

    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @return the color of player logged
     */

    public Color getColor() {
        return color;
    }

    /**
     * this metod execute the command
     * @param controller the reference controller
     * @param view the personal VirtualView that identify the player that have sent this command
     */

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this, view);
    }
}
