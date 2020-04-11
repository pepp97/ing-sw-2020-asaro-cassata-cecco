package it.polimi.ingsw.controller;

import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.ParserJson;
import it.polimi.ingsw.view.VirtualView;

public class Controller {

    private Game game;
    private boolean canSkip;
    private ParserJson parser;


    public Controller() {
        this.game = new Game();
        parser=new ParserJson();
    }

    public void apply(LoginCommand command, VirtualView view) {
        game.login(command.getNickname(), command.getColor(),view);
    }

    public void apply(ChooseSettings command, VirtualView view){
        game.selectNplayer(command.getNplayer());
    }


    public void apply(ChooseGods command){
        game.setUsableGod(command.getNamesGod(),parser.getUsableGod());
    }

    public void apply(ChooseYourGod command, VirtualView view) {
        game.setPlayerGod(command.getName(),view);
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
}
