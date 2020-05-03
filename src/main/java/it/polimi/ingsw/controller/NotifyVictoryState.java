package it.polimi.ingsw.controller;

public class NotifyVictoryState implements TurnState {


    @Override
    public void executeState(Controller controller) {
        //evento
        System.out.println(controller.getGame().getCurrentPlayer().getUsername()+" ,COMPLIMENTI HAI VINTO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return;
    }

    @Override
    public void goBack() {

    }

}
