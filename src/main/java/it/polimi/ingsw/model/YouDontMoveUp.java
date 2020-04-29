package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that use this effect
 *  @author  Salvatore Cassata
 */

public class YouDontMoveUp implements SubAction {
    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }



    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {
        game.getController().setGoOn(false);
        for(Worker w: game.getCurrentPlayer().getWorkers())
            w.setCanMoveUp(false);
    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        for(Worker w: game.getCurrentPlayer().getWorkers())
            w.setCanMoveUp(true);
        game.getController().setGoOn(true);
        return true;
    }
}
