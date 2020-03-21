package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to change the position of a Player
 *  @author  Salvatore Cassata
 */
public class ChangePosition implements SubAction {

    private List<Square> availableSquare = new ArrayList<>();
    /**
     * this use change the position of the player and save the new position in the HistoryPos of the worker
     * @param worker is the worker to move
     * @param target is the Square where the worker will go
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {
        worker.getSquare().removeWorker();
        target.getSquare().setWorker(worker);
        worker.setActualPos(target.getSquare());
        worker.getHistoryPos().add(target.getSquare());
    }

    /**
     *
     * @param worker
     * @param game
     * @return if true the worker can change position
     */

    @Override
    public Boolean isUsable(Worker worker, Game game) {

        Boolean result = false;


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getWorker() != null) {
                availableSquare.add(s);
                result = true;
                worker.setCanBuild(result);
            }

        return result;
    }
}


