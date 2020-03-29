package it.polimi.ingsw.events;


import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class ConnectionSuccessful implements Event {


    @Override
    public void send(View view) {
        view.update(this);
    }

}
