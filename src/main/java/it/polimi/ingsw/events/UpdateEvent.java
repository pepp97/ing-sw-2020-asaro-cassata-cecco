package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the event sends to the players to notify a change in the model
 */

public class UpdateEvent  implements Event{


    private SquareToJson[][]squares;

    /**
     * Default constructor
     * @param squares is the bi-dimensional array that contains the information of the squares
     */

    public UpdateEvent(SquareToJson[][] squares) {
        this.squares = squares;
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
