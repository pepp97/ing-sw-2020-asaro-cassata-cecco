package it.polimi.ingsw.model;

public interface SubAction {
    /**
     *
     * @param game
     */
    public void use(Game game) ;

    /**
     *
     * @param game
     * @return
     */
    public Boolean isUsable(Game game);
}
