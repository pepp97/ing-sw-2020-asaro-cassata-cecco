package it.polimi.ingsw.events;

import it.polimi.ingsw.view.VirtualView;

public class EndGame implements Event {

    private String result;

    public EndGame(String result) {
        this.result = result;
    }

    @Override
    public void send(VirtualView view) {
       view.update(this);
    }

}
