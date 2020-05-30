package it.polimi.ingsw.controller;

import it.polimi.ingsw.events.ExceptionEvent;

public class NotifyVictoryState implements TurnState {


    @Override
    public void executeState(Controller controller) {
        //evento
        System.out.println(controller.getGame().getCurrentPlayer().getUsername()+" ,COMPLIMENTI HAI VINTO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ExceptionEvent e=new ExceptionEvent("The Winner is:     " + controller.getGame().getWinner().getUsername()+ "!!!");
        controller.getGame().notifyObservers(e);
        return;
    }

    @Override
    public void goBack() {

    }

    @Override
    public Boolean tryToEscape() {
        return null;
    }

}
