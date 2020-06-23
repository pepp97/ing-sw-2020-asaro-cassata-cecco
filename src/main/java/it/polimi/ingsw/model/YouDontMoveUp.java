package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that use this effect
 *  @author  Salvatore Cassata
 */

public class YouDontMoveUp implements SubAction {
    /**
     * it is true if the subaction need interation of the player, false otherwise
     */
    private boolean interationNeeded=false;


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
        for(Worker w: game.getCurrentPlayer().getWorkers())
            w.setCanMoveUp(false);
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
        for(Worker w: game.getCurrentPlayer().getWorkers())
            w.setCanMoveUp(true);
        game.getController().setGoOn(true);
        return true;
    }
}
