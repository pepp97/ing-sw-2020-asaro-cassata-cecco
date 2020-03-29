package it.polimi.ingsw.events;

import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class DeathPlayer implements Event {

    @Override
    public void send(View view) {
        view.update(this);
    }
}
