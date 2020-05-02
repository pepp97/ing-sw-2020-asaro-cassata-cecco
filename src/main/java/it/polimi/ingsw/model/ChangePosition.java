package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to change the position of a Player
 *
 * @author Salvatore Cassata
 */
public class ChangePosition implements SubAction {

    private List<Square> availableSquare = new ArrayList<>();
    private boolean interationNeeded = true;


    public boolean isInterationNeeded() {
        return interationNeeded;
    }

    /**
     * this use change the position of the player and save the new position in the HistoryPos of the worker
     *
     * @param game
     */
    @Override
    public void use(Game game) {

        // ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",availableSquare);
        // game.notifyObservers(chooseTarget);
        game.getController().setGoOn(false);

        int i = 0;


        Worker worker = (Worker) game.getTargetInUse();
        game.getController().setCanSkip(true);

        if (game.getTargetSelected().getSquare().getWorker() != null && ((Worker) game.getTargetInUse()).getMandatorySquare() == null) {
            game.setTargetSelected(game.getTargetSelected().getSquare().getWorker());
            Worker w1 = (Worker) game.getTargetSelected();
            w1.setMandatorySquare(((Worker) game.getTargetInUse()).getHistoryPos().get(0));
            game.getController().setCanSkip(false);
        }

        if (worker.getMandatorySquare() != null) {
            //game.getTargetInUse().getSquare().removeWorker();
            game.getController().setCanSkip(false);
            game.getCurrentPlayer().setHasBeenMoved(true);
            worker.getMandatorySquare().setWorker((Worker) game.getTargetInUse());
            Square[][] mappa = game.getField().getSquares();
            SquareToJson[][] map = new SquareToJson[5][5];
            for (int x = 0; x < 5; x++)
                for (int y = 0; y < 5; y++)
                    if (mappa[x][y].getWorker() != null)
                        map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateX());
                    else
                        map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
            UpdateEvent event = new UpdateEvent(map);
            game.notifyObservers(event);
           // worker.setMandatorySquare(null);
            return;
        }

        if (worker.getCanBeMoved()) {
            if (availableSquare.contains(game.getTargetSelected().getSquare())) {
                game.getTargetInUse().getSquare().removeWorker();
                game.getCurrentPlayer().setHasBeenMoved(true);
                game.getTargetSelected().getSquare().setWorker((Worker) game.getTargetInUse());
                availableSquare.clear();
                Square[][] mappa = game.getField().getSquares();
                SquareToJson[][] map = new SquareToJson[5][5];
                for (int x = 0; x < 5; x++)
                    for (int y = 0; y < 5; y++)
                        if (mappa[x][y].getWorker() != null)
                            map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateX());
                        else
                            map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
                UpdateEvent event = new UpdateEvent(map);
                game.notifyObservers(event);
                // availableSquare.add((worker.getHistoryPos().get(0));
            } else new ExceptionEvent("target not available");
        } else new ExceptionEvent("you can't move");
        //worker.setActualPos(target.getSquare());
        // worker.getHistoryPos().add(target.getSquare());

        //creazione evento+gestione skippable
        //game.getCurrentPlayer().setInQue(false);
    }

    //only for a test purpose
    public List<Square> getAvailableSquare() {
        return availableSquare;
    }

    /**
     * @param game
     * @return if true the worker can change position
     */

    @Override
    public boolean isUsable(Game game) {
//        game.getCurrentPlayer().setInQue(true);
        Boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();
        if (game.getTargetSelected() != null) {
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(true);
            return true;
        }
        // if(game.getTargetSelected().getSquare().getWorker()==null) {
        for (Square s : worker.getSquare().getAdjacentSquares()) {
            if (s.getLevel() < 4 && (s.getWorker() == null || s.getWorker().getC() != worker.getC()) && ((worker.getCanMoveUp() && worker.getSquare().getLevel() == s.getLevel() - 1) || (worker.getSquare().getLevel() > s.getLevel() - 1)))
                availableSquare.add(s);
        }
        result = true;
        List<SquareToJson> availableSquares = new ArrayList<>();
        for (Square s1 : availableSquare)
            availableSquares.add(new SquareToJson(s1.getLevel(), "", s1.getCoordinateX(), s1.getCoordinateY()));

        SquareToJson[][] map = new SquareToJson[5][5];
        Square[][] mappa = game.getField().getSquares();


        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (mappa[i][j].getWorker() != null)
                    map[i][j] = new SquareToJson(mappa[i][j].getLevel(), mappa[i][j].getWorker().getC().toString(), i, j);
                else map[i][j] = new SquareToJson(mappa[i][j].getLevel(), "", i, j);


        ChooseTarget chooseTarget = new ChooseTarget("Where do you want to move?", availableSquares, map);
        UpdateEvent event = new UpdateEvent(map);
        game.notifyObservers(event);
        game.notifyCurrent(chooseTarget);

        //}
        /*else{
            game.getCurrentPlayer().setInQue(false);
            game.getController().setGoOn(true);
            return true;
        }*/
        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());

        if (availableSquare.size() == 0) {
            game.getCurrentPlayer().setDefeat(true);
            result = false;
        }
        worker.setCanBeMoved(result);
        game.getCurrentPlayer().setInQue(true);
        game.getController().setGoOn(true);
        return result;
    }
}