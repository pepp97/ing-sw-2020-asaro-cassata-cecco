package it.polimi.ingsw.model;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildTest {

    private Build build=new Build(false);
    private Field field;
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);

    @Test
    public void buildTest(){
        game=new Game ();
        Player p= new Player("john");
        game.setCurrentPlayer(p);
        God g=new God();
        p.setGod(g);
        List<Integer>cantdo=new ArrayList<>();
        cantdo.add(2);
        g.setCantDo(cantdo);
        field=game.getField();
        Square [][] squares=field.getSquares();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(3);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(2);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(2);
        squares[3][3].setLevel(2);
        squares[2][2].setWorker(w2);
        assertFalse(build.isUsable(w2, game));
        assertTrue(build.isUsable(w1,game));
        assertNotEquals(2, squares[1][1].getLevel());
        build.use(w1, squares[1][1],game);
        assertEquals(2, squares[1][1].getLevel());
    }



}
