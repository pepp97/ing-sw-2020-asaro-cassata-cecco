package it.polimi.ingsw.events;


import it.polimi.ingsw.view.VirtualView;

public class ConnectionSuccessful implements Event {


    @Override
    public void send(VirtualView view) {
        view.update(this);
    }

}
