package it.polimi.ingsw.model;


import it.polimi.ingsw.Observable;
import it.polimi.ingsw.Observer;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.Disconnection;
//import it.polimi.ingsw.commands.PingDelete;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.io.IOException;
import java.util.*;

/**
 * This class is the base of the game and is the link between the others class
 */
public class Game implements Observable {

    /**
     * it is the list of Player that play this game
     */

    private List<Player> playerList = new ArrayList<>();
    /**
     * it is the list of observers that need to communicate change in the model
     */
    private List<Observer> observers = new ArrayList<>();
    /**
     * it is the current player
     */
    private Player currentPlayer;
    /**
     * it is the view of the current player
     */
    private View currentView;
    /**
     * it is the target choose by the current player
     */
    private Target targetSelected;
    /**
     * it is the target choose to use during the current turn by the current player
     */
    private Target targetInUse;
    /**
     * it is the entity of the field
     */
    private Field field;
    /**
     * it is the number of player setted by the first player logged
     */
    private int numplayer = 0;
    /**
     * it is the list of the God that player can choose when is his turn
     */
    private List<God> startGods;
    /**
     * it is the list of all the god can choose
     */
    private List<God> totalGods;
    /**
     * it is the list of effects of the God card
     */
    private List<String> effects = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    /**
     * it is the index of the position in the list of the current player
     */
    private int turnIndex = 1;
    /**
     * it is the list of the God used during the match
     */
    private List<String> selected = new ArrayList<>();
    /**
     * it is the winner of the match
     */
    private Player winner;
    /**
     * it is the controller entity
     */
    private Controller controller;
    /**
     * it is the number of retries to reach the timeout
     */
    private int maxRetries = 10;
    /**
     * it is true if the match have to finish, false otherwise
     */
    private boolean stop = false;
    /**
     * it is the length of raw and columns of the field
     */
    private static final int length = 5;
    /**
     * it is true if the player want use the undo command, false otherwise
     */
    private boolean undo = false;
    /**
     * it is true if the player have to killed, false otherwise
     */
    private boolean kill = false;
    /**
     * it is true if the match have to finish, false otherwise
     */
    private boolean end = false;
    /**
     * it is a temporary virtual view
     */
    private VirtualView tmpView;

