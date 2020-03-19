package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List <Player> playerList=new ArrayList<>();
    private Player currentPlayer;
    private Target targetSelected;
    private Board board;

    public Game() {
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
}
