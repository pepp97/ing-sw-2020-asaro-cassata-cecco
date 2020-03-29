package it.polimi.ingsw.events;

import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class ChooseTarget implements Event {

    private String message;
    private List<Square> availableSquare;

    public ChooseTarget(String message, List<Square> availableSquare) {
        this.message = message;
        this.availableSquare = availableSquare;
    }

    @Override
    public void send(View view) {
        view.update(this);
    }
}
