package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

/**
 * Is the command that send to the model the request of disconnection from the server
 */
public class Disconnection implements Command {

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
