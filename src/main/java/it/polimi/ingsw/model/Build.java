package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to build a new Level
 *
 * @author Salvatore Cassata
 */

public class Build implements SubAction {

    public List<Square> getAvailableSquare() {
        return availableSquare;
    }

    private boolean interationNeeded = true;


    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    private List<Square> availableSquare = new ArrayList<>();


    /**
     * This method is called when a player say to build
     *
     * @param game
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
                    game.getCurrentPlayer().getGod().getCantDo().clear();
                    availableSquare.clear();
                    if(game.getCurrentPlayer().getGod().getName().equals("Atlas") && !game.getController().isCanSkip())
                        game.getTargetSelected().getSquare().setLevel(4);

                    UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
                    game.notifyObservers(event);
                } else
                    new ExceptionEvent("you can't build here!");
            } else new ExceptionEvent("you can't build");
            //generazione pacchetto->creazione evento

        } else {
            if(worker.getMandatorySquare().getLevel() < 4) {
                worker.getMandatorySquare().upgrade();
                UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
                game.notifyObservers(event);
                worker.setMandatorySquare(null);
            }
            else{
               game.notifyCurrent(new ExceptionEvent("you can't build another time in this square"));
            }


        }
        game.setTargetSelected(null);
        worker.setSquareNotAvailable(null);

    }

    /**
     * this method control if the workers choosed can build
     *
     * @param game
     * @return if true the worker can build
     */

    @Override
    public boolean isUsable(Game game) {


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



        }
        else {
            if(worker.getSquare().getAdjacentSquares().contains(worker.getMandatorySquare())&&worker.getMandatorySquare().getLevel()!=4 && !(cantDo.contains(worker.getMandatorySquare().getLevel() + 1))){
                result=true;
                availableSquare.add(worker.getMandatorySquare());
            }
            else game.notifyCurrent(new ExceptionEvent("you can't use the effect!"));
        }

        if (availableSquare.size() == 0) {
            result = false;
            result=game.getController().tryToEscape();
        }


        worker.setCanBuild(result);
        game.getController().setGoOn(true);
        return result;
    }


}
