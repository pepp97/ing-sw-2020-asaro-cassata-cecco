package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class ChooseWorker implements Event {

    List<SquareToJson> posWorker;
    private SquareToJson[][]squares;

    public ChooseWorker(List<SquareToJson> posWorker, SquareToJson[][] squares) {
        this.posWorker = posWorker;
        this.squares = squares;
    }

    public List<SquareToJson> getPosWorker() {
        return posWorker;
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
