package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that don't use this effect
 *  @author  Salvatore Cassata
 */

public class TheyDontMoveUp implements SubAction {
    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

        for(Player p: game.getPlayerList())
            if (p != game.getCurrentPlayer())
                for(Worker w: p.getWorkers())
                    w.setCanMoveUp(false);

    }

    /**
     * @param worker
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        return true;
    }
}
