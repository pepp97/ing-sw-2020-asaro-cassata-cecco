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
    Worker w1=new Worker();
    Worker w2=new Worker();


    @Test
    void levelFilterTest(){
        list.add(1);
        list.add(2);
        game=new Game ();
        Player p= new Player("john",Color.BLACK);
        game.setCurrentPlayer(p);
        God g=new God();
        p.setGod(g);
        levelFilter=new LevelFilter(list);
        game.setTargetSelected(w1);
        game.setTargetInUse(w2);
        levelFilter.use(game);
        assertEquals(list, g.getCantDo());

    }



}
