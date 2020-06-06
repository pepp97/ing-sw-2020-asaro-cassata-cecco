package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;

import java.util.ArrayList;

public class StartTurnState implements TurnState {


    @Override
    public void executeState(Controller controller) {
       // controller.getGame().setEndTurn(false);


        controller.setGoOn(true);
        controller.getGame().setTargetSelected(null);
        controller.getGame().setTargetInUse(null);


        controller.getGame().setCurrentPlayer(controller.getNextPlayer(controller.getGame().getCurrentPlayer()));
        controller.saveAll();
        controller.setCanSkip(false);
        Player currentPlayer= controller.getGame().getCurrentPlayer();
        currentPlayer.setHasBeenMoved(false);
        currentPlayer.setHasBuilt(false);
        currentPlayer.setDefeat(false);
        currentPlayer.setInQue(false);

        Square square [][]=controller.getGame().getField().getSquares();

        for(int x=0; x<5;x++)
            for(int y=0; y<5; y++)
                square[x][y].setStart_level(square[x][y].getLevel());


       for(Worker w: currentPlayer.getWorkers()){
           w.setSquareNotAvailable(null);
           w.setMandatorySquare(null);
           w.resetHystoricPos();
           w.setCanBeMoved(true);
           w.setCanBuild(true);
       }

       currentPlayer.getGod().setCantDo(new ArrayList<>());



    }

    @Override
    public void goBack() {

    }

    @Override
    public Boolean tryToEscape() {
        return null;
    }


}
