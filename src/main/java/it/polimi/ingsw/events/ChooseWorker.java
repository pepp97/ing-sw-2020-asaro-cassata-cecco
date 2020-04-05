package it.polimi.ingsw.events;

import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

public class ChooseWorker implements Event {

    List<Square> posWorker;

    public ChooseWorker(List<Square> posWorker) {
        this.posWorker = posWorker;
    }

    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(View view) {
        view.update(this);
    }

}
