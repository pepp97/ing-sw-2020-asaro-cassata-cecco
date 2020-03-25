package it.polimi.ingsw.model;

public class SwapTarget implements SubAction {



    /**
     * @param game
     */

    @Override
    public void use(Game game) {
        Target temp = game.getTargetInUse();
        game.setTargetInUse(game.getTargetSelected());
        game.setTargetSelected(temp);

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
