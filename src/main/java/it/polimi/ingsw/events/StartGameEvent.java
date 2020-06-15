package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class StartGameEvent implements Event {

    private List<String> gods;
    private int numPlayers;
    public StartGameEvent(List<String> gods , int numPlayers) {
        this.gods = gods;
        this.numPlayers = numPlayers;
    }


    @Override
    public void send(Gui view) {
        view.update(this);
    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }

    public List<String> getGods() {
        return gods;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
