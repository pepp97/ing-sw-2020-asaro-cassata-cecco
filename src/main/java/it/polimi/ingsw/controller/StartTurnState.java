package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.EffectRoutine;
import it.polimi.ingsw.model.SubAction;
import it.polimi.ingsw.model.Worker;

import java.util.ArrayList;

public class StartTurnState implements TurnState {


    @Override
    public void executeState(Controller controller) {
        controller.getGame().setCurrentPlayer(controller.getNextPlayer(controller.getGame().getCurrentPlayer()));
        controller.setCanSkip(false);
        controller.getGame().getCurrentPlayer().setHasBeenMoved(false);
        controller.getGame().getCurrentPlayer().setHasBuilt(false);
        controller.getGame().getCurrentPlayer().setDefeat(false);
        controller.getGame().getCurrentPlayer().setInQue(false);

       for(Worker w: controller.getGame().getCurrentPlayer().getWorkers()){
           w.setSquareNotAvailable(null);
           w.setMandatorySquare(null);
           w.resetHystoricPos();
           w.setCanBeMoved(true);
           w.setCanBuild(true);
       }

       controller.getGame().getCurrentPlayer().getGod().setCantDo(new ArrayList<>());



    }




}
