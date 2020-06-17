package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the command that send to the model the response of the use effect question
 */
public class UseEffect implements Command {

    private boolean reply;

    /**
     * Default constructor
     * @param reply is the answer of the player
     */
    public UseEffect(boolean reply) {
        this.reply = reply;
    }

    /**
     * @return the reply of the player
     */
    public boolean getReply() {
        return reply;
    }

    /**
     * this metod execute the command
     * @param controller the reference controller
     * @param view the personal VirtualView that identify the player that have sent this command
     */

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this);
    }
}
