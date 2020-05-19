package it.polimi.ingsw.model;
//DEVE AVERE ISTANZA CONTROLLER CHE HA ATTRIBUTO SKIP.

import it.polimi.ingsw.Observable;
import it.polimi.ingsw.Observer;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;
import it.polimi.ingsw.view.gui.WaitSelectYourGod;

import java.io.IOException;
import java.util.*;

/**
 * This class is the base of the game and is the link between the others class
 */
public class Game implements Observable {

    private List<Player> playerList = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private List<VirtualView> views = new ArrayList<>();
    private Player currentPlayer;
    private View currentView;
    private Target targetSelected;
    private Board board;
    private Target targetInUse;
    private Field field;
    private int numplayer = 0;
    private List<God> startGods;
    private List<God> totalGods;
    private ParserJson p;
    private List<String> effects = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private int turnIndex = 1;
    private List<String> selected = new ArrayList<>();
    private Player winner;
    private Controller controller;
    private int maxRetries = 60;
    private boolean stop = false;
    private static final int length = 5;


    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Game(Controller controller) {
        this.controller = controller;
        field = new Field();
        board = new Board();
        startGods = new ArrayList<>();
    }

    public void add(Player player) {
        if (playerList.size() < numplayer)
            playerList.add(player);
    }

    public View getCurrentView() {
        return currentView;
    }

    public void setCurrentView(View currentView) {
        this.currentView = currentView;
        // currentPlayer=currentView.getOwner();
    }

    public Field getField() {
        return field;
    }

    public Target getTargetInUse() {
        return targetInUse;
    }

    public void setTargetInUse(Target targetInUse) {
        this.targetInUse = targetInUse;
    }

    public List<Player> getPlayerList() {
        return List.copyOf(playerList);
    }

    public void setPlayerInList(Player player) {
        if (playerList.size() < 3)
            this.playerList.add(player);
    }

