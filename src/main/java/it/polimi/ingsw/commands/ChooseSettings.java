package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the command that send to the model the number of player of the game
 */


public class ChooseSettings implements Command{
    private int nplayer;

    /**
     * Default constructor
     * @param nplayer is the number of player of the game selected by the first player
     */

    public ChooseSettings(int nplayer) {
        this.nplayer = nplayer;
    }

    /**
     *
     * @return the number of players of the match
     */
    public int getNplayer() {
        return nplayer;
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
