package it.polimi.ingsw.controller;

import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.ParserJson;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Game game;
    private boolean canSkip;
    private List<Player> turnManager=new ArrayList<>();


    public Controller() {
        this.game = new Game();
    }

    public void setTurnManager(List<Player> turnManager) {
        this.turnManager = turnManager;
    }

    public void apply(LoginCommand command, VirtualView view) {
        game.login(command.getNickname(), command.getColor(),view);
    }

    public void apply(ChooseSettings command, VirtualView view){
        game.selectNplayer(command.getNplayer(),view);
    }


    public void apply(ChooseGods command){
        game.setUsableGod(command.getNamesGod());
    }

    public void apply(ChooseYourGod command, VirtualView view) {
        game.setPlayerGod(command.getName(),view);
        System.out.println("THREAD NUMERO 1,2");
        System.out.println(view.toString());
    }

    public void apply(ChooseInitialPosition command, VirtualView view) {
        game.setInitialPosition(command.getCoordinateX(),command.getCoordinateY(),view);
    }

    //spostare in game?
    public void apply(ChooseYourWorker command) {
        game.setTargetInUse(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getWorker());
    }

    //spostare in game?
    public void apply(ChooseTarget command) {

        game.setTargetSelected(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getSquare());
    }

    public void apply(UseEffect command) {
        canSkip=command.getReply();
    }


    public void apply(Connection connection,VirtualView view) {
        System.out.println("CI SONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO2");
        ConnectionSuccessful event=new ConnectionSuccessful();
        event.send(view);
    }

    public void apply(StarterCommand starterCommand, VirtualView view) {
        int i=0;

        for(Player player: game.getPlayerList()) {
            if (player.getUsername() == starterCommand.getNick()){
                turnManager.add(player);
                break;
            }
            i++;
        }

        for (int j=i+1; j<game.getPlayerList().size();j++)
            turnManager.add(game.getPlayerList().get(j));

        for(int k=0; k<i;k++)
            turnManager.add(game.getPlayerList().get(k));
    }
}