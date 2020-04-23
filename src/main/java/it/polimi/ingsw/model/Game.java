package it.polimi.ingsw.model;
//DEVE AVERE ISTANZA CONTROLLER CHE HA ATTRIBUTO SKIP.
import it.polimi.ingsw.Observable;
import it.polimi.ingsw.Observer;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Game implements Observable {

    private List <Player> playerList=new ArrayList<>();
    private List <Observer> observers=new ArrayList<>();
    private Player currentPlayer;
    private View currentView;
    private Target targetSelected;
    private Board board;
    private Target targetInUse;
    private Field field;
    private int numplayer=0;
    private List<God> startGods;
    private List<God> totalGods;
    private ParserJson p;
    private List <String> effects=new ArrayList<>();
    private List <String> names=new ArrayList<>();
    private int turnIndex=1;
    private List<String> selected=new ArrayList<>();
    private Player winner;
    private Controller controller;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Game(Controller controller) {
        this.controller=controller;
        field=new Field();
        board=new Board();
        startGods=new ArrayList<>();
    }

    public void add(Player player){
        if (playerList.size()< numplayer)
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
        if(playerList.size()<3)
        this.playerList.add(player);
    }
    public void removePlayerInList(Player player){
        this.playerList.remove(player);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
        Event e=null;
        if(playerList.size()==numplayer && numplayer!=0){
            currentView=view;
            e=new ExceptionEvent("game already started");
            notifyCurrent(e);
        }
         else if(playerList.size()==1 && numplayer==0){
            currentView=view;
            e=new ExceptionEvent("Another player is setting the number of opponents, please wait");
            System.out.println("ERROREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        notifyCurrent(e);}
        else if(nicknameAvailable(nickname) && colorAvailable(color)){
            currentView=view;
            observers.add(view);
            Player player=new Player(nickname,color);
            playerList.add(player);
            view.setOwner(player);
            if (playerList.size()==1){
                e=new SettingsEvent();
                notifyCurrent(e);
            }
           else if(playerList.size()==numplayer) {
                System.out.println("ENTRATO?");
                List<String> godlist=new ArrayList<>();
                 p=new ParserJson();
                totalGods=p.getUsableGod();
                for(God g: totalGods){
                    godlist.add(g.getName());
                }
                List<String> list=new ArrayList<>();
                for(Player p1: playerList)
                    list.add(p1.getUsername());
                e=new LoginSuccessful(list);
                notifyObservers(e);
                e = new StartGameEvent(godlist, numplayer);
                currentView= (View) observers.get(0);
                notifyCurrent(e);
            }else {List<String> list=new ArrayList<>();
                for(Player p: playerList)
                    list.add(p.getUsername());
                 e=new LoginSuccessful(list);
                 notifyObservers(e);}
        }
        else if (!nicknameAvailable(nickname)){
            e=new ExceptionEvent("Username already in use!");
            notifyCurrent(e);}
        else {
            e = new ExceptionEvent("Color already in use!");
            notifyCurrent(e);
        }

    }

    private boolean nicknameAvailable(String nick){
        for(Player p: playerList)
            if(nick.equals(p.getUsername()))
                return false;
        return true;
    }

    private boolean colorAvailable(Color color){
        for(Player p: playerList)
            if(color.equals(p.getColor()))
                return false;
        return true;
    }


    public void selectNplayer(int nplayer, VirtualView view) {
        this.currentView=view;
        this.numplayer=nplayer;
        List<String> list=new ArrayList<>();
        for(Player p: playerList)
            list.add(p.getUsername());
        Event e=new LoginSuccessful(list);
        notifyCurrent(e);
    }


    public void setUsableGod(List<String> god){
        for (String s : god) {
            for (God totalGod : p.getUsableGod()) {
                if (s.equals(totalGod.getName())) {
                    startGods.add(totalGod);
                   effects.add(totalGod.getTextEffect());
                   names.add(totalGod.getName());
                }
            }
        }
        currentView=(View)observers.get(turnIndex);
        Event e=new ChooseYourGodEvent(names,effects);
        notifyCurrent(e);
    }

    public synchronized void setPlayerGod(String godname, VirtualView view) {

        System.out.println("il nome è " + godname);
        for(God g: startGods){
            System.out.println(g.getName());
            if(g.getName().equals(godname)) {
                if (selected.contains(g.getName())) {
                    currentView = view;
                    notifyCurrent(new ExceptionEvent("God already selected"));
                } else {
                    view.getOwner().setGod(g);
                    selected.add(g.getName());
                    turnIndex++;
                    if (turnIndex == numplayer)
                        turnIndex = 0;
                    currentView = (View) observers.get(turnIndex);
                    Event e;
                    if (turnIndex == 1) {
                        List<String> fullGods = new ArrayList<>();
                        for (God g1 : startGods)
                            fullGods.add(g1.getName());

                        LinkedHashMap<String,String> godPlayer=new LinkedHashMap<>();
                        for(Player p:playerList){
                            godPlayer.put(p.getUsername(),p.getGod().getName());
                        }
                        e = new StartMatchEvent(godPlayer);
                        notifyObservers(e);
                    } else {
                        e = new ChooseYourGodEvent(names, effects);
                        notifyCurrent(e);
                        break;
                    }
                }
            }
        }
    }


    public Controller getController() {
        return controller;
    }

    public void setInitialPosition(int coordinateX, int coordinateY, VirtualView view) {
        Worker w=new Worker();
        view.getOwner().setWorkers(w);
        getField().getSquares()[coordinateX][coordinateY].setWorker(w);
    }


    @Override
    public void notifyObservers(Event event) {
        for (Observer observer : observers)
            observer.update(event);
    }

    public void notifyCurrent(Event event){
        currentView.update(event);
    }

    @Override
    public void register(Observer observer) {

    }

    @Override
    public void unregister(Observer observer) {

    }
}
