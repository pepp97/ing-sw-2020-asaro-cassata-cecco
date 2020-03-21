package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to build a new Level
 *  @author  Salvatore Cassata
 */

public class Build implements SubAction {

    public List<Square> getAvailableSquare() {
        return availableSquare;
    }

    private List<Square> availableSquare = new ArrayList<>();

    /**
     * This method is called when a player say to build
     * @param worker is the one choosed by the Player
     * @param target
     * @param game
     *
     */
    @Override
    public void use( Worker worker, Target target, Game game) {
        if(worker.isCanBuild()){
            target.getSquare().upgrade();
            game.getCurrentPlayer().getGod().getCantDo().clear();
        }

    }

    /**
     * this method control if the workers choosed can build
     * @param worker is the one choosed by the Player
     * @param game
     * @return if true the worker can build
     */

    @Override
    public Boolean isUsable(Worker worker, Game game) {


      Boolean result = false;
      List<Integer> cantDo = game.getCurrentPlayer().getGod().getCantDo();

        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getLevel()!=4 && !(cantDo.contains(s.getLevel()))) {
                availableSquare.add(s);
                result = true;
            }

        worker.setCanBuild(result);

      return result;
    }
}
