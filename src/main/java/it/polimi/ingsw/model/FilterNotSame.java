package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to add a filter that says that the target choose can't be the last
 *  @author  Salvatore Cassata
 */

public class FilterNotSame implements SubAction {
    /**
     * @param game
     */
    @Override
    public void use(Game game) {

        // controllare tutte per build o passare penultima posizione

        Worker worker = (Worker) game.getTargetInUse();

        for(Square s: worker.getSquare().getAdjacentSquares()){
            if(s.getStart_level() != s.getLevel())
                worker.setSquareNotAvailable(s);
        }
        if (worker.getSquareNotAvailable() == null)
            worker.setSquareNotAvailable(worker.getHistoryPos().get(0)); // passo la penultima posizione, che coincide con la prima

    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {

        return true;

    }
}