    public List<String> getNames() {
        return List.copyOf(names);
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isUndo() {
        return undo;
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
    }

    /**
     * default constructor
     *
     * @param controller is the controller of the match
     */

    public Game(Controller controller) {
        this.controller = controller;
        field = new Field();
        startGods = new ArrayList<>();
    }

    /**
     * this method is used to add a player in the list
     *
     * @param player it is the player to add
     */

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

    /**
     * this method is called when a player lose, to remove it from the list of the player
     *
     * @param player it is the player to remove
     */

    public void removePlayerInList(Player player) {
        numplayer--;
        Worker w1 = player.getWorkers().get(0);
        Worker w2 = player.getWorkers().get(1);
        for (int j = 0; j < 5; j++)
            for (int i = 0; i < 5; i++)
                if (field.getSquares()[i][j].getWorker() != null)
                    if (field.getSquares()[i][j].getWorker().equals(w1) || field.getSquares()[i][j].getWorker().equals(w2))
                        field.getSquares()[i][j].removeWorker();

        for (Observer o : observers) {
            if (o.getOwner().equals(player)) {
                unregister(o);
                VirtualView v = (VirtualView) o;
                try {
                    v.closeAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }


        this.playerList.remove(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        for (Observer v : observers)
            if (v.getOwner().equals(currentPlayer))
                currentView = (View) v;
    }

    public Target getTargetSelected() {
        return targetSelected;
    }

    public void setTargetSelected(Target targetSelected) {
        this.targetSelected = targetSelected;
    }

    /**
     * it is the method called to logged a player
     *
     * @param nickname it is the nickname choose by the player
     * @param color    it is the color choose by the player
     * @param view     it is the current view of the player
     */


    public synchronized void login(String nickname, Color color, VirtualView view) {
        //  aggiustare numero di giocatori che si possono loggare
        if (currentView != null)
            tmpView = (VirtualView) currentView;
        currentView = view;
        if (gameAlreadyStarted()) {
            notifyCurrent(new ExceptionEvent("game already started, you have been disconnected"));
            notifyCurrent(new LogoutSuccessful());
            try {
                currentView.closeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentView = tmpView;
        } else if (!nicknameAvailable(nickname)) {
            notifyCurrent(new ExceptionEvent("Username already in use!"));
        } else if (!colorAvailable(color)) {
            notifyCurrent(new ExceptionEvent("Color already in use!"));
        } else {
            /*currentView = view;
            views.add(view);*/
            if (playerList.size() == 1 && numplayer == 0) {
                notifyCurrent(new ExceptionEvent("Another player is setting the number of opponents, please wait"));
            } else if (nicknameAvailable(nickname) && colorAvailable(color)) {
                playerLogin(nickname, color, view);
            }
        }
    }

    /**
     * it is a check if the game is started
     *
     * @return true if the game is started false otherwise
     */

    public boolean gameAlreadyStarted() {
        if (playerList.size() == numplayer && numplayer != 0) {
            return true;
        }
        return false;
    }

    /**
     * this method handle the player login
     *
     * @param nickname it is the nickname choose by the player
     * @param color    it is the color choose by the player
     * @param view     it is the current view of the player
     */

    private void playerLogin(String nickname, Color color, VirtualView view) {
        register(view);
        startMytimer();
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

    /**
     * this method is used to complete the login of the player
     *
     * @param playerList it's the player to add to the game
     */

    private void lastOption(List<Player> playerList) {
        List<String> list = new ArrayList<>();
        for (Player p : playerList)
            list.add(p.getUsername());
        notifyObservers(new LoginSuccessful(list));
    }

    /**
     * this method is called when complete the login the last player
     */

    private void checkIfFull() {
        List<String> godlist = new ArrayList<>();

        totalGods = controller.getP().getUsableGod();
        for (God g : totalGods) {
            godlist.add(g.getName());
        }
        List<String> list = new ArrayList<>();
        for (Player p1 : playerList)
            list.add(p1.getUsername());
        notifyObservers(new LoginSuccessful(list));
        currentView = (View) observers.get(0);
        notifyCurrent(new StartGameEvent(godlist, numplayer));
        notifyObservers(new Pong());
    }

    /**
     * this method is called to start the timer that handle the ping
     */

    public void startMytimer() {
        Timer timer = new Timer();

        TimeoutCheckerInterface timeoutChecker = (l) -> {
            System.out.println("TIMERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR: " + l);
            if (kill)
                return true;
            //notifyObservers(new Pong());
            Boolean timeoutReached = l > maxRetries;
            int i = 0;

            for (; i < observers.size(); i++) {
                VirtualView v = (VirtualView) observers.get(i);
                if (!v.isPing()) {
                    break;
                }
            }

            if (i == observers.size()) {
                for (Observer v : observers) {
                    VirtualView v1 = (VirtualView) v;
                    v1.setPing(false);
                    v.update(new Pong());
                }
                startMytimer();
                return true;
            }

            if (timeoutReached) {
                System.out.println("timeout!!!!!!!!!!!!!!!!!!!!!!");

                for (Observer o : observers) {
                    VirtualView v = (VirtualView) o;
                    if (!v.isPing()) {
                        controller.apply(new Disconnection(), v);
                        break;
                    }
                }

                return true;
            }
           /* if (stop) {
                stop = false;
                return true;
            }*/

            return false;
        };

        TimerTask task = new TimeoutCounter(timeoutChecker);
        int initialDelay = 50;
        int delta = 1000;
        timer.schedule(task, initialDelay, delta);

    }

    //   public void resetTimer() {
    //notifyObservers(new Pong());
    //stop = true;
    //     startMytimer();
    //}

    /**
     * it checks if the nickname is available
     *
     * @param nick is the nickname choose by the player
     * @return true if yhe nickname is available, false otherwise
     */

    public boolean nicknameAvailable(String nick) {
        for (Player p : playerList)
            if (nick.equals(p.getUsername()))
                return false;
        return true;
    }

    /**
     * it checks if the color is available
     *
     * @param color is the color choose by the player
     * @return true if yhe color is available, false otherwise
     */

    public boolean colorAvailable(Color color) {
        for (Player p : playerList)
            if (color.equals(p.getColor()))
                return false;
        return true;
    }

    /**
     * this method is called when the first player choose the number of player of the game
     *
     * @param nplayer it is the number of player of the game
     * @param view    is the virtual view of the player that has choose
     */


    public void selectNplayer(int nplayer, VirtualView view) {
        this.currentView = view;
        this.numplayer = nplayer;
        List<String> list = new ArrayList<>();
        for (Player p : playerList)
            list.add(p.getUsername());
        Event e = new LoginSuccessful(list);
        notifyCurrent(e);
    }

    /**
     * this method is called to set the gods that players can choose
     *
     * @param god is the list of gods
     */


    public void setUsableGod(List<String> god) {
        for (String s : god) {
            for (God totalGod : controller.getP().getUsableGod()) {
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

    /**
     * this method is called to set the god corresponding at the current player
     *
     * @param godname is the name of the god choose
     * @param view    is the virtual view of the player that have choose
     */

    public synchronized void setPlayerGod(String godname, VirtualView view) {
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
                        for (Observer o : observers) {
                            VirtualView v = (VirtualView) o;
                            if (!v.getOwner().equals(currentView.getOwner())) {
                                currentView = v;
                                UpdateEvent event = new UpdateEvent(squareToJsonArrayGenerator());
                                notifyCurrent(event);
                            }
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
        for (Player p : playerList)
            if (p.getGod() != null) {
                passGod.remove(p.getGod().getName());
                passEffect.remove(p.getGod().getTextEffect());
            }

        return passEffect;

    }


    public Controller getController() {
        return controller;
    }

    /**
     * this method is called to set the initial position of the worker
     *
     * @param coordinateX is the coordinate x of position choose
     * @param coordinateY is the coordinate y of position choose
     * @param view        is the virtual view of the current player
     */

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
        observers.add(observer);
    }

    public void notifyCurrent(Event event) {
        currentView.update(event);
    }


    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * this method is called to generate an array used to communicate with the views of the players
     *
     * @return the array that represents the field
     */

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

    /**
     * this method is called to end the game
     */


    public void endGame() {
        while (observers.size() > 0) {
            currentView = (VirtualView) observers.get(0);
            LogoutSuccessful logoutSuccessful = new LogoutSuccessful();
            notifyCurrent(logoutSuccessful);
            try {
                currentView.closeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
            unregister(currentView);
        }
        end = true;
        //controller.restart();
    }

    public void setNumplayer(int numplayer) {
        this.numplayer = numplayer;
    }

    public int getNumplayer() {
        return numplayer;
    }

    public List<God> getStartGods() {
        return startGods;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void killtimer() {
        kill = true;
    }


    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean b) {
        end = b;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}