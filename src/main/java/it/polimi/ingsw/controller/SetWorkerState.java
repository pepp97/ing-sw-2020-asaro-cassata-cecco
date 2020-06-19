package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.SetWorkerEvent;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.view.VirtualView;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the State called when a Player have to set worker position
 */

public class SetWorkerState implements TurnState {
    private List <Square> availableSquare=new ArrayList<>();
    private Controller controller;

    /**
     * this method is used to execute the state
     * @param controller is the main controller
     */


    @Override
    public void executeState(Controller controller) {
        availableSquare.clear();
        this.controller=controller;
        //discorso lista virtualview in game
        // for (int i = 0; i < controller.getGame().getObservers().size(); i++) {
            //VirtualView view=(VirtualView) controller.getGame().getObservers().get(i);
            //if (controller.getGame().getCurrentPlayer().equals(view.getOwner())){
        fillSquare();
        SquareToJson[][]map=new SquareToJson[5][5];
        Square [][]mappa=controller.getGame().getField().getSquares();

        for(int i=0;i<5;i++)
            for(int j=0; j<5;j++)
                if(mappa[i][j].getWorker()!=null)
                    map[i][j]=new SquareToJson(mappa[i][j].getLevel(),mappa[i][j].getWorker().getC().toString(),i,j);
                else map[i][j]=new SquareToJson(mappa[i][j].getLevel(),"",i,j);

        UpdateEvent event=new UpdateEvent(map);
        controller.getGame().notifyObservers(event);


        List <SquareToJson> toJsons=new ArrayList<>();
        for(Square s:availableSquare)
            toJsons.add(new SquareToJson(s.getLevel(), "",s.getCoordinateX(),s.getCoordinateY() ));


        SetWorkerEvent event2=new SetWorkerEvent(toJsons,map);
        controller.getGame().notifyCurrent(event2);

    }
    /**
     * this method is used to go back if a player do a lose don't expected
     */

    @Override
    public void goBack() {

    }
    /**
     * this method is used to check some lose condition
     * @return true if there are some condition to lose, false otherwise
     */

    @Override
    public Boolean tryToEscape() {
        return null;
    }


    //}

    /**
     * this method is called to fill the square available where player can position his worker
     */
    private void fillSquare() {

        Square [][]matrix=controller.getGame().getField().getSquares();

        for(int j=0; j<5;j++)
            for(int i=0; i<5;i++)
                if(matrix[i][j].getWorker()==null)
                    availableSquare.add(matrix[i][j]);

    }


}
