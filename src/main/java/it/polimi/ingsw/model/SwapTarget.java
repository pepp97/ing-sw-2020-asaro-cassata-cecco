package it.polimi.ingsw.model;

public class SwapTarget implements SubAction {

    private boolean skippable=true;

    public SwapTarget(boolean skippable) {
        this.skippable = skippable;
    }

    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {
        Target temp = game.getTargetInUse();
        game.setTargetInUse(game.getTargetSelected());
        game.setTargetSelected(temp);

    }

    /**
     * @param worker
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        return true;
    }
}
