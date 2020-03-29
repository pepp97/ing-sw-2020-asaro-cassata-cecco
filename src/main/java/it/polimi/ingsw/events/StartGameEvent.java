package it.polimi.ingsw.events;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class StartGameEvent implements Event {

    private List<String> gods;

    public StartGameEvent(List<String> gods) {
        this.gods = gods;
    }

    @Override
    public void send(View view) {
        view.update(this);
    }


}
