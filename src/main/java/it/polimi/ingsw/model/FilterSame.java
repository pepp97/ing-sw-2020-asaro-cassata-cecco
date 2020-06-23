package it.polimi.ingsw.model;


/**
 * It is the micro-effect that have the role to set a filter
 */
public class FilterSame implements SubAction {

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


