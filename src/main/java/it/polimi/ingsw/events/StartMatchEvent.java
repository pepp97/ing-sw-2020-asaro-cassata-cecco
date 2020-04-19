package it.polimi.ingsw.events;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.LinkedHashMap;
import java.util.List;


public class StartMatchEvent implements Event {
    private SquareToJson [][] mappa;
    private LinkedHashMap<String,String> godPlayer;

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

    public SquareToJson[][] getMappa() {
        return mappa;
    }

    public LinkedHashMap<String, String> getGodPlayer() {
        return godPlayer;
    }

    public void setMappa(SquareToJson[][] mappa) {
        this.mappa = mappa;
    }

    public void setGodPlayer(LinkedHashMap<String, String> godPlayer) {
        this.godPlayer = godPlayer;
    }

    @Override
    public void send(Gui view) {

    }

    @Override
    public void send(VirtualView view) {

    }
}