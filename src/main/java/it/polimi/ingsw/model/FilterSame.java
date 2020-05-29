package it.polimi.ingsw.model;


/**
 * It is the micro-effect that have the role to set a filter
 */
public class FilterSame implements SubAction {

    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }
    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {

        // controllare tutte per build o passare penultima posizione

        Worker worker = (Worker) game.getTargetInUse();

        for (Square s : worker.getSquare().getAdjacentSquares()) {
            if (s.getStart_level() != s.getLevel())
                worker.setMandatorySquare(s);
        }
        game.getController().setGoOn(false);
    }

    @Override
    public void clearList() {

    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        game.getController().setGoOn(true);
        return true;

    }
}


