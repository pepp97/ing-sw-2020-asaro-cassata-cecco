package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

public class SettingsEvent implements Event {

    @Override
    public void send(Gui view) {

        view.update(this);

    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }

}
