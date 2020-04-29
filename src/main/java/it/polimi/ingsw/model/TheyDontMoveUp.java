package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that don't use this effect
 *
 * @author Salvatore Cassata
 */

public class TheyDontMoveUp implements SubAction {
    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {

        for (Player p : game.getPlayerList())
            if (!(p.equals(game.getCurrentPlayer())))
                for (Worker w : p.getWorkers())
                    w.setCanMoveUp(false);

        game.getCurrentPlayer().setInQue(false);

    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        game.getCurrentPlayer().setInQue(true);
        for(Player p: game.getPlayerList())
            for (Worker w:p.getWorkers())
                w.setCanMoveUp(true);
        Worker worker = (Worker) game.getTargetInUse();
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() - worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() == 1)
            return true;
        return false;


    }
}
