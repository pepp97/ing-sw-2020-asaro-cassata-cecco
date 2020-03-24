package it.polimi.ingsw.model;

/**
 * represent the subaction that have the role to comunicate with the user
 * @author Salvatore Cassata
 */
public class AskUser implements SubAction {
    @Override
    public void use(Game game) {

        // comunicare con il giocatore

    }
/*
 * this method is always true because it don't need to control nothing
 */
    @Override
    public Boolean isUsable(Game game) {
        return Boolean.TRUE;
    }
}
