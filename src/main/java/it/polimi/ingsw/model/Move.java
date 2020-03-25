package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that move the player
 *  @author  Salvatore Cassata
 */

public class Move implements SubAction {

    private List<Square> availableSquare = new ArrayList<>();

    public Move() {

    }

    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

        if (worker.getCanBeMoved()){
            worker.getSquare().removeWorker();
            //worker.setActualPos(target.getSquare());
            target.getSquare().setWorker(worker);
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
            if(s.getWorker() == null && (worker.getCanMoveUp()) && worker.getSquare().getLevel()>=s.getLevel()-1) {
                availableSquare.add(s);
                result = true;
            }

        worker.setCanBeMoved(result);

        return result;
    }
}
