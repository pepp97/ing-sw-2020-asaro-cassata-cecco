package it.polimi.ingsw.model;

import it.polimi.ingsw.events.askUser;

/**
 * represent the subaction that have the role to comunicate with the user
 *
 * @author Salvatore Cassata
 */
public class AskUser implements SubAction {

    private boolean interationNeeded=true;



    public boolean isInterationNeeded() {
        return interationNeeded;
    }



    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {
        game.getController().setGoOn(false);


    }

    /*
     * this method is always true because it don't need to control nothing
     */

    /**
     * @param game instance
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