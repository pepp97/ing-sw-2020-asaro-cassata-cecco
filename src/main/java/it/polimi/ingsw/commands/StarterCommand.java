package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
/**
 * Is the command that send to the model the player starter
 */
public class StarterCommand implements Command {
    private String nick;

    /**
     * Default constructor
     * @param nick is the nickname of the starter player choose
     */
    public StarterCommand(String nick) {
        this.nick = nick;
    }

    /**
     *
     * @return the nickname of starter player
     */

    public String getNick() {
        return nick;
    }

    /**
     * this metod execute the command
     * @param controller the reference controller
     * @param view the personal VirtualView that identify the player that have sent this command
     */

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this,view);
    }
}
