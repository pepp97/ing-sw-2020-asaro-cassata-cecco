package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.EffectRoutine;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.SubAction;
import it.polimi.ingsw.model.Worker;

import java.util.ArrayList;

public class StartTurnState implements TurnState {


    @Override
    public void executeState(Controller controller) {
        controller.setGoOn(true);
        controller.getGame().setCurrentPlayer(controller.getNextPlayer(controller.getGame().getCurrentPlayer()));
        controller.setCanSkip(false);
        controller.getGame().getCurrentPlayer().setHasBeenMoved(false);
        controller.getGame().getCurrentPlayer().setHasBuilt(false);
        controller.getGame().getCurrentPlayer().setDefeat(false);
        controller.getGame().getCurrentPlayer().setInQue(false);

        Square square [][]=controller.getGame().getField().getSquares();

        for(int x=0; x<5;x++)
            for(int y=0; y<5; y++)
                square[x][y].setStart_level(square[x][y].getLevel());


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
