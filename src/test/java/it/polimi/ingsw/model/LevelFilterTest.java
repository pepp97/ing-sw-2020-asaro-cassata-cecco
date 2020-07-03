package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LevelFilterTest {

    private List <Integer> list=new ArrayList<>();
    private LevelFilter levelFilter;
    private Field field;
    private Game game=new Game(new Controller());
    Worker w1=new Worker();
    Worker w2=new Worker();


    @Test
    void levelFilterTest(){
        list.add(1);
        list.add(2);
        game=new Game (new Controller());
        game.setMaxRetries(10000);
        Player p= new Player("john",Color.BLACK);
        game.setCurrentPlayer(p);
        List<EffectRoutine> listt=new ArrayList<>();
        listt.add(new EffectRoutine("prova",false));
        God g=new God("prova","prova","prova",listt);
        p.setGod(g);
        levelFilter=new LevelFilter(list);
        game.setTargetSelected(w1);
        game.setTargetInUse(w2);
        levelFilter.use(game);
        assertEquals(list, g.getCantDo());
        levelFilter.isInterationNeeded();

    }



}
