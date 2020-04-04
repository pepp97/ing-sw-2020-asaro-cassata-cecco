package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class EndGame implements Event {

    private String result;

    public EndGame(String result) {
        this.result = result;
    }

    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(View view) {
       view.update(this);
    }

}
