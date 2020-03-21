package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that move the player
 *  @author  Salvatore Cassata
 */

public class Move implements SubAction {

    private List<Square> availableSquare = new ArrayList<>();

    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

        if (worker.isCanBeMoved()){
            worker.getSquare().removeWorker();
            worker.setActualPos(target.getSquare());

        }

    }

    /**
     * @param worker
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Worker worker, Game game) {

        Boolean result = false;


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getWorker() == null && (worker.isCanMoveUp())) {
                availableSquare.add(s);
                result = true;
            }

        worker.setCanBeMoved(result);

        return result;
    }
}
