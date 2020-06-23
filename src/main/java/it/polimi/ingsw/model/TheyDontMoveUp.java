package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to set the CanMoveUp in each worker of the player that don't use this effect
 *
 * @author Salvatore Cassata
 */

public class TheyDontMoveUp implements SubAction {
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
        Worker worker = (Worker) game.getTargetInUse();
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() - worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() == 1)
        for (Player p : game.getPlayerList())
            if (!(p.equals(game.getCurrentPlayer())))
                for (Worker w : p.getWorkers())
                    w.setCanMoveUp(false);

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
        for(Player p: game.getPlayerList())
            for (Worker w:p.getWorkers())
                w.setCanMoveUp(true);

            return true;

    }
}
