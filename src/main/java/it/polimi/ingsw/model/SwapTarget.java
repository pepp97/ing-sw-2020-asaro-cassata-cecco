package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to swap the target
 */
public class SwapTarget implements SubAction {
    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }

    @Override
    public void use(Game game) {
        game.getController().setGoOn(false);
        Target temp = game.getTargetInUse();
        game.setTargetInUse((Worker)game.getTargetSelected());
        game.setTargetSelected(temp);
        game.getCurrentPlayer().setInQue(false);
    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public boolean isUsable(Game game) {
        //game.getCurrentPlayer().setInQue(true);
        game.getController().setGoOn(true);
        return true;
    }

}
