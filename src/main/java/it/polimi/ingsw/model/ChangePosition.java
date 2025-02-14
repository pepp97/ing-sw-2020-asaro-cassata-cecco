package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.UndoCommand;
import it.polimi.ingsw.controller.DefeatState;
import it.polimi.ingsw.controller.NotifyVictoryState;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to change the position of a Player
 *
 * @author Salvatore Cassata
 */
public class ChangePosition implements SubAction {

    /**
     * it is the List of Square usable by the player to use the effect
     */

    private List<Square> availableSquare = new ArrayList<>();

    /**
     * it is true if the subaction need interation of the player, false otherwise
     */
    private boolean interationNeeded = true;



    public List<Square> getAvailableSquare() {
        return List.copyOf(availableSquare);
    }

    public boolean isInterationNeeded() {
        return interationNeeded;
    }

    /**
     * this method is called to execute the effect
     * @param game instance of the game
     */
    @Override
    public void use(Game game) {

        // ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",availableSquare);
        // game.notifyObservers(chooseTarget);
        game.getController().setGoOn(false);

        Worker worker = (Worker) game.getTargetInUse();
        game.getController().setCanSkip(true);

        if (game.getTargetSelected().getSquare().getWorker() != null && ((Worker) game.getTargetInUse()).getMandatorySquare() == null) {
            game.setTargetSelected(game.getTargetSelected().getSquare().getWorker());
            Worker w1 = (Worker) game.getTargetSelected();
            w1.setMandatorySquare(((Worker) game.getTargetInUse()).getHistoryPos().get(0));
            game.getController().setCanSkip(false);
        }

        if (worker.getMandatorySquare() != null) {
            //game.getTargetInUse().getSquare().removeWorker();
            game.getController().setCanSkip(false);
            game.getCurrentPlayer().setHasBeenMoved(true);
            worker.getMandatorySquare().setWorker((Worker) game.getTargetInUse());
            UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
            game.notifyObservers(event);
            // worker.setMandatorySquare(null);
            return;
        }

        if (worker.getCanBeMoved()) {
            if (availableSquare.contains(game.getTargetSelected().getSquare())) {
                game.getTargetInUse().getSquare().removeWorker();
                game.getCurrentPlayer().setHasBeenMoved(true);
                game.getTargetSelected().getSquare().setWorker((Worker) game.getTargetInUse());
                availableSquare.clear();
                UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
                game.notifyObservers(event);

                if (worker.getSquare().getLevel() == 3 && worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() < 3) {
                    for (Worker w : game.getCurrentPlayer().getWorkers())
                        if (w.equals(worker)) {
                            game.setWinner(game.getCurrentPlayer());
                            TurnState state = new NotifyVictoryState();
                            game.getController().setState(state);
                            state.executeState(game.getController());
                            break;
                        }
                }
                // availableSquare.add((worker.getHistoryPos().get(0));
            } else { game.notifyCurrent(new ExceptionEvent("target not available"));
                game.getController().apply(new UndoCommand(), (VirtualView) game.getCurrentView());
            }
        } else new ExceptionEvent("you can't move");
        //worker.setActualPos(target.getSquare());
        // worker.getHistoryPos().add(target.getSquare());

        //creazione evento+gestione skippable
        //game.getCurrentPlayer().setInQue(false);
    }

    @Override
    public void clearList() {
        availableSquare.clear();
    }


    /**
     * this method is called to check if the effect is usable
     * @param game instance of the game
     * @return a boolean to determine if the effect is usable
     */

    @Override
    public boolean isUsable(Game game) {
//        game.getCurrentPlayer().setInQue(true);
        availableSquare.clear();
        Boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();
        if (game.getTargetSelected() != null) {
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(true);
            return true;
        }
        // if(game.getTargetSelected().getSquare().getWorker()==null) {
        for (Square s : worker.getSquare().getAdjacentSquares()) {
            if (s.getLevel() < 4 && (s.getWorker() == null || s.getWorker().getC() != worker.getC()) && ((worker.getCanMoveUp() && worker.getSquare().getLevel() == s.getLevel() - 1) || (worker.getSquare().getLevel() > s.getLevel() - 1)))
                availableSquare.add(s);
        }
        result = true;
        List<SquareToJson> availableSquares = new ArrayList<>();
        for (Square s1 : availableSquare)
            availableSquares.add(new SquareToJson(s1.getLevel(), "", s1.getCoordinateX(), s1.getCoordinateY()));

        SquareToJson[][] map = game.squareToJsonArrayGenerator();

        ChooseTarget chooseTarget = new ChooseTarget("Where do you want to move?", availableSquares, map);
        UpdateEvent event = new UpdateEvent(map);
        game.notifyObservers(event);
        game.notifyCurrent(chooseTarget);

        //}
        /*else{
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(true);
            return true;
        }*/
        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());

        if (availableSquare.size() == 0) {
            game.getCurrentPlayer().setDefeat(true);
            result = false;
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(false);
            return result;
        }
        worker.setCanBeMoved(result);
        game.getCurrentPlayer().setInQue(true);
        game.getController().setGoOn(true);
        return result;
    }
}