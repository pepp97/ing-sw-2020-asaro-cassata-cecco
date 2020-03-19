package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to che
 *  @author  Salvatore Cassata
 */

public class LevelFilter implements SubAction {
    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

    }

    /**
     * @param worker
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        return null;
    }
}
