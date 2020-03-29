package it.polimi.ingsw.events;


import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public interface Event {
    public void send(View view);
}
