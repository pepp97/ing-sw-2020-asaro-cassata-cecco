package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;

public class DefeatState implements TurnState {

    @Override
    public void executeState(Controller controller) {

        Player p = controller.getGame().getCurrentPlayer();

        if (controller.getGame().getPlayerList().size() == 2) {
            for (Player winner : controller.getGame().getPlayerList()) {
                if (!winner.equals(p)) {
                    DeathPlayer event = new DeathPlayer(p.getUsername());
                    controller.getGame().notifyCurrent(event);
                    controller.getGame().setWinner(winner);
                    ExceptionEvent e = new ExceptionEvent("Player " + controller.getGame().getWinner().getUsername() + " has win...");
                    controller.getGame().notifyCurrent(e);
                    controller.getGame().removePlayerInList(p);
                    notifyVictory(controller);
                    /*TurnState state = new NotifyVictoryState();
                    controller.getGame().getController().setState(state);
                    state.executeState(controller.getGame().getController());
                    */
                    return;
                }
            }
        }
        DeathPlayer death = new DeathPlayer(p.getUsername());
        controller.getGame().notifyCurrent(death);

        ExceptionEvent e = new ExceptionEvent("Player " + controller.getGame().getCurrentPlayer().getUsername() + " has lost...");
        controller.getGame().notifyObservers(e);


        for (int i = controller.getGame().getPlayerList().size(); i > 1; i--)
            controller.getGame().setCurrentPlayer(controller.getNextPlayer(controller.getGame().getCurrentPlayer()));
        controller.getGame().removePlayerInList(p);
        controller.deletePlayer(p);

        SquareToJson[][] map = new SquareToJson[5][5];
        Square[][] mappa = controller.getGame().getField().getSquares();


        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (mappa[i][j].getWorker() != null)
                    map[i][j] = new SquareToJson(mappa[i][j].getLevel(), mappa[i][j].getWorker().getC().toString(), i, j);
                else map[i][j] = new SquareToJson(mappa[i][j].getLevel(), "", i, j);


        UpdateEvent event = new UpdateEvent(map);
        controller.getGame().notifyObservers(event);


    }

    private void notifyVictory(Controller controller){
        EndGame endGame= new EndGame(controller.getGame().getWinner().getUsername());
        controller.getGame().notifyObservers(endGame);
        ExceptionEvent e = new ExceptionEvent("Player " + controller.getGame().getCurrentPlayer().getUsername() + " has lost...");
        controller.getGame().notifyObservers(e);
        return;
    }

    @Override
    public void goBack() {

    }

    @Override
    public Boolean tryToEscape() {
        return false;
    }

}
