package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that move the player
 *  @author  Salvatore Cassata
 */

public class Move implements SubAction {


    private List<Square> availableSquare = new ArrayList<>();
    private boolean interationNeeded=true;

    public boolean isInterationNeeded() {
        return interationNeeded;
    }


    /**
     * @param game
     */
    @Override
    public void use(Game game) {

        //ChooseTarget chooseTarget=new ChooseTarget("Select where do you want to move",List.copyOf(availableSquare));
        //game.notifyCurrent(chooseTarget);
        game.getController().setGoOn(false);
        int i=0;

        while(game.getTargetSelected()==null)
            i++;

        Worker worker = (Worker) game.getTargetInUse();
        if (worker.getCanBeMoved()) {
        if(availableSquare.contains(game.getTargetSelected())) {


                worker.getSquare().removeWorker();
                //worker.setActualPos(target.getSquare());
                game.getCurrentPlayer().setHasBeenMoved(true);
                game.getTargetSelected().getSquare().setWorker(worker);
                availableSquare.clear();
            Square [][] mappa=game.getField().getSquares();
            SquareToJson [][]map = new SquareToJson[5][5];
            for(int x=0; x<5; x++)
                for(int y=0; y<5; y++)
                    if (mappa[x][y].getWorker()!=null)
                        map[x][y]=new SquareToJson(mappa[x][y].getLevel(),mappa[x][y].getWorker().getC().toString(),mappa[x][y].getCoordinateX(),mappa[x][y].getCoordinateX());
                    else
                        map[x][y]=new SquareToJson(mappa[x][y].getLevel(), "", mappa[x][y].getCoordinateX(), mappa[x][y].getCoordinateY());
            UpdateEvent event=new UpdateEvent(map);
            game.notifyObservers(event);
            }
        else new ExceptionEvent("target not available");
        }

        else new ExceptionEvent("You can't move");

        //creazione evento + sistemare minotauro

    }

    //only for testing purpose
    public List<Square> getAvailableSquare() {
        return availableSquare;
    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {
        availableSquare.clear();

        Boolean result = false;
        Worker worker = (Worker) game.getTargetInUse();


        for(Square s: worker.getSquare().getAdjacentSquares())
            if(s.getWorker() == null && s.getLevel()<4 &&((worker.getCanMoveUp()&& worker.getSquare().getLevel()==s.getLevel()-1) || (worker.getSquare().getLevel()>s.getLevel()-1))) {
                availableSquare.add(s);
                result = true;
                List <SquareToJson> availableSquares= new ArrayList<>();
                for(Square s1 : availableSquare)
                    availableSquares.add(new SquareToJson(s1.getLevel(), "", s1.getCoordinateX(), s1.getCoordinateY()));

                SquareToJson[][]map=new SquareToJson[5][5];
                Square [][]mappa=game.getField().getSquares();


                for(int i=0;i<5;i++)
                    for(int j=0; j<5;j++)
                        if(mappa[i][j].getWorker()!=null)
                            map[i][j]=new SquareToJson(mappa[i][j].getLevel(),mappa[i][j].getWorker().getC().toString(),i,j);
                        else map[i][j]=new SquareToJson(mappa[i][j].getLevel(),"",i,j);


                ChooseTarget chooseTarget = new ChooseTarget("Where do you want to move?", availableSquares,map);
                game.notifyObservers(chooseTarget);
            }

        if (worker.getSquareNotAvailable() != null)
            availableSquare.remove(worker.getSquareNotAvailable());

        if (availableSquare.size() == 0) {
            if(!game.getCurrentPlayer().isHasBeenMoved())
                game.getCurrentPlayer().setDefeat(true);
            result = false;
        }
        worker.setCanBeMoved(result);

        game.getController().setGoOn(true);
        return result;
    }
}
