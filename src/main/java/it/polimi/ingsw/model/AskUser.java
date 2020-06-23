package it.polimi.ingsw.model;

import it.polimi.ingsw.events.askUser;

/**
 * represent the subaction that have the role to comunicate with the user
 *
 * @author Salvatore Cassata
 */
public class AskUser implements SubAction {
    /**
     * it is true if the subaction need interation of the player, false otherwise
     */

    private boolean interationNeeded=true;



    public boolean isInterationNeeded() {
        return interationNeeded;
    }



    /**
     * this method is called to execute the effect
     * @param game instance of the game
     */
    @Override
    public void use(Game game) {
        game.getController().setGoOn(false);
    }

    @Override
    public void clearList() {

    }



    /**
     * this method is called to check if the effect is usable
     * @param game instance of the game
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        game.getController().setGoOn(true);
        askUser askUser = new askUser();
        game.getCurrentPlayer().setInQue(true);
        game.notifyCurrent(askUser);
        return true;
    }
}