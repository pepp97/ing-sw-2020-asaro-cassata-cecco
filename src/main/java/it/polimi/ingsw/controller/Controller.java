package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.model.Game;

import it.polimi.ingsw.model.ParserJson;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Game game;
    private boolean canSkip = false;
    private List<Player> turnManager = new ArrayList<>();
    private TurnState state;
    private boolean goOn = false;

    private Game tmpGame;
    private int tmpIndex;


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

    public synchronized void apply(LoginCommand command, VirtualView view) {
        game.login(command.getNickname(), command.getColor(), view);
    }

    public void apply(ChooseSettings command, VirtualView view) {
        game.resetTimer();
        game.selectNplayer(command.getNplayer(), view);
    }


    public void apply(ChooseGods command) {
        game.resetTimer();
        game.setUsableGod(command.getNamesGod());
    }

    public void apply(ChooseYourGod command, VirtualView view) {
        game.resetTimer();
        game.setPlayerGod(command.getName(), view);
        System.out.println("THREAD NUMERO 1,2");
        System.out.println(view.toString());
    }

    public void apply(ChooseInitialPosition command, VirtualView view) {
        game.resetTimer();
        game.setInitialPosition(command.getCoordinateX(), command.getCoordinateY(), view);
// entro appena tutti hanno selezionato la posizione iniziale
        if (turnManager.get(turnManager.size() - 1).getWorkers().size() == 2) {
            SquareToJson[][] map = new SquareToJson[5][5];
            Square[][] mappa = game.getField().getSquares();

            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    if (mappa[i][j].getWorker() != null)
                        map[i][j] = new SquareToJson(mappa[i][j].getLevel(), mappa[i][j].getWorker().getC().toString(), i, j);
                    else map[i][j] = new SquareToJson(mappa[i][j].getLevel(), "", i, j);

            Event e = new UpdateEvent(map);
            game.notifyObservers(e);
            state = new StartTurnState();
            state.executeState(this);
            state = new ExecuteRoutineState();
            state.executeState(this);
        } else if (game.getCurrentPlayer().getWorkers().size() == 2)
            game.setCurrentPlayer(getNextPlayer(game.getCurrentPlayer()));

        state.executeState(this);
    }


    //arriva l'esito del worker da utilizzare per fare le azioni
    public void apply(ChooseYourWorker command) {
        game.resetTimer();
        game.setTargetInUse(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getWorker());
        game.getCurrentPlayer().setInQue(false);

        state.executeState(this);
    }

    //spostare in game?
    public void apply(ChooseTarget command) {
        saveAll();//
        game.setUndo(true);
        game.resetTimer();
        game.setTargetSelected(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getSquare());
        game.getCurrentPlayer().setInQue(false);
        this.setGoOn(true);

        state.executeState(this);
    }


    public void apply(UseEffect command) {
        game.resetTimer();
        canSkip = !command.getReply();
        game.getCurrentPlayer().setInQue(false);
        //this.setGoOn(false);
        state.executeState(this);


    }


    public synchronized void apply(Disconnection disconnection, VirtualView view) {
        game.unregister(view);
        game.getViews().remove(view);
        try {
            view.closeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (view.getOwner() != null) {
            ExceptionEvent e = new ExceptionEvent("Player: " + view.getOwner().getUsername() + " is disconnected, the match is finished");
            game.notifyObservers(e);
            game.endGame();
        }

    }

    public synchronized void apply(Connection connection, VirtualView view) {
        System.out.println("CI SONOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO2");
        ConnectionSuccessful event = new ConnectionSuccessful();
        event.send(view);
    }

    public void apply(StarterCommand starterCommand, VirtualView view) {
        game.resetTimer();
        int i = 0;


        for (Player player : game.getPlayerList()) {
            if (player.getUsername().equals(starterCommand.getNick())) {
                turnManager.add(player);
                break;
            }
            i++;
        }

        for (int j = i + 1; j < game.getPlayerList().size(); j++)
            turnManager.add(game.getPlayerList().get(j));

        for (int k = 0; k < i; k++)
            turnManager.add(game.getPlayerList().get(k));

        for (Player p : turnManager)
            System.out.println(p.getUsername());

        game.setCurrentPlayer(turnManager.get(0));
        for (View v : game.getViews())
            if (v.getOwner().equals(game.getCurrentPlayer()))
                game.setCurrentView(v);
        state = new SetWorkerState();
        state.executeState(this);
    }



    public void goBack() {
        state.goBack();
    }

    public Player getNextPlayer(Player player) {

        int i = 0;
        for (Player p : turnManager) {
            if (player.equals(p))
                break;
            i++;
        }

        if (i == turnManager.size() - 1)
            i = -1;

        return turnManager.get(i + 1);
    }

    public void deletePlayer(Player currentPlayer) {
        turnManager.remove(currentPlayer);
    }

    public void setGoOn(boolean b) {
        this.goOn = b;
    }

    public boolean isGoOn() {
        return this.goOn;
    }

    public Boolean tryToEscape() {
        boolean result = state.tryToEscape();
        if (result) {
            goBack();
            game.getCurrentPlayer().setInQue(false);
        }
        return result;
    }

    public TurnState getState() {
        return state;
    }

    public List<Player> getTurnManager() {
        return List.copyOf(turnManager);
    }

    public void  apply(Ping ping){

    }

    public void apply(UndoCommand command, VirtualView view){
        if(view.getOwner().equals(game.getCurrentPlayer())&& state instanceof ExecuteRoutineState && game.isUndo()){
            game = tmpGame;
            ((ExecuteRoutineState) state).setI(tmpIndex);
            goOn = false;
            game.getCurrentPlayer().setInQue(false);
            UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
            game.notifyObservers(event);
            state.executeState(this);
        }
        else {
            ExceptionEvent e = new ExceptionEvent("Sorry, you can't use Undo, timeout reached");
            game.notifyObservers(e);
        }
    }

    private void saveAll() {

        tmpGame = this.game;
        ExecuteRoutineState tmpState = (ExecuteRoutineState) state;
        tmpIndex =  tmpState.getI() - 1;

    }


}