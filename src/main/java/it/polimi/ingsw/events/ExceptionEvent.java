package it.polimi.ingsw.events;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class ExceptionEvent implements Event {
    private String exception;

    public ExceptionEvent(String exception) {
        this.exception = exception;
    }

    @Override
    public void send(View view) {
        view.update(this);
    }
}
