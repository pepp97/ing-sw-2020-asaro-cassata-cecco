package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    private Game g;
    private Controller c;
    private VirtualView v;
    private Player p1, p2, p3;
    private Worker a,b;

    @BeforeEach
    void setup() {
        c = new Controller();
        g = new Game(c);
        v = new VirtualView();
        p1 = new Player("Rick", Color.BLACK);
        p2 = new Player("Salvo", Color.WHITE);
        p3 = new Player("Peppe", Color.BROWN);

        a= new Worker();
        b= new Worker();


    }

    @Test
    void nameIsAlreadyPresent() {
        g.login(p1.getUsername(),p1.getColor(),v);
        assertFalse(g.nicknameAvailable(p1.getUsername()));
    }

    @Test
    void colorIsAlreadyPresent() {
        g.login(p1.getUsername(),p1.getColor(),v);
        assertFalse(g.colorAvailable(p1.getColor()));
    }

    @Test
    void gameAlreadyStarted() {
        g.login(p1.getUsername(),p1.getColor(),v);
        g.selectNplayer(2, v);
        g.login(p3.getUsername(),p3.getColor(),v);
        assertTrue(g.gameAlreadyStarted());
    }

    @Test
    void login(){
        g.login(p1.getUsername(),p1.getColor(),v);
        g.login(p3.getUsername(),p3.getColor(),v);

        g.selectNplayer(3, v);
        assertTrue(g.nicknameAvailable(p2.getUsername()));
        g.login(p2.getUsername(), p2.getColor(), v);
        assertTrue(g.getPlayerList().contains(p2));
    }

    @Test
    void removePlayerInList(){
        g.login(p1.getUsername(),p1.getColor(),v);
        g.selectNplayer(2, v);
        g.login(p3.getUsername(),p3.getColor(),v);

        p1.setWorkers(a);
        p1.setWorkers(b);
        assertTrue(g.getPlayerList().contains(p1));
        g.removePlayerInList(p1);
        assertFalse(g.getPlayerList().contains(p1));
    }

    @Test
    void setUsableGod(){
        g.login(p1.getUsername(),p1.getColor(),v);
        g.selectNplayer(2, v);
        g.login(p3.getUsername(),p3.getColor(),v);
        List<String> god= new ArrayList<>();
        List<Boolean> b= new ArrayList<>();
        god.add("Apollo");
        god.add("Athena");
        g.setUsableGod(god);
        for (String s : g.getNames()) {
            for(String j: god){
                if(s.equals(j)){
                    b.add(true);
                }
            }
        }
        for(int i=0;i<2;i++){
            assertTrue(b.get(i));
        }
    }

    @Test
    void selectNplayer(){
        int size=2;
        g.selectNplayer(size, v);
        assertTrue(g.getNumplayer()==size);
        assertTrue(g.getCurrentView()==v);
    }

    @Test
    void setPlayerGod(){
        g.login(p1.getUsername(),p1.getColor(),v);
        g.selectNplayer(2, v);
        g.login(p3.getUsername(),p3.getColor(),v);

        List<String> god= new ArrayList<>();
        god.add("Apollo");
        god.add("Athena");
        g.setUsableGod(god);
        g.setPlayerGod("Apollo",v);
        assertTrue(v.getOwner().getGod().getName().equals("Apollo"));
    }


     @Test
    void setInitialPosition(){
         g.login(p1.getUsername(),p1.getColor(),v);
         g.selectNplayer(2, v);
         g.login(p3.getUsername(),p3.getColor(),v);

         g.setInitialPosition(0,0,v);
         assertTrue(v.getOwner().getWorkers().size()==1);
         g.setInitialPosition(0,1,v);
         assertTrue(v.getOwner().getWorkers().size()==2);
     }


}
