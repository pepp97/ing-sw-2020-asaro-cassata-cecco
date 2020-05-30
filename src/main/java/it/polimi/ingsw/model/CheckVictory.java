package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.NotifyVictoryState;
import it.polimi.ingsw.controller.TurnState;

import java.util.List;

/**
 * It is the micro-effect that have the role to check if the player have win if he is go down by levelToWin level
 *
 * @author Salvatore Cassata
 */


public class CheckVictory implements SubAction {

    private int levelToWin;

    private boolean interationNeeded = false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }

    public CheckVictory(List<Integer> levelToWin) {
        this.levelToWin = levelToWin.get(0);
    }

    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {
        if (isUsable(game))
            for (Player p : game.getPlayerList()) {
                if (p.getWorkers().contains((Worker) game.getTargetInUse())) {
                    game.setWinner(game.getCurrentPlayer());
                    TurnState state = new NotifyVictoryState();
                    game.getController().setState(state);
                    state.executeState(game.getController());
                    break;
                }
            }
        game.getController().setGoOn(false);
    }

    @Override
    public void clearList() {

    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        Worker worker = (Worker) game.getTargetInUse();
        game.getController().setGoOn(true);

        return worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() - worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() >= levelToWin;
    }
}