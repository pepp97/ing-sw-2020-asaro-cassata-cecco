package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.LinkedHashMap;

/**
 * This Class is the event sends to players to start the match
 */


public class StartMatchEvent implements Event {
    private SquareToJson [][] mappa;
    private LinkedHashMap<String,String> godPlayer;

    /**
     *Default constructor
     * @param listPlayer is the HashMap that link player with your god
     */

    public StartMatchEvent(LinkedHashMap<String,String> listPlayer) {
        mappa=new SquareToJson[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                mappa[i][j]=new SquareToJson(0,"",i,j);
            }
        }
        godPlayer=listPlayer;
        /*for(Player p:listPlayer){
            godPlayer.put(p.getUsername(),p.getGod().getName());
        }*/
    }

    /**
     * @return the array bi-dimensional that contain the squares information
     */

    public SquareToJson[][] getMappa() {
        return mappa;
    }

    /**
     * @return the HashMap that link player with your god
     */

    public LinkedHashMap<String, String> getGodPlayer() {
        return godPlayer;
    }

    public void setMappa(SquareToJson[][] mappa) {
        this.mappa = mappa;
    }

    public void setGodPlayer(LinkedHashMap<String, String> godPlayer) {
        this.godPlayer = godPlayer;
    }
    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(Gui view) {
        view.update(this);
    }
    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(VirtualView view) {
        view.update(this);
    }
}