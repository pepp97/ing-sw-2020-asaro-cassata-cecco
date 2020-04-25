package it.polimi.ingsw.model;

import it.polimi.ingsw.events.askUser;

/**
 * represent the subaction that have the role to comunicate with the user
 *
 * @author Salvatore Cassata
 */
public class AskUser implements SubAction {

    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {
        askUser askUser = new askUser();
        game.notifyCurrent(askUser);
    }

    /*
     * this method is always true because it don't need to control nothing
     */

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public Boolean isUsable(Game game) {
        return true;
    }
}