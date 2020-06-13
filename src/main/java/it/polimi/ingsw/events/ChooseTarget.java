package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

/**
 * is the event sends to the current player for choose the target
 */

public class ChooseTarget implements Event  {

    private String message;
    private List<SquareToJson> availableSquare;
    private SquareToJson[][]squares;

    /**
     * Default constructor
     * @param message is the message to show to the player
     * @param availableSquare is the List of square that the player will can choose
     * @param squares is the array bi-dimensional that contain the squares information
     */

    public ChooseTarget(String message, List<SquareToJson> availableSquare, SquareToJson[][] squares) {
        this.message = message;
        this.availableSquare = availableSquare;
        this.squares = squares;
    }

    /**
     * @return the array bi-dimensional that contain the squares information
     */

    public SquareToJson[][] getSquares() {
        return squares;
    }

    /**
     * @return the message to show to the player
     */

    public String getMessage() {
        return message;
    }

    /**
     * @return the available square where there are the available target
     */

    public List<SquareToJson> getAvailableSquare() {
        return availableSquare;
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
