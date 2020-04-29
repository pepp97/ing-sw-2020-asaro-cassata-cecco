package it.polimi.ingsw.model;

import it.polimi.ingsw.events.ExceptionEvent;

public class MoveInSameDirection implements SubAction {

    private Square positionToGo;
    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * @param game
     */
    @Override
    public void use(Game game) {
        Worker toMove=((Worker)game.getTargetSelected());
        //toMove.getSquare().removeWorker();
        System.out.println(toMove);
        System.out.println(positionToGo);
        positionToGo.setWorker(toMove);
        game.getCurrentPlayer().setInQue(false);
    }

    /**
     * @param game
     * @return
     */

    // aumenta aumenta, diminuisce diminuisce per le diagonali,
    @Override
    public boolean isUsable(Game game) {
        game.getCurrentPlayer().setInQue(true);
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
        if (squares[coordinateX][coordinateY].getWorker() == null && squares[coordinateX][coordinateY].getLevel()!=4) {
            positionToGo = squares[coordinateX][coordinateY].getSquare();
            result=true;
            return result;
        }
        new ExceptionEvent("Minothaur cannot use its effect, try again.");
        //mandare evento
        game.getController().goBack();
        return result;
    }
}
