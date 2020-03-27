package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.TargetNotAvailableException;

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
     * @param game
     */
    @Override
    public void use(Game game) throws TargetNotAvailableException {
        if(availableSquare.contains(game.getTargetSelected())) {
            game.getTargetInUse().getSquare().removeWorker();
            game.getTargetSelected().getSquare().setWorker((Worker) game.getTargetInUse());
            availableSquare.clear();
        }

        else throw new TargetNotAvailableException();
        //worker.setActualPos(target.getSquare());
        // worker.getHistoryPos().add(target.getSquare());
    }

    public List<Square> getAvailableSquare() {
        return availableSquare;
    }

    /**
     *
     * @param game
     * @return if true the worker can change position
     */

    @Override
    public Boolean isUsable(Game game) {

        Boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getLevel() < 4 &&(s.getWorker()==null || s.getWorker().getC()!=worker.getC())&& s.getLevel()-1<=worker.getSquare().getLevel()) {
                availableSquare.add(s);
                result = true;
                worker.setCanBeMoved(result);
            }

        return result;
    }
}


