package it.polimi.ingsw.controller;

import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.ParserJson;
import it.polimi.ingsw.view.VirtualView;

public class Controller {

    private Game game;
    private boolean skippable;
    private ParserJson parser;


    public Controller() {
        this.game = new Game();
        parser=new ParserJson();
    }

    public void apply(LoginCommand command, VirtualView view) {
        game.login(command.getNickname(),command.getColor(),view);
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

    //IMPLEMENTARE
    public void apply(ChooseYourWorker command) {
        //ESTRAE WORKER...
        //game.setTargetInUse(workerEstratto);
    }

    public void apply(ChooseTarget command) {
        //ESTRAE TARGET...
        //game.setTargetSelected(targetestratto);
    }

    public void apply(UseEffect command) {
        skippable=command.getReply();
    }
}
