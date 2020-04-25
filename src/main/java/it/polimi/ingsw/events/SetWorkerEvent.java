package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

public class SetWorkerEvent implements Event {
    private List <SquareToJson> availableSquares=new ArrayList<>();
    private SquareToJson [][] map;

    public SetWorkerEvent(List<SquareToJson> availableSquares, SquareToJson[][] map) {
        this.availableSquares = availableSquares;
        this.map = map;
    }

    public List<SquareToJson> getAvailableSquares() {
        return availableSquares;
    }

    public SquareToJson[][] getMap() {
        return map;
    }

    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(VirtualView view) {

    }
}