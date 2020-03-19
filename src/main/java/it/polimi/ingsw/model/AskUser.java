package it.polimi.ingsw.model;

/**
 * represent the subaction that have the role to comunicate with the user
 * @author Salvatore Cassata
 */
public class AskUser implements SubAction {
    @Override
    public void use(Worker worker, Target target, Game game) {

        // comunicare con il giocatore

    }
/*
 * this method is always true because it don't need to control nothing
 */
    @Override
    public Boolean isUsable(Worker worker, Game game) {
        return Boolean.TRUE;
    }
}
