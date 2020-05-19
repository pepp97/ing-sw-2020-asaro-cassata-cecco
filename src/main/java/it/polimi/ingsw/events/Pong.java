package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

public class Pong  implements Event{

    public Pong() {
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
