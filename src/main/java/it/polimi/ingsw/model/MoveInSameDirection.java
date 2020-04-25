package it.polimi.ingsw.model;

/**
 * It is the micro-effect that has the role of moving another player in the same direction
 */
public class MoveInSameDirection implements SubAction {

    private Square positionToGo;


    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {
        Worker toMove = ((Worker) game.getTargetSelected());
        //toMove.getSquare().removeWorker();
        System.out.println(toMove);
        System.out.println(positionToGo);
        positionToGo.setWorker(toMove);

    }


    // aumenta aumenta, diminuisce diminuisce per le diagonali,

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
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
        if (squares[coordinateX][coordinateY].getWorker() == null && squares[coordinateX][coordinateY].getLevel() != 4) {
            positionToGo = squares[coordinateX][coordinateY].getSquare();
            return true;
        }
        game.getController().goBack();
        return false;
    }
}
