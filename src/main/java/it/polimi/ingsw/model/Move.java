package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.controller.DefeatState;
import it.polimi.ingsw.controller.NotifyVictoryState;
import it.polimi.ingsw.controller.TurnState;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that move the player
 *
 * @author Salvatore Cassata
 */

public class Move implements SubAction {


    private List<Square> availableSquare = new ArrayList<>();
    private boolean interationNeeded = true;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * @param game
     */
    @Override
    public void use(Game game) {

        //ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",List.copyOf(availableSquare));
        //game.notifyCurrent(chooseTarget);

        game.getController().setGoOn(false);
        int i = 0;

        while (game.getTargetSelected() == null)
            i++;

        Worker worker = (Worker) game.getTargetInUse();
        if (worker.getCanBeMoved()) {
            if (availableSquare.contains(game.getTargetSelected())) {


                worker.getSquare().removeWorker();
                //worker.setActualPos(target.getSquare());
                game.getCurrentPlayer().setHasBeenMoved(true);
                game.getTargetSelected().getSquare().setWorker(worker);
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

            } else new ExceptionEvent("target not available");
        } else game.notifyCurrent(new ExceptionEvent("You can't move"));

        //creazione evento + sistemare minotauro
        game.setTargetSelected(null);
        worker.setSquareNotAvailable(null);
    }

    @Override
    public void clearList() {
        availableSquare.clear();
    }


    public List<Square> getAvailableSquare() {
        return List.copyOf(availableSquare);
    }

    /**
     * @param game
     * @return
     */
    @Override
    public boolean isUsable(Game game) {
        availableSquare.clear();

        boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();
        game.getCurrentPlayer().setInQue(true);

        for (Square s : worker.getSquare().getAdjacentSquares())
            if (s.getWorker() == null && s.getLevel() < 4 && ((worker.getCanMoveUp() && worker.getSquare().getLevel() == s.getLevel() - 1) || (worker.getSquare().getLevel() > s.getLevel() - 1))) {
                availableSquare.add(s);
                result = true;
            }

        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());
        if (availableSquare.size() > 0) {
            List<SquareToJson> availableSquares = new ArrayList<>();
            for (Square s1 : availableSquare)
                availableSquares.add(new SquareToJson(s1.getLevel(), "", s1.getCoordinateX(), s1.getCoordinateY()));

            SquareToJson[][] map = game.squareToJsonArrayGenerator();
            ChooseTarget chooseTarget = new ChooseTarget("Where do you want to move?", availableSquares, map);
            UpdateEvent event = new UpdateEvent(map);
            game.notifyObservers(event);
            game.notifyCurrent(chooseTarget);
        } else if (availableSquare.size() == 0 && !game.getCurrentPlayer().isHasBeenMoved() ) {
            game.getCurrentPlayer().setDefeat(true);
            result = false;
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(false);
            return result;
        } else if (availableSquare.size() == 0) {
            result = false;
            game.notifyCurrent(new ExceptionEvent("you can't Use your Effect"));
            game.getCurrentPlayer().setInQue(false);
            ((Worker) game.getTargetInUse()).setSquareNotAvailable(null);
        }
        worker.setCanBeMoved(result);

        game.getController().setGoOn(true);
        return result;
    }
}
