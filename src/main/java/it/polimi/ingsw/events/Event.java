package it.polimi.ingsw.events;

import it.polimi.ingsw.view.View;

public interface Event {
    public void send(View view);
}
