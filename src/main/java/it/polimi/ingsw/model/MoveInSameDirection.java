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
        UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
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
            UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
            game.notifyObservers(event);
            game.setTargetSelected(null);
            //mandare evento
            game.getController().goBack();
            //game.setTargetSelected(null);
        }
        return result;
    }
}
