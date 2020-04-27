package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.events.UpdateEvent;

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
     *
     * @param game
     */
    @Override
    public void use(Game game) {


        Worker worker = (Worker) game.getTargetInUse();
        int i = 0;


        if (worker.getMandatorySquare() == null) {
            if (worker.getCanBuild()) {
                if (availableSquare.contains(game.getTargetSelected())) {
                    game.getTargetSelected().getSquare().upgrade();
                    game.getCurrentPlayer().setHasBuilt(true);
                    game.getCurrentPlayer().getGod().getCantDo().clear();
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
                } else
                    new ExceptionEvent("you can't build here!");
            } else new ExceptionEvent("you can't build");
            //generazione pacchetto->creazione evento
        } else {
            worker.getMandatorySquare().upgrade();
            worker.setMandatorySquare(null);
        }


    }

    /**
     * this method control if the workers choosed can build
     *
     * @param game
     * @return if true the worker can build
     */

    @Override
    public Boolean isUsable(Game game) {


        Boolean result = false;
        List<Integer> cantDo = game.getCurrentPlayer().getGod().getCantDo();
        Worker worker = (Worker) game.getTargetInUse();

        if(worker.getMandatorySquare() == null){
            for (Square s : worker.getSquare().getAdjacentSquares())
                if (s.getWorker() == null && s.getLevel() != 4 && !(cantDo.contains(s.getLevel() + 1)) && s != worker.getSquareNotAvailable()) {
                    availableSquare.add(s);
                    result = true; }
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


                    ChooseTarget chooseTarget = new ChooseTarget("Select your square to upgrade", availableSquares,map);
                    game.notifyObservers(chooseTarget);


                }


            if (availableSquare.size() == 0)
                result = false;

            worker.setCanBuild(result);
        game.getController().setGoOn(true);
            return result;
            }


}
