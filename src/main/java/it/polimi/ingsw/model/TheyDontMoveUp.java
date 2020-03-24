package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that don't use this effect
 *  @author  Salvatore Cassata
 */

public class TheyDontMoveUp implements SubAction {



    /**
     * @param game
     */
    @Override
    public void use(Game game) {

        for(Player p: game.getPlayerList())
            if (p != game.getCurrentPlayer())
                for(Worker w: p.getWorkers())
                    w.setCanMoveUp(false);

    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {
        Worker worker = (Worker) game.getTargetInUse();
            if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() - worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() == 1)
                return true;
            return false;


    }
}
