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
     * @param game
     */
    @Override
    public void use(Game game) {
        Worker worker = (Worker) game.getTargetInUse();

        if (worker.getCanBeMoved()){
            worker.getSquare().removeWorker();
            //worker.setActualPos(target.getSquare());
            game.getTargetSelected().getSquare().setWorker(worker);
        }

    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {

        Boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getWorker() == null && s.getLevel()<4 &&((worker.getCanMoveUp()&& worker.getSquare().getLevel()==s.getLevel()-1) || (worker.getSquare().getLevel()>s.getLevel()-1))) {
                availableSquare.add(s);
                result = true;
            }

        worker.setCanBeMoved(result);

        return result;
    }
}
