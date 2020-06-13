package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;
/**
 * is the event sends to the current player for choose the worker to use
 */

public class ChooseWorker implements Event {

    List<SquareToJson> posWorker;
    private SquareToJson[][]squares;

    /**
     * Default constructor
     * @param posWorker is the List of position where are the player's workers
     * @param squares is the array bi-dimensional that contain the squares information
     */

    public ChooseWorker(List<SquareToJson> posWorker, SquareToJson[][] squares) {
        this.posWorker = posWorker;
        this.squares = squares;
    }

    /**
     * @return the List of worker's position
     */
    public List<SquareToJson> getPosWorker() {
        return posWorker;
    }

    /**
     * @return the array bi-dimensional that contain the squares information
     */

    public SquareToJson[][] getSquares() {
        return squares;
    }

    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */

    @Override
    public void send(Gui view) {
        view.update(this);
    }

    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }

}
