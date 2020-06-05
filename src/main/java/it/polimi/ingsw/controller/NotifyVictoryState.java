package it.polimi.ingsw.controller;

import it.polimi.ingsw.events.DeathPlayer;
import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.model.Player;

public class NotifyVictoryState implements TurnState {


    @Override
    public void executeState(Controller controller) {
        //evento
        System.out.println(controller.getGame().getWinner().getUsername()+" ,COMPLIMENTI HAI VINTO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Player p = controller.getGame().getCurrentPlayer();
        EndGame endGame= new EndGame(controller.getGame().getWinner().getUsername());
        for(int i=0; i<controller.getGame().getPlayerList().size();i++){
            if(p== controller.getGame().getWinner()){
                controller.getGame().notifyCurrent(endGame);
                controller.getGame().setCurrentPlayer(controller.getNextPlayer(p));
                p=controller.getGame().getCurrentPlayer();
            }else{
                DeathPlayer death = new DeathPlayer(p.getUsername());
                controller.getGame().notifyCurrent(death);
                ExceptionEvent e = new ExceptionEvent("Player " + controller.getGame().getWinner().getUsername() + " has win...");
                controller.getGame().notifyCurrent(e);
                controller.getGame().setCurrentPlayer(controller.getNextPlayer(p));
                p=controller.getGame().getCurrentPlayer();
            }
        }
        controller.getGame().killtimer();
        controller.getGame().endGame();
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
