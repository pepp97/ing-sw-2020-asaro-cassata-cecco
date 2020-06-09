package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.commands.ChooseTarget;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.*;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    private Game game;
    private boolean canSkip = false;
    private List<Player> turnManager = new ArrayList<>();
    private TurnState state;
    private boolean goOn = false;
    private boolean undoCheckFlag = false;
    private final static int size = 5;
    private Square[][] map = new Square[size][size];
    private static int maxRetries = 5;
    private int limit = 0;
    private ParserJson p;
    private boolean killTimer = false;


    public Controller() {
        this.game = new Game(this);
        p = new ParserJson();
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


    public synchronized void apply(LoginCommand command, VirtualView view) {
        game.login(command.getNickname(), command.getColor(), view);
    }

    public synchronized void apply(ChooseSettings command, VirtualView view) {
        //game.resetTimer();
        game.selectNplayer(command.getNplayer(), view);
    }


    public synchronized void apply(ChooseGods command) {
        //game.resetTimer();

        game.setUsableGod(command.getNamesGod());
    }

    public synchronized void apply(ChooseYourGod command, VirtualView view) {
        //game.resetTimer();
        game.setPlayerGod(command.getName(), view);
    }

    public TurnState getState() {
        return state;
    }

    public List<Player> getTurnManager() {
        return turnManager;
    }

    public synchronized void apply(ChooseInitialPosition command, VirtualView view) {
        //game.resetTimer();
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
    public synchronized void apply(ChooseYourWorker command) {
        //game.resetTimer();
        killTimer = false;
        game.setTargetInUse(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getWorker());
        game.getCurrentPlayer().setInQue(false);

        state.executeState(this);
    }

    //spostare in game?
    public synchronized void apply(ChooseTarget command) {
        // saveAll();
        game.setUndo(true);
        //game.resetTimer();
        game.setTargetSelected(game.getField().getSquares()[command.getCoordinateX()][command.getCoordinateY()].getSquare());
        game.getCurrentPlayer().setInQue(false);
        this.setGoOn(true);

        state.executeState(this);
    }


    public synchronized void apply(UseEffect command) {
        //   game.resetTimer();

        canSkip = !command.getReply();
        game.getCurrentPlayer().setInQue(false);
        //this.setGoOn(false);
        state.executeState(this);


    }


    public synchronized void apply(Disconnection disconnection, VirtualView view) {
        if (!game.getObservers().contains(view)) {
            apply(new PingDelete(), view);
            return;
        }
        game.setEnd(true);
        game.killtimer();
        game.unregister(view);
        if (view.getOwner() != null) {
            ExceptionEvent e = new ExceptionEvent("Player: " + view.getOwner().getUsername() + " is disconnected, the match is finished");
            game.notifyObservers(e);
            game.endGame();
        }
        try {
            view.closeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public synchronized void apply(Connection connection, VirtualView view) {

        if (game.getToPing() != game.getObservers()) {
            game.getToPing().add(view);
            game.startMytimer();
        }
        ConnectionSuccessful event = new ConnectionSuccessful();
        event.send(view);
    }

    public synchronized void apply(StarterCommand starterCommand, VirtualView view) {
        // game.resetTimer();
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
        for (Observer o : game.getObservers()) {
            View v=(View)o;
            if (v.getOwner().equals(game.getCurrentPlayer()))
                game.setCurrentView(v);
        }
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


    public void apply(Ping ping, VirtualView v) {
        System.out.println("ping...");
        v.setPing(true);
        // game.resetTimer();
    }

    public synchronized void apply(UndoCommand command, VirtualView view) {
        for (Worker w : game.getCurrentPlayer().getWorkers())
            if (undoCheckFlag)
                w.setCanMoveUp(undoCheckFlag);
        if (game.isUndo()) {
            killTimer = true;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    game.getField().getSquares()[i][j].setLevel(map[i][j].getLevel());
                    if (map[i][j].getWorker() != null)
                        game.getField().getSquares()[i][j].setWorker(map[i][j].getWorker());
                    else if (game.getField().getSquares()[i][j].getWorker() != null)
                        game.getField().getSquares()[i][j].removeWorker();
                }
            }

            for (int count = 0; count < game.getPlayerList().size(); count++)
                if (game.getPlayerList().get(count).equals(game.getCurrentPlayer())) {
                    if (count == 0)
                        count = game.getPlayerList().size();
                    game.setCurrentPlayer(game.getPlayerList().get(count - 1));
                    break;
                }
            game.notifyObservers(new UpdateEvent(game.squareToJsonArrayGenerator()));
            state = new StartTurnState();
            state.executeState(this);
            state = new ExecuteRoutineState();
            state.executeState(this);
        } else {
            ExceptionEvent e = new ExceptionEvent("Sorry, you can't use Undo function.");
            game.notifyCurrent(e);
        }
    }

    public void setUndoCheckFlag(boolean undoCheckFlag) {
        this.undoCheckFlag = undoCheckFlag;
    }

    public void saveAll() {
        if (game.getCurrentPlayer().getWorkers().get(0).getCanMoveUp() == true)
            undoCheckFlag = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = new Square(i, j);
                map[i][j].setLevel(game.getField().getSquares()[i][j].getLevel());
                if (game.getField().getSquares()[i][j].getWorker() != null) {
                    map[i][j].setWorker(game.getField().getSquares()[i][j].getWorker());
                    game.getField().getSquares()[i][j].getWorker().setActualPos(game.getField().getSquares()[i][j]);
                }
            }
        }
    }


    public void restart() {
        game = new Game(this);
        canSkip = false;
        turnManager = new ArrayList<>();
        goOn = false;
    }


    public void startTimer() {
        Timer timer = new Timer();

        TimeoutCheckerInterface timeoutChecker = (l) -> {
            System.out.println("UNDOOOOOOOOOOOOOOOOOOOO: " + limit);
            //notifyObservers(new Pong());
            Boolean timeoutReached = limit > maxRetries;
            int i = 0;

            if (killTimer)
                return true;
            if (timeoutReached) {
                for (Worker w : game.getCurrentPlayer().getWorkers())
                    w.setCanMoveUp(true);
                undoCheckFlag = false;
                game.notifyObservers(new UpdateEvent(game.squareToJsonArrayGenerator()));
                limit = 0;
                TurnState state = new StartTurnState();
                i = -1;
                setState(state);
                state.executeState(this);
                ExecuteRoutineState state1 = new ExecuteRoutineState();
                setState(state1);
                state1.executeState(this);
                //resetTimer();
                return true;
            }
            limit++;
            return false;
        };
        TimerTask task = new TimeoutCounter(timeoutChecker);
        int initialDelay = 50;
        int delta = 1000;
        timer.schedule(task, initialDelay, delta);
    }

    public void apply(PingDelete pingDelete, VirtualView view) {
        game.getToPing().remove(view);
        try {
            view.closeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Observer o : game.getToPing()){
            o.update(new Pong());
        }
    }

    public ParserJson getP() {
        return p;
    }
}

