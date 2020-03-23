package it.polimi.ingsw.model;

public class MoveInSameDirection implements SubAction {

    private boolean skippable=true;
    private Square positionToGo;

    public MoveInSameDirection(boolean skippable) {
        this.skippable = skippable;
    }

    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

        worker.getSquare().removeWorker();
        worker.setActualPos(positionToGo);

    }

    /**
     * @param worker
     * @param game
     * @return
     */

    // aumenta aumenta, diminuisce diminuisce per le diagonali,
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        int coordinateX = worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX();
        int coordinateY = worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY();
        Boolean result = false;
        Square[][] squares = game.getBoard().getField().getSquares();

        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateX() > worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX())
            coordinateX = coordinateX + 1;
        else if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateX() < worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateX())
            coordinateX = coordinateX - 1;
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateY() > worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY())
            coordinateY = coordinateY + 1;
        else if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getCoordinateY() < worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getCoordinateY())
            coordinateY = coordinateY - 1;
        if (squares[coordinateX][coordinateY].getWorker() == null) {
            positionToGo = squares[coordinateX][coordinateY].getSquare();
            return true;
        }
        return false;

    }
}
