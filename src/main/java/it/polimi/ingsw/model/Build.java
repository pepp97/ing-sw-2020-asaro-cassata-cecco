package it.polimi.ingsw.model;

import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.exceptions.TargetNotAvailableException;

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
     * @param game
     *
     */
    @Override
    public void use(Game game) throws TargetNotAvailableException {

        ChooseTarget chooseTarget=new ChooseTarget("Select your square to upgrade",availableSquare);
        game.notifyObservers(chooseTarget);

        int i=0;

        while(game.getTargetSelected()==null)
            i++;

        if(availableSquare.contains(game.getTargetSelected())){
            game.getTargetSelected().getSquare().upgrade();
            game.getCurrentPlayer().getGod().getCantDo().clear();
            availableSquare.clear();
        }
        else
            throw new TargetNotAvailableException();

        //generazione pacchetto->creazione evento


    }

    /**
     * this method control if the workers choosed can build
     * @param game
     * @return if true the worker can build
     */

    @Override
    public Boolean isUsable(Game game) {


      Boolean result = false;
      List<Integer> cantDo = game.getCurrentPlayer().getGod().getCantDo();
      Worker worker = (Worker) game.getTargetInUse();


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getWorker()==null && s.getLevel()!=4 && !(cantDo.contains(s.getLevel())) && s != worker.getSquareNotAvailable()) {
                availableSquare.add(s);
                result = true;
            }

        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());
        if (availableSquare.size() == 0)
            result = false;

        worker.setCanBuild(result);

      return result;
    }
}
