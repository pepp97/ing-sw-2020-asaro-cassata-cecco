package it.polimi.ingsw.model;

public interface SubAction {
    /**
     *
     * @param worker
     * @param target
     * @param game
     */
    public void use(Worker worker, Target target, Game game);

    /**
     *
     * @param worker
     * @param game
     * @return
     */
    public Boolean isUsable(Worker worker, Game game);
}
