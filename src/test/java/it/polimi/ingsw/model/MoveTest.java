package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Game game=new Game(new Controller());
    private Field field=game.getField();
    private Move move=new Move();
    Worker w1=new Worker();
    Worker w2=new Worker();
    Square [][] squares=field.getSquares();



    @BeforeEach
    void setup(){
        game.setCurrentView(new VirtualView());
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
    }

    @AfterEach
    public void tearDown()
    {
        move.getAvailableSquare().clear();
        w1.setCanBeMoved(true);
        w2.setCanBeMoved(true);
    }


    @Test
    void usabilityMoveTest_correct(){
        game.setTargetInUse(w1);
        assertTrue(move.isUsable(game));
        //System.out.println(move.getAvailableSquare());
    }

    @Test
    void usabilityMoveTest_notCorrect(){
        game.setTargetInUse(w2);
        assertFalse(move.isUsable(game));
       // System.out.println(move.getAvailableSquare());
    }


    @Test
    void moveTestOk () {
        int prova = w1.getHistoryPos().size();
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][0]);
        move.isUsable(game);
        move.use(game);
        assertEquals(prova + 1, w1.getHistoryPos().size());
        assertFalse(squares[1][1].getWorker() == w1);
        assertTrue(squares[0][0].getWorker() == w1);
    }


    @Test //worker could move but not in the square selected
    void movementNotAvailable(){
        game.setTargetInUse(w1);
       // System.out.println(game.getTargetInUse());
        //System.out.println(move.isUsable(game));
        game.setTargetSelected(squares[4][4]);
        Square oldSquare=game.getTargetInUse().getSquare();
        move.use(game);
        assertEquals(oldSquare,game.getTargetInUse().getSquare());
    }

    @Test
    void movementNotAvailableBecauseOfConstraint(){
        w1.setCanMoveUp(false);
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][0]);
        move.isUsable(game);
        move.use(game);
        squares[0][1].setLevel(2);
        squares[1][0].setLevel(2);
        assertFalse(move.isUsable(game));
        w1.setCanMoveUp(true);
        assertTrue(move.isUsable(game));
        move.getAvailableSquare().clear();
        w1.setSquareNotAvailable(squares[1][1]);
        assertFalse(move.isUsable(game));
       // move.isUsable(game);
        //System.out.println(squares[1][1]);
        //System.out.println(move.getAvailableSquare());
    }



}
