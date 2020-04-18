package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class ExceptionEvent implements Event {
    private String exception;

    public ExceptionEvent(String exception) {
        this.exception = exception;
    }

    public String getException() {
        return exception;
    }

    @Override
    public void send(Gui view) {
        view.update(this);
    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }
}
