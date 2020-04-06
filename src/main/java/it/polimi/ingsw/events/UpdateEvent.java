package it.polimi.ingsw.events;

import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;

public class UpdateEvent  implements Event{


    private Square [][]squares;

    public UpdateEvent(Square[][] squares) {
        this.squares = squares;
    }

    public Square[][] getSquares() {
        return squares;
    }

    @Override
    public void send(Gui view) {
        view.update(this);
    }

    @Override
    public void send(View view) {
        view.update(this);
    }
}