    public void removePlayerInList(Player player) {
        numplayer--;
        Worker w1 = player.getWorkers().get(0);
        Worker w2 = player.getWorkers().get(1);
        for (int j = 0; j < 5; j++)
            for (int i = 0; i < 5; i++)
                if (field.getSquares()[i][j].getWorker() != null)
                    if (field.getSquares()[i][j].getWorker().equals(w1) || field.getSquares()[i][j].getWorker().equals(w2))
                        field.getSquares()[i][j].removeWorker();

        for(VirtualView v: views)
            if(v.getOwner().equals(player)){
                unregister(v);
                views.remove(v);
            }


        this.playerList.remove(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        for (VirtualView v : views)
            if (v.getOwner().equals(currentPlayer))
                currentView = v;
    }

    public Target getTargetSelected() {
        return targetSelected;
    }

    public void setTargetSelected(Target targetSelected) {
        this.targetSelected = targetSelected;
    }

    protected Board getBoard() {
        return board;
    }

    //public void setBoard(Board board) {
    //  this.board = board;
    //}


    //IMPLEMENTARE
    public synchronized void login(String nickname, Color color, VirtualView view) {
        //  aggiustare numero di giocatori che si possono loggare
        currentView = view;
        if (!nicknameAvailable(nickname)) {
            notifyCurrent(new ExceptionEvent("Username already in use!"));
        } else if (!colorAvailable(color)) {
            notifyCurrent(new ExceptionEvent("Color already in use!"));
        } else {
            /*currentView = view;
            views.add(view);*/
            if (gameAlreadyStarted()) {
                notifyCurrent(new ExceptionEvent("game already started"));

            } else if (playerList.size() == 1 && numplayer == 0) {
                notifyCurrent(new ExceptionEvent("Another player is setting the number of opponents, please wait"));
            } else if (nicknameAvailable(nickname) && colorAvailable(color)) {
                playerLogin(nickname, color, view);
            }
        }
    }

    public boolean gameAlreadyStarted() {
        if (playerList.size() == numplayer && numplayer != 0) {
            return true;
        }
        return false;
    }

    private void playerLogin(String nickname, Color color, VirtualView view) {
        views.add(view);
        observers.add(view);
        Player player = new Player(nickname, color);
        playerList.add(player);
        view.setOwner(player);
        if (playerList.size() == 1) {
            notifyCurrent(new SettingsEvent());
        } else if (playerList.size() == numplayer) {
            checkIfFull();
        } else {
            lastOption(playerList);
        }
    }

    private void lastOption(List<Player> playerList) {
        List<String> list = new ArrayList<>();
        for (Player p : playerList)
            list.add(p.getUsername());
        notifyObservers(new LoginSuccessful(list));
    }

    private void checkIfFull() {
        List<String> godlist = new ArrayList<>();
        p = new ParserJson();
        totalGods = p.getUsableGod();
        for (God g : totalGods) {
            godlist.add(g.getName());
        }
        List<String> list = new ArrayList<>();
        for (Player p1 : playerList)
            list.add(p1.getUsername());
        notifyObservers(new LoginSuccessful(list));
        currentView = (View) observers.get(0);
        notifyCurrent(new StartGameEvent(godlist, numplayer));
        startMytimer();
    }

    private void startMytimer() {

        TimeoutCheckerInterface timeoutChecker = (l) -> {
            System.out.println(l);
            Boolean timeoutReached = l > maxRetries;
            if (timeoutReached) {
                System.out.println("reached limit");
                ExceptionEvent exceptionEvent = new ExceptionEvent("Timeout error, the match is interrupted...");
                notifyObservers(exceptionEvent);
                endGame();

                return true;
            }
            if (stop) {
                stop = false;
                return true;
            }
            System.out.println("timer: " + l);
            return false;
        };

        Timer timer = new Timer();
        TimerTask task = new TimeoutCounter(timeoutChecker);
        int initialDelay = 50;
        int delta = 1000;
        timer.schedule(task, initialDelay, delta);

    }

    public void resetTimer() {
        stop = true;
        startMytimer();
    }

    public boolean nicknameAvailable(String nick) {
        for (Player p : playerList)
            if (nick.equals(p.getUsername()))
                return false;
        return true;
    }

    public boolean colorAvailable(Color color) {
        for (Player p : playerList)
            if (color.equals(p.getColor()))
                return false;
        return true;
    }


    public void selectNplayer(int nplayer, VirtualView view) {
        this.currentView = view;
        this.numplayer = nplayer;
        List<String> list = new ArrayList<>();
        for (Player p : playerList)
            list.add(p.getUsername());
        Event e = new LoginSuccessful(list);
        notifyCurrent(e);
    }

    public List<VirtualView> getViews() {
        return views;
    }

    public void setUsableGod(List<String> god) {
        for (String s : god) {
            for (God totalGod : p.getUsableGod()) {
                if (s.equals(totalGod.getName())) {
                    startGods.add(totalGod);
                    effects.add(totalGod.getTextEffect());
                    names.add(totalGod.getName());
                }
            }
        }
        currentView = (View) observers.get(turnIndex);
        List<String> passGod = new ArrayList<>(names);
        List<String> passEffect = createList(passGod);
        Event e = new ChooseYourGodEvent(passGod, passEffect);
        notifyObservers(new WaitYourGodEvent(passGod, passEffect));
        notifyCurrent(e);
    }

    public synchronized void setPlayerGod(String godname, VirtualView view) {
        System.out.println("il nome è " + godname);
        List<String> passGod = new ArrayList<>(names);
        List<String> passEffect = createList(passGod);
        notifyObservers(new WaitYourGodEvent(passGod, passEffect));
        for (God g : startGods) {
            System.out.println(g.getName());
            if (g.getName().equals(godname)) {
                if (selected.contains(g.getName())) {
                    currentView = view;
                    notifyCurrent(new ExceptionEvent("God already selected"));
                } else {
                    view.getOwner().setGod(g);
                    selected.add(g.getName());
                    turnIndex++;
                    if (turnIndex == numplayer)
                        turnIndex = 0;
                    if (turnIndex != 1) {
                         passGod = new ArrayList<>(names);
                         passEffect = createList(passGod);
                        notifyCurrent(new WaitYourGodEvent(passGod, passEffect));
                    }
                    currentView = (View) observers.get(turnIndex);
                    Event e;
                    if (turnIndex == 1) {
                        List<String> fullGods = new ArrayList<>();
                        for (God g1 : startGods)
                            fullGods.add(g1.getName());

                        LinkedHashMap<String, String> godPlayer = new LinkedHashMap<>();
                        for (Player p : playerList) {
                            godPlayer.put(p.getUsername(), p.getGod().getName());
                        }
                        e = new StartMatchEvent(godPlayer);
                        System.out.println(currentView.getOwner().getUsername());
                        currentView = (VirtualView) observers.get(0);
                        View tmp = currentView;
                        notifyObservers(e);
                        for (VirtualView v : views)
                            if (!v.getOwner().equals(currentView.getOwner())) {
                                currentView = v;
                                UpdateEvent event = new UpdateEvent(squareToJsonArrayGenerator());
                                notifyCurrent(event);
                            }
                        currentView = tmp;

                    } else {
                        passGod = new ArrayList<>(names);
                        passEffect = createList(passGod);
                        e = new ChooseYourGodEvent(passGod, passEffect);
                        notifyCurrent(e);
                        break;
                    }
                }
            }
        }
    }


    private List<String> createList(List<String> passGod) {
        List<String> passEffect = new ArrayList<>(effects);
       for(Player p: playerList)
           if(p.getGod()!=null){
               passGod.remove(p.getGod().getName());
               passEffect.remove(p.getGod().getTextEffect());
           }

       return passEffect;

    }

    /*
    List<String> passEffect = new ArrayList<>(effects);
        for (int i = 0; i < passGod.size(); i++) {
            String s = passGod.get(i);
            String e = passEffect.get(i);
            if (selected.size() != 0) {
                for (String sel : selected) {
                    if (sel.equals(s)) {
                        passGod.remove(s);
                        passEffect.remove(e);
                    }
                }
            }
        }
        return passEffect;
     */


    public Controller getController() {
        return controller;
    }

    public void setInitialPosition(int coordinateX, int coordinateY, VirtualView view) {
        Worker w = new Worker();
        view.getOwner().setWorkers(w);
        getField().getSquares()[coordinateX][coordinateY].setWorker(w);
    }


    @Override
    public void notifyObservers(Event event) {
        for (Observer observer : observers)
            observer.update(event);
    }

    @Override
    public void register(Observer observer) {

    }

    public void notifyCurrent(Event event) {
        currentView.update(event);
    }


    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    public SquareToJson[][] squareToJsonArrayGenerator() {
        Square[][] mappa = field.getSquares();

        SquareToJson[][] map = new SquareToJson[length][length];
        for (int x = 0; x < length; x++)
            for (int y = 0; y < length; y++)
                if (mappa[x][y].getWorker() != null)
                    map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
                else
                    map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
        return map;
    }


    public void endGame() {

        while (views.size() > 0) {
            currentView = views.get(0);
            LogoutSuccessful logoutSuccessful = new LogoutSuccessful();
            notifyCurrent(logoutSuccessful);
            try {
                currentView.closeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            views.remove(currentView);
            unregister(currentView);
        }
    }


}
