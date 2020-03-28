package it.polimi.ingsw.model;
//DEVE AVERE ISTANZA CONTROLLER CHE HA ATTRIBUTO SKIP.
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List <Player> playerList=new ArrayList<>();
    private Player currentPlayer;
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
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    //IMPLEMENTARE
    public void login(String nickname, Color color, VirtualView view) {
    }

    public void selectNplayer(int nplayer) {
        this.numplayer=nplayer;
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

    //IMPLEMENTARE
    public void setInitialPosition(int coordinateX, int coordinateY, VirtualView view) {
    }



}
