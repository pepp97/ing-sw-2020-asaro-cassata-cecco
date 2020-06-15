package it.polimi.ingsw.events;


import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

public class ConnectionSuccessful implements Event {


    @Override
    public void send(Gui view) {
        view.update( this);
        System.out.println(this);
        System.out.println(this.getClass());
    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
        System.out.println(this.getClass());
    }
}
