package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

public class askUser implements Event {
    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(View view) {
        view.update(this);
    }
}
