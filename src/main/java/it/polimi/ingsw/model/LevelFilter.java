package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to che
 *
 * @author Salvatore Cassata
 */

public class LevelFilter implements SubAction {

    private List<Integer> cantDo = new ArrayList<>();

    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }
    public LevelFilter(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    /**
     * @param game instance
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
     * @param game instance
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
