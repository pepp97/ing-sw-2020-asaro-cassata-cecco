package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ChooseWorker;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class ChooseWorkerState implements TurnState {
    @Override
    public void executeState(Controller controller) {
        List<Square> pos=new ArrayList<>();
        for (Worker s: controller.getGame().getCurrentPlayer().getWorkers())
            pos.add(s.getSquare());
        List <SquareToJson> positions= new ArrayList<>();
        for(Square s : pos)
            positions.add(new SquareToJson(s.getLevel(), s.getWorker().getC().toString(), s.getCoordinateX(), s.getCoordinateY()));
        SquareToJson[][]map=new SquareToJson[5][5];
        Square [][]mappa=controller.getGame().getField().getSquares();

        for(int x=0;x<5;x++)
            for(int y=0; y<5;y++)
                if(mappa[x][y].getWorker()!=null)
                    map[x][y]=new SquareToJson(mappa[x][y].getLevel(),mappa[x][y].getWorker().getC().toString(),x,y);
                else map[x][y]=new SquareToJson(mappa[x][y].getLevel(),"",x,y);
        ChooseWorker chooseWorker=new ChooseWorker(positions, map);
        controller.getGame().getCurrentPlayer().setInQue(true);
        controller.getGame().notifyCurrent(chooseWorker);
        controller.setGoOn(false);
    }

    @Override
    public void goBack() {

    }
}
