package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that use this effect
 *  @author  Salvatore Cassata
 */

public class YouDontMoveUp implements SubAction {



    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {

        for(Worker w: game.getCurrentPlayer().getWorkers())
            w.setCanMoveUp(false);
    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public Boolean isUsable( Game game) {
        return true;
    }
}
