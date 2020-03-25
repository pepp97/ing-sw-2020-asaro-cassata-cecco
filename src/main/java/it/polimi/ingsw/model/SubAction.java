package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.TargetNotAvailableException;

public interface SubAction {
    /**
     *
     * @param game
     */
    public void use(Game game) throws TargetNotAvailableException;

    /**
     *
     * @param game
     * @return
     */
    public Boolean isUsable(Game game);
}
