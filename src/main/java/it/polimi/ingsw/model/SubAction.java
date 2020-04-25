package it.polimi.ingsw.model;

public interface SubAction {
    /**
     * @param game instance
     */
    public void use(Game game);


    public Boolean isUsable(Game game);
}
