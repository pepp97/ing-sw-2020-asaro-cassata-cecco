package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

public class UpdateEvent  implements Event{


    private SquareToJson[][]squares;

    public UpdateEvent(SquareToJson[][] squares) {
        this.squares = squares;
    }

    public SquareToJson[][] getSquares() {
        return squares;
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
