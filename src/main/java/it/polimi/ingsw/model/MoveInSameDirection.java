package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

public class MoveInSameDirection implements SubAction {
    /**
     * it is the square where the player will go at the end of the effect
     */
    private Square positionToGo;

    /**
     * it is true if the subaction need interation of the player, false otherwise
     */
    private boolean interationNeeded = false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * this method is called to execute the effect
     * @param game instance of the game
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

    @Override
    public void clearList() {

    }

    /**
     * this method is called to check if the effect is usable
     * @param game instance of the game
     * @return a boolean to determine if the effect is usable
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
            Square old=worker.getHistoryPos().get(worker.getHistoryPos().size()-2);
            Square newSquare=worker.getHistoryPos().get(worker.getHistoryPos().size()-1);
            worker.removeSquare(worker.getHistoryPos().size()-1);
            worker.getSquare().removeWorker();
            old.setWorker(worker);
            worker.removeSquare(worker.getHistoryPos().size()-1);
            newSquare.setWorker(((Worker) game.getTargetSelected()));
            UpdateEvent event = new UpdateEvent(game.squareToJsonArrayGenerator());
            game.notifyObservers(event);
            game.setTargetSelected(null);
            game.getController().goBack();
        }
        return result;
    }
}
