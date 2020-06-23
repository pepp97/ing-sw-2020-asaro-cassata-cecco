package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to add a filter that says that the target choose can't be the last
 *
 * @author Salvatore Cassata
 */

public class FilterNotSame implements SubAction {

    /**
     * it is true if the subaction need interation of the player, false otherwise
     */

    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }
    /**
     * this method is called to execute the effect
     * @param game instance of the game
     */
    @Override
    public void use(Game game) {

        // controllare tutte per build o passare penultima posizione

        Worker worker = (Worker) game.getTargetInUse();
        game.getController().setGoOn(false);

        for (Square s : worker.getSquare().getAdjacentSquares()) {
            if (s.getStart_level() != s.getLevel())
                worker.setSquareNotAvailable(s);
        }
        if (worker.getSquareNotAvailable() == null)
            worker.setSquareNotAvailable(worker.getHistoryPos().get(0)); // passo la penultima posizione, che coincide con la prima

    }

    @Override
    public void clearList() {

    }


    /**
     * this method is called to check if the effect is usable
     * @param game instance of the game
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {

        game.getController().setGoOn(true);
        return true;

    }
}
