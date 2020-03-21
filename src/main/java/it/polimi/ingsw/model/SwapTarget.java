package it.polimi.ingsw.model;

public class SwapTarget implements SubAction {
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
