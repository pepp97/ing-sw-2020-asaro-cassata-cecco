package it.polimi.ingsw.model;

import it.polimi.ingsw.exceptions.TargetNotAvailableException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SameDirectionTest {

    private Field field;
    private Move move=new Move();
    private Game game=new Game();
    Worker w1=new Worker(Color.Black);
    Worker w2=new Worker(Color.Brown);
    private MoveInSameDirection moveInSameDirection=new MoveInSameDirection();

    @Test
    void testMoveInSameDirection(){
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
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[1][1]);
        move.isUsable(game);
        try {
            move.use(game);
        } catch (TargetNotAvailableException e) {
            e.printStackTrace();
        }
        game.setTargetSelected(w2);
        moveInSameDirection.isUsable(game);
        moveInSameDirection.use(game);
        assertEquals(squares[0][0].getWorker(),w2);
        game.setTargetSelected(squares[2][2]);
        move.isUsable(game);
        try {
            move.use(game);
        } catch (TargetNotAvailableException e) {
            e.printStackTrace();
        }
        moveInSameDirection.isUsable(game);
        game.setTargetSelected(w2);
        moveInSameDirection.use(game);
        assertEquals(squares[3][3].getWorker(),w2);

    }

}
