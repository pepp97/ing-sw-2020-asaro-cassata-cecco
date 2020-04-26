package it.polimi.ingsw.model;

import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;

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
    public void use(Game game) {

       // ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",availableSquare);
       // game.notifyObservers(chooseTarget);

        int i=0;

        while(game.getTargetSelected()==null)
            i++;


        Worker worker = (Worker) game.getTargetInUse();
        game.getController().setCanSkip(true);

        if(game.getTargetSelected().getSquare().getWorker()!=null) {
            game.setTargetSelected(game.getTargetSelected().getSquare().getWorker());
            game.getController().setCanSkip(false);
        }

        if (worker.getCanBeMoved()) {
        if(availableSquare.contains(game.getTargetSelected().getSquare())) {
            game.getTargetInUse().getSquare().removeWorker();
            game.getCurrentPlayer().setHasBeenMoved(true);
            game.getTargetSelected().getSquare().setWorker((Worker) game.getTargetInUse());
            availableSquare.clear();
        }

        else new ExceptionEvent( "target not available");}
        else new ExceptionEvent("you can't move");
        //worker.setActualPos(target.getSquare());
        // worker.getHistoryPos().add(target.getSquare());


        //creazione evento+gestione skippable
    }

    //only for a test purpose
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
            if(s.getLevel() < 4 &&(s.getWorker()==null || s.getWorker().getC()!=worker.getC())&& ((worker.getCanMoveUp()&& worker.getSquare().getLevel()==s.getLevel()-1) || (worker.getSquare().getLevel()>s.getLevel()-1))) {
                availableSquare.add(s);
                result = true;

            }
        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());

        if (availableSquare.size() == 0){
            game.getCurrentPlayer().setDefeat(true);
            result = false;
        }
        worker.setCanBeMoved(result);
        return result;
    }
}