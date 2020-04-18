package it.polimi.ingsw.model;
//DEVE AVERE ISTANZA CONTROLLER CHE HA ATTRIBUTO SKIP.
import it.polimi.ingsw.Observable;
import it.polimi.ingsw.Observer;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
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
    private int numplayer;
    private List<God> startGods;


    public Game() {
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
    public void login(String nickname, Color color, VirtualView view) {
        //  aggiustare numero di giocatori che si possono loggare
        Event e=null;
        if(nicknameAvailable(nickname) && colorAvailable(color)){
            currentView=view;
            Player player=new Player(nickname,color);
            playerList.add(player);
            view.setOwner(player);
            if (playerList.size()==1){
                e=new SettingsEvent();
                notifyCurrent(e);
            }
           else if(playerList.size()==numplayer) {
                List<String> godlist=new ArrayList<>();
                for(God g: startGods)
                    godlist.add(g.getName());
                e = new StartGameEvent(godlist, numplayer);
                notifyObservers(e);
            }
           /*else {
                List<String> list=new ArrayList<>();
                for(Player p: playerList)
                    list.add(p.getUsername());
                e=new LoginSuccessful(list);
                notifyCurrent(e);}*/
        }
        else if (!nicknameAvailable(nickname))
            e=new ExceptionEvent("Username already in use!");
        else
            e=new ExceptionEvent("Color already in use!");
        notifyCurrent(e);

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


    public void selectNplayer(int nplayer) {
        this.numplayer=nplayer;
        List<String> list=new ArrayList<>();
        for(Player p: playerList)
            list.add(p.getUsername());
        Event e=new LoginSuccessful(list);
        notifyCurrent(e);
    }


    public void setUsableGod(List<String> god, List<God> totalGods){
        for (String s : god) {
            for (God totalGod : totalGods) {
                if (s.equals(totalGod.getName())) {
                    startGods.add(totalGod);
                }
            }
        }
    }

    public void setPlayerGod(String name, VirtualView view) {
        for (Player player : playerList) {
            if (view.getOwner() == player) {
                for(God start: startGods){
                    if(name.equals(start.getName())){
                        player.setGod(start);
                    }
                }
            }
        }
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
