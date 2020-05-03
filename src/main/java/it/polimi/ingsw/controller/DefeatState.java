package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class DefeatState implements TurnState {

    @Override
    public void executeState(Controller controller) {
        Player p=controller.getGame().getCurrentPlayer();
        for(int i=controller.getGame().getPlayerList().size();i>1;i--)
            controller.getGame().setCurrentPlayer(controller.getNextPlayer(controller.getGame().getCurrentPlayer()));
        controller.getGame().removePlayerInList(p);
        controller.deletePlayer(p);
        TurnState state=new StartTurnState();
        executeState(controller);
        //+eventi
    }

    @Override
    public void goBack() {

    }

}
