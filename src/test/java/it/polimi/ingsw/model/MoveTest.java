package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.TargetNotAvailableException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Field field;
    private Move move=new Move();
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);


    @Test
    void moveTest(){
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
        game.setTargetInUse(w2);
        assertFalse(move.isUsable( game));
        move.getAvailableSquare().clear();
        game.setTargetInUse(w1);
        assertTrue(move.isUsable(game));
        int prova=w1.getHistoryPos().size();
        game.setTargetSelected(squares [0][0]);
        try {
            move.use(game);
        } catch (TargetNotAvailableException e) {
            e.printStackTrace();
        }
        assertEquals(prova+1, w1.getHistoryPos().size());
        assertFalse(squares[1][1].getWorker()==w1);
        assertTrue(squares[0][0].getWorker()==w1);

    }


}
