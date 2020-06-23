package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to che
 *
 * @author Salvatore Cassata
 */

public class LevelFilter implements SubAction {

    /**
     * it is the list of level to filter
     */

    private List<Integer> cantDo = new ArrayList<>();

    /**
     * it is true if the subaction need interation of the player, false otherwise
     */

    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }
    public LevelFilter(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    /**
     * this method is called to execute the effect
     * @param game instance of the game
     */
    @Override
    public void use(Game game) {

        game.getCurrentPlayer().getGod().setCantDo(cantDo);
        Worker w= (Worker) game.getTargetInUse();

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
    // faccio il controllo se può costruire così setto il livello altrimenti non lo setto, chiedersi se serve fare ciò
    //sostituire con return true?
    @Override
    public boolean isUsable(Game game) {

        game.getController().setGoOn(true);
        return true;
    }
}
