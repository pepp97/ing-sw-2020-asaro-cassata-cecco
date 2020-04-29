package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to che
 *
 * @author Salvatore Cassata
 */

public class LevelFilter implements SubAction {

    private List<Integer> cantDo = new ArrayList<>();

    private boolean interationNeeded=false;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }
    public LevelFilter(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    public List<Integer> getCantDo() {
        return cantDo;
    }


    public void setCantDo(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    /**
     * @param game instance
     */
    @Override
    public void use(Game game) {

        game.getCurrentPlayer().getGod().setCantDo(cantDo);
        game.getCurrentPlayer().setInQue(false);
    }

    /**
     * @param game instance
     * @return a boolean to determine if the effect is usable
     */
    // faccio il controllo se può costruire così setto il livello altrimenti non lo setto, chiedersi se serve fare ciò
    //sostituire con return true?
    @Override
    public boolean isUsable(Game game) {
       /* List<Square> adiacentSquare = worker.getSquare().getAdjacentSquares();
        Boolean result = false;
        int j = 0;
        List<Integer> cantDo = game.getCurrentPlayer().getGod().getCantDo();

        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getLevel()!=4 && !(cantDo.contains(s.getLevel()))) {
                result = true;
                worker.setCanBuild(result);
            }

        return result;*/
        game.getCurrentPlayer().setInQue(true);
        return true;
    }
}
