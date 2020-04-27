package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.ParserJson;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Game game;
    private boolean canSkip=false;
    private List<Player> turnManager=new ArrayList<>();
    private TurnState state;
    private boolean goOn;


    public Controller() {
        this.game = new Game(this);
    }

    public void setCanSkip(boolean canSkip) {
        this.canSkip = canSkip;
    }

    public boolean isCanSkip() {
        return canSkip;
    }

    public void setState(TurnState state) {
        this.state = state;
    }

    public Game getGame() {
        return game;
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

        if(turnManager.get(turnManager.size()-1).getWorkers().size()==2) {
            SquareToJson[][]map=new SquareToJson[5][5];
            Square[][]mappa=game.getField().getSquares();

            for(int i=0;i<5;i++)
                for(int j=0; j<5;j++)
                    if(mappa[i][j].getWorker()!=null)
                        map[i][j]=new SquareToJson(mappa[i][j].getLevel(),mappa[i][j].getWorker().getC().toString(),i,j);
                    else map[i][j]=new SquareToJson(mappa[i][j].getLevel(),"",i,j);

            Event e=new UpdateEvent(map);
            game.notifyObservers(e);
            state=new StartTurnState();
            state.executeState(this);
            state=new ExecuteRoutineState();
            state.executeState(this);
        }else if(game.getCurrentPlayer().getWorkers().size()==2)
            game.setCurrentPlayer(getNextPlayer(game.getCurrentPlayer()));

        state.executeState(this);
    }



    //spostare in game?
    public void apply(ChooseYourWorker command) {
        game.setTargetInUse(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getWorker());
        game.getCurrentPlayer().setInQue(false);
        state.executeState(this);
    }

    //spostare in game?
    public void apply(ChooseTarget command) {
        game.setTargetSelected(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getSquare());
        this.setGoOn(true);
        state.executeState(this);
    }

    public void apply(UseEffect command) {
        canSkip=!command.getReply();
        game.getCurrentPlayer().setInQue(false);
        this.setGoOn(false);
        state.executeState(this);

    }


    public void apply(Connection connection,VirtualView view) {
        System.out.println("CI SONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO2");
        ConnectionSuccessful event=new ConnectionSuccessful();
        event.send(view);
    }

    public void apply(StarterCommand starterCommand, VirtualView view) {
        int i=0;



        for(Player player: game.getPlayerList()) {
            if (player.getUsername().equals(starterCommand.getNick())){
                turnManager.add(player);
                break;
            }
            i++;
        }

        for (int j=i+1; j<game.getPlayerList().size();j++)
            turnManager.add(game.getPlayerList().get(j));

        for(int k=0; k<i;k++)
            turnManager.add(game.getPlayerList().get(k));

        for(Player p:turnManager)
            System.out.println(p.getUsername());

        game.setCurrentPlayer(turnManager.get(0));
        game.setCurrentView(view);
        state=new SetWorkerState();
        state.executeState(this);
    }


    public void goBack() {
        state.executeState(this);
    }

    public Player getNextPlayer(Player player) {

        int i=0;
        for (Player p: turnManager){
            if (player.equals(p))
                break;
            i++;
        }

        if(i==turnManager.size()-1)
            i=-1;

        return  turnManager.get(i+1);
    }

    public void deletePlayer(Player currentPlayer) {
        turnManager.remove( currentPlayer);
    }

    public void setGoOn(boolean b) {
        this.goOn=b;
    }

    public boolean isGoOn() {
        return  this.goOn;
    }
}