package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.ExceptionEvent;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    private Game game;
    private Field field;
    private Move move=new Move();
    Worker w1=new Worker();
    Worker w2=new Worker();
    Worker w3 = new Worker();
    Worker w4 = new Worker();
    Square [][] squares;
    Player p;


    @BeforeEach
    void setup(){
        Controller controller = new Controller();
        game = controller.getGame();
        field = game.getField();
        squares=field.getSquares();
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.BLACK);
        w4.setC(Color.WHITE);
        p= new Player("john",Color.BLACK);
        game.setCurrentPlayer(p);
        VirtualView view = new VirtualView();
        view.setOwner(p);
        game.setCurrentView(view);
        Player p2=new Player("provaa",Color.WHITE);
        p.setWorkers(w1);
        p2.setWorkers(w2);
        p.setWorkers(w3);
        p2.setWorkers(w4);
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
        game.startMytimer();
    }

    @AfterEach
    public void tearDown()
    {
        move.clearList();
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
        move.isInterationNeeded();
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

    @Test //in the specific: the constraints are that the worker cannot move up && the worker cannot move because of a square not available (line 120)
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
        move.clearList();
        w1.setSquareNotAvailable(squares[1][1]);
        assertFalse(move.isUsable(game));
       // move.isUsable(game);
        //System.out.println(squares[1][1]);
        //System.out.println(move.getAvailableSquare());
    }

    @Test
    void VictoryAfterMovement(){
        game.setTargetInUse(w1);
        squares[1][1].setLevel(2);
        squares[2][2].setLevel(3);
        squares[2][2].removeWorker();
        move.isUsable(game);
        game.setTargetSelected(squares[2][2]);
        move.use(game);
        assertTrue(game.getWinner().equals(p));
    }



}
