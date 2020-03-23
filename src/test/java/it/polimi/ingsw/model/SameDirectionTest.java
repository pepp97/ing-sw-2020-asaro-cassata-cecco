package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SameDirectionTest {

    private Field field;
    private Move move=new Move(true);
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);
    private MoveInSameDirection moveInSameDirection=new MoveInSameDirection(false);

    @Test
    public void testMoveInSameDirection(){
        field=game.getField();
        Square [][] squares=field.getSquares();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(0);
        squares[2][2].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(0);
        squares[2][2].setWorker(w2);
        move.use(w1, squares[1][1],game);
        moveInSameDirection.isUsable(w1, game);
        moveInSameDirection.use(w1, w2, game);
        assertEquals(squares[0][0].getWorker(),w2);
        move.use(w1,squares[2][2],game);
        moveInSameDirection.isUsable(w1, game);
        moveInSameDirection.use(w1, w2, game);
        assertEquals(squares[3][3].getWorker(),w2);

    }

}
