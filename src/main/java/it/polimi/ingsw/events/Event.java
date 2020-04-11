package it.polimi.ingsw.events;


import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public interface Event {
    void send(Gui view);

    public void send(VirtualView view);
}
