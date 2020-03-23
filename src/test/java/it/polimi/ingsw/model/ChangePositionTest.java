package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePositionTest {

    private ChangePosition changePosition=new ChangePosition(true);
    private Field field;
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);
    Worker w3=new Worker(Color.Brown);

    @Test
    public void changePositionTest(){
        game=new Game ();
        field=game.getField();
        Square [][] squares=field.getSquares();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(2);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(4);
        squares[2][2].setWorker(w2);
        squares[0][4].setWorker(w3);
        squares[0][3].setLevel(2);
        squares[1][3].setLevel(2);
        squares[1][4].setLevel(2);
        assertFalse(changePosition.isUsable(w3,game));
        assertTrue(changePosition.isUsable(w2,game));
        int test=w1.getHistoryPos().size();
        //System.out.println(test);
        changePosition.use(w1, squares[2][2],game);
        //System.out.println(w1.getHistoryPos().size());
        assertEquals(test+1, w1.getHistoryPos().size());
        assertEquals(w1.getSquare(), squares[2][2]);
    }

}
