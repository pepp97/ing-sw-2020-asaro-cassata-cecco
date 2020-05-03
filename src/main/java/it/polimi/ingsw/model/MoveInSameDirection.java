package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

public class MoveInSameDirection implements SubAction {

    private Square positionToGo;
    private boolean interationNeeded = false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * @param game
     */
    @Override
    public void use(Game game) {
        Worker toMove = ((Worker) game.getTargetSelected());
        //toMove.getSquare().removeWorker();
        System.out.println(toMove);
        System.out.println(positionToGo);
        positionToGo.setWorker(toMove);
        Square[][] mappa = game.getField().getSquares();
        SquareToJson[][] map = new SquareToJson[5][5];
        for (int x = 0; x < 5; x++)
            for (int y = 0; y < 5; y++)
                if (mappa[x][y].getWorker() != null)
                    map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateX());
                else
                    map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
        UpdateEvent event = new UpdateEvent(map);
        game.notifyObservers(event);
        game.getController().setGoOn(false);
    }

    /**
     * @param game
     * @return
     */

    // aumenta aumenta, diminuisce diminuisce per le diagonali,
    @Override
    public boolean isUsable(Game game) {
        game.getController().setGoOn(true);
        Worker worker = (Worker) game.getTargetInUse();
        int coordinateX = worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX();
        int coordinateY = worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY();
        Boolean result = false;
        Square[][] squares = game.getField().getSquares();

        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateX() > worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX())
            coordinateX = coordinateX - 1;
        else if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateX() < worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX())
            coordinateX = coordinateX + 1;
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateY() > worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY())
            coordinateY = coordinateY - 1;
        else if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateY() < worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY())
            coordinateY = coordinateY + 1;

        if (coordinateX>=0 && coordinateX <= 4 && coordinateY>=0 && coordinateY <= 4 && squares[coordinateX][coordinateY].getWorker() == null && squares[coordinateX][coordinateY].getLevel() != 4 ) {
            positionToGo = squares[coordinateX][coordinateY].getSquare();
            result = true;
            return result;
        } else {
            game.notifyCurrent(new ExceptionEvent("Minothaur cannot use its effect, let's go back."));
            game.getController().setGoOn(false);
            Square old=worker.getHistoryPos().get(0);
            Square newSquare=worker.getHistoryPos().get(1);
            worker.getHistoryPos().remove(1);
            worker.getSquare().removeWorker();
            old.setWorker(worker);
            worker.getHistoryPos().remove(1);
            newSquare.setWorker(((Worker) game.getTargetSelected()));
            Square[][] mappa = game.getField().getSquares();
            SquareToJson[][] map = new SquareToJson[5][5];
            for (int x = 0; x < 5; x++)
                for (int y = 0; y < 5; y++)
                    if (mappa[x][y].getWorker() != null)
                        map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateX());
                    else
                        map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
            UpdateEvent event = new UpdateEvent(map);
            game.notifyObservers(event);
            game.setTargetSelected(null);
            //mandare evento
            game.getController().goBack();
            //game.setTargetSelected(null);
        }
        return result;
    }
}
