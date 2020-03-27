package it.polimi.ingsw.events;


import it.polimi.ingsw.view.VirtualView;

public interface Event {
    public void send(VirtualView view);
}
