package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to swap the target
 */
public class SwapTarget implements SubAction {

    @Override
    public void use(Game game) {
        Target temp = game.getTargetInUse();
        game.setTargetInUse(game.getTargetSelected());
        game.setTargetSelected(temp);

    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    @Override
    public Boolean isUsable(Game game) {
        return true;
    }

}
