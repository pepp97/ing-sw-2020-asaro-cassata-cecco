package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the command that send to the model the god choose by the player
 */

public class ChooseYourGod implements Command {

    private String name;

    /**
     * Default constructor
     * @param name is the name of the god selected by the player to use during the match
     */

    public ChooseYourGod(String name) {
        this.name = name;
    }

    /**
     *
     * @return the name of God selected
     */
    public String getName() {
        return name;
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
