package it.polimi.ingsw.controller;

import it.polimi.ingsw.events.DeathPlayer;
import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.model.Player;

/**
 * This class is the State called when a Player win
 */

public class NotifyVictoryState implements TurnState {
    /**
     * this method is used to execute the state
     * @param controller is the main controller
     */

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
    /**
     * this method is used to go back if a player do a lose don't expected
     */

    @Override
    public void goBack() {

    }
    /**
     * this method is used to check some lose condition
     * @return true if there are some condition to lose, false otherwise
     */
    @Override
    public Boolean tryToEscape() {
        return null;
    }

}
