package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LevelFilterTest {

    private List <Integer> list=new ArrayList<>();
    private LevelFilter levelFilter;
    private Field field;
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);


    @Test
    public void levelFilterTest(){
        list.add(1);
        list.add(2);
        game=new Game ();
        Player p= new Player("john");
        game.setCurrentPlayer(p);
        God g=new God();
        p.setGod(g);
        levelFilter=new LevelFilter(list, true);
        levelFilter.use(w1, w2, game);
        assertEquals(list, g.getCantDo());

    }



}
