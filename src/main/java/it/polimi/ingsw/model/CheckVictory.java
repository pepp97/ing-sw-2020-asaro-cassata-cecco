package it.polimi.ingsw.model;

/**
 * It is the micro-effect that have the role to check if the player have win
 *  @author  Salvatore Cassata
 */

public class CheckVictory implements  SubAction{
    /**
     * @param worker
     * @param target
     * @param game
     */
    @Override
    public void use(Worker worker, Target target, Game game) {

        //se è salito da livello 3 a livello 4



    }

    /**
     * @param worker
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        // vedere come fare per Pan
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() == 4 && worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() == 3) {
            return true;
            // player have win
        }

        return false;
    }
}
