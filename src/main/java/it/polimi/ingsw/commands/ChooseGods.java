package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;


import java.util.ArrayList;
import java.util.List;

/**
 * This Class Is the command that send to the model the gods selected by the first player
 */

public class ChooseGods implements Command {

    private List<String> namesGod;

    /**
     * Default constructor
     * @param namesGod is the List og Gods selected
     */
    public ChooseGods(List<String> namesGod) {
        this.namesGod = namesGod;
    }

    /**
     *
     * @return the list of Gods selected
     */

    public List<String> getNamesGod() {
        return namesGod;
    }

    /**
     * this method execute the command
     * @param controller the reference controller
     * @param view the personal VirtualView that identify the player that have sent this command
     */

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this);
    }
}
