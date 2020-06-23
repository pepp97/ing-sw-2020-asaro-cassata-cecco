package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.UndoCommand;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to build a new Level
 *
 * @author Salvatore Cassata
 */

public class Build implements SubAction {

    /**
     * it is the List of Square usable by the player to use the effect
     */

    private List<Square> availableSquare = new ArrayList<>();


    public List<Square> getAvailableSquare() {
        return List.copyOf(availableSquare);
    }

    /**
     * it is true if the subaction need interation of the player, false otherwise
     */

    private boolean interationNeeded = true;


    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * this method is called to execute the effect
     * @param game instance of the game
     */
    @Override
    public void use(Game game) {

        Worker worker = (Worker) game.getTargetInUse();
        int i = 0;
        game.getController().setGoOn(false);

        if (worker.getMandatorySquare() == null) {
            if (worker.getCanBuild()) {
                if (availableSquare.contains(game.getTargetSelected())) {
                    game.getTargetSelected().getSquare().upgrade();
                    game.getCurrentPlayer().setHasBuilt(true);
                    if (game.getCurrentPlayer().getGod().getCantDo().size()>0)
                        if (game.getCurrentPlayer().getGod().getCantDo().get(0) == 0 && !game.getController().isCanSkip())
                            game.getTargetSelected().getSquare().setLevel(4);
                    game.getCurrentPlayer().getGod().clearFilter();
                    availableSquare.clear();
                    UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
                    game.notifyObservers(event);
                } else{ game.notifyCurrent(new ExceptionEvent("target not available"));
                    game.getController().apply(new UndoCommand(), (VirtualView) game.getCurrentView());
                }
            } else new ExceptionEvent("you can't build");
            //generazione pacchetto->creazione evento

        } else {
            if (worker.getMandatorySquare().getLevel() < 4) {
                worker.getMandatorySquare().upgrade();
                UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
                game.notifyObservers(event);
                worker.setMandatorySquare(null);
            } else {
                game.notifyCurrent(new ExceptionEvent("you can't build another time in this square"));
            }


        }
        game.setTargetSelected(null);
        worker.setSquareNotAvailable(null);

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

        availableSquare.clear();
        Boolean result = false;
        List<Integer> cantDo = game.getCurrentPlayer().getGod().getCantDo();
        Worker worker = (Worker) game.getTargetInUse();

        if (worker.getMandatorySquare() == null) {
            game.getCurrentPlayer().setInQue(true);
            for (Square s : worker.getSquare().getAdjacentSquares())
                if (s.getWorker() == null && s.getLevel() != 4 && !(cantDo.contains(s.getLevel() + 1)) && s != worker.getSquareNotAvailable()) {
                    availableSquare.add(s);
                    result = true;
                }
            List<SquareToJson> availableSquares = new ArrayList<>();
            for (Square s1 : availableSquare)
                availableSquares.add(new SquareToJson(s1.getLevel(), "", s1.getCoordinateX(), s1.getCoordinateY()));

            SquareToJson[][] map = game.squareToJsonArrayGenerator();
            ChooseTarget chooseTarget = new ChooseTarget("Select your square to upgrade", availableSquares, map);
            UpdateEvent event = new UpdateEvent(map);
            game.notifyObservers(event);
            game.notifyCurrent(chooseTarget);


        } else {
            if (worker.getSquare().getAdjacentSquares().contains(worker.getMandatorySquare()) && worker.getMandatorySquare().getLevel() != 4 && !(cantDo.contains(worker.getMandatorySquare().getLevel() + 1))) {
                result = true;
                availableSquare.add(worker.getMandatorySquare());
            } else game.notifyCurrent(new ExceptionEvent("you can't use the effect!"));
        }

        if (availableSquare.size() == 0) {
            result = false;
            game.getCurrentPlayer().setInQue(false);
            result = game.getController().tryToEscape();
            if (result) {
                game.notifyCurrent(new UpdateEvent(game.squareToJsonArrayGenerator()));
                game.notifyCurrent(new ExceptionEvent("you can't use your effect!"));
            }
        }


        worker.setCanBuild(result);
        game.getController().setGoOn(true);
        return result;
    }


}
