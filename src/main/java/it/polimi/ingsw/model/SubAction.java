package it.polimi.ingsw.model;

public interface SubAction {

    /**
     * @param game instance
     */
    public void use(Game game);

    boolean isInterationNeeded();
    public boolean isUsable(Game game);
}
