package it.polimi.ingsw.model;

public class MoveInSameDirection implements SubAction {

    private Square positionToGo;


    /**
     * @param game
     */
    @Override
    public void use(Game game) {
        Worker toMove=(Worker)game.getTargetSelected();
        toMove.getSquare().removeWorker();
        positionToGo.setWorker(toMove);

    }

    /**
     * @param game
     * @return
     */

    // aumenta aumenta, diminuisce diminuisce per le diagonali,
    @Override
    public Boolean isUsable(Game game) {
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
        if (squares[coordinateX][coordinateY].getWorker() == null) {
            positionToGo = squares[coordinateX][coordinateY].getSquare();
            return true;
        }
        return false;

    }
}
