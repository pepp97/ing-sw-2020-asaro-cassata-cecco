package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class ChooseTarget implements Event  {

    private String message;
    private List<SquareToJson> availableSquare;

    public ChooseTarget(String message, List<SquareToJson> availableSquare) {
        this.message = message;
        this.availableSquare = availableSquare;
    }

    public String getMessage() {
        return message;
    }

    public List<SquareToJson> getAvailableSquare() {
        return availableSquare;
    }


    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(VirtualView view) {

    }

}
