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
    private SquareToJson[][]squares;

    public ChooseTarget(String message, List<SquareToJson> availableSquare, SquareToJson[][] squares) {
        this.message = message;
        this.availableSquare = availableSquare;
        this.squares = squares;
    }

    public SquareToJson[][] getSquares() {
        return squares;
    }

    public String getMessage() {
        return message;
    }

    public List<SquareToJson> getAvailableSquare() {
        return availableSquare;
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
