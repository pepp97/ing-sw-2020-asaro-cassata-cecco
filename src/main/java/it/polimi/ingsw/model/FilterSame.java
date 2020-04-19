package it.polimi.ingsw.model;

public class FilterSame implements SubAction {
    /**
     * @param game
     */
    @Override
    public void use(Game game) {

        // controllare tutte per build o passare penultima posizione

        Worker worker = (Worker) game.getTargetInUse();

        for(Square s: worker.getSquare().getAdjacentSquares()){
            if(s.getStart_level() != s.getLevel())
                worker.setMandatorySquare(s);
        }
    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {

        return true;

    }
}


