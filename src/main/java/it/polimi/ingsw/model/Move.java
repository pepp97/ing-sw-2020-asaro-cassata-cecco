package it.polimi.ingsw.model;

import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;

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

        ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",List.copyOf(availableSquare));
        game.notifyCurrent(chooseTarget);

        int i=0;

        while(game.getTargetSelected()==null)
            i++;

        Worker worker = (Worker) game.getTargetInUse();
        if (worker.getCanBeMoved()) {
        if(availableSquare.contains(game.getTargetSelected())) {


                worker.getSquare().removeWorker();
                //worker.setActualPos(target.getSquare());
                game.getTargetSelected().getSquare().setWorker(worker);
                availableSquare.clear();
            }
        else new ExceptionEvent("target not available");
        }

        else new ExceptionEvent("You can't move");

        //creazione evento

    }

    //only for testing purpose
    public List<Square> getAvailableSquare() {
        return availableSquare;
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
        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());
        if (availableSquare.size() == 0)
            result = false;

        worker.setCanBeMoved(result);


        return result;
    }
}
