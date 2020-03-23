package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Field field;
    private Move move=new Move(true);
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);


    @Test
    public void moveTest(){
        field=game.getField();
        Square [][] squares=field.getSquares();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(0);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(4);
        squares[2][2].setWorker(w2);
        assertFalse(move.isUsable(w2, game));
        assertTrue(move.isUsable(w1, game));
        int prova=w1.getHistoryPos().size();
        move.use(w1, squares[0][0],game);
        assertEquals(prova+1, w1.getHistoryPos().size());
        assertFalse(squares[1][1].getWorker()==w1);
        assertTrue(squares[0][0].getWorker()==w1);

    }


}
