package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to add a filter that says that the target choose can't be the last
 *  @author  Salvatore Cassata
 */

public class FilterNotSame implements SubAction {
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

        return true;

    }
}
