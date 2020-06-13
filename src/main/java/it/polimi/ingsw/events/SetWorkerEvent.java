package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

/**
 * is the event sends to the current player to set the initial position of workers
 */

public class SetWorkerEvent implements Event {

    private List <SquareToJson> availableSquares=new ArrayList<>();
    private SquareToJson [][] map;

    /**
     * Default constructor
     * @param availableSquares is the List of squares where workers can be positionated
     * @param map is the bi-dimensional array that contain the information of the squares
     */

    public SetWorkerEvent(List<SquareToJson> availableSquares, SquareToJson[][] map) {
        this.availableSquares = availableSquares;
        this.map = map;
    }

    /**
     * @return the List of squares where workers can be positionated
     */

    public List<SquareToJson> getAvailableSquares() {
        return availableSquares;
    }

    /**
     * @return the bi-dimensional array that contain the information of the squares
     */
    public SquareToJson[][] getMap() {
        return map;
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
