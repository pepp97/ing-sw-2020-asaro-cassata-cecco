package it.polimi.ingsw.model;

import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePositionTest {

    private ChangePosition changePosition=new ChangePosition();
    private Game game=new Game();
    private Field field=game.getField();
    Worker w1=new Worker();
    Worker w2=new Worker();
    Worker w3=new Worker();
    Square [][] squares=field.getSquares();
    Player p1=new Player("prova", Color.BLACK);
    Player p2=new Player("provaa",Color.WHITE);


    @BeforeEach
    void setup(){
        p1.setWorkers(w1);
        p2.setWorkers(w2);
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
        changePosition.getAvailableSquare().clear();
        w1.setCanBeMoved(true);
        w2.setCanBeMoved(true);
    }


    @Test
    void usabilityCPTest_correct(){
        game.setTargetInUse(w1);
        assertTrue(changePosition.isUsable(game));
        //System.out.println(move.getAvailableSquare());
    }

    @Test
    void usabilityCPTest_notCorrect(){
        game.setTargetInUse(w2);
        squares[1][1].setLevel(2);
        System.out.println(changePosition.getAvailableSquare());
        System.out.println(squares[1][1]);
        assertFalse(changePosition.isUsable(game));
        // System.out.println(move.getAvailableSquare());
    }


    @Test
    void CPTestOk () {
        int prova = w1.getHistoryPos().size();
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][0]);
        changePosition.isUsable(game);
        changePosition.use(game);
        assertEquals(prova + 1, w1.getHistoryPos().size());
        assertFalse(squares[1][1].getWorker() == w1);
        assertTrue(squares[0][0].getWorker() == w1);
    }


    @Test //worker could move but not in the square selected
    void CPNotAvailable(){
        game.setTargetInUse(w1);
        // System.out.println(game.getTargetInUse());
        //System.out.println(move.isUsable(game));
        game.setTargetSelected(squares[4][4]);
        Square oldSquare=game.getTargetInUse().getSquare();
        changePosition.use(game);
        assertEquals(oldSquare,game.getTargetInUse().getSquare());
    }

    @Test
    void CPNotAvailableBecauseOfConstraint(){
        w1.setCanMoveUp(false);
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][0]);
        changePosition.isUsable(game);
        changePosition.use(game);
        squares[0][1].setLevel(2);
        squares[1][0].setLevel(2);
        changePosition.isUsable(game);
        System.out.println(changePosition.getAvailableSquare());
        //squares[0][0].
        System.out.println(squares[1][1]);
        assertFalse(changePosition.isUsable(game));
        w1.setCanMoveUp(true);
        assertTrue(changePosition.isUsable(game));
        changePosition.getAvailableSquare().clear();
        w1.setSquareNotAvailable(squares[1][1]);
        assertFalse(changePosition.isUsable(game));
        // move.isUsable(game);
        //System.out.println(squares[1][1]);
        //System.out.println(move.getAvailableSquare());
    }

    @Test
    void CPtestInBusySquare(){
        int prova = w1.getHistoryPos().size();
        System.out.println(prova);
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[2][2]);
        assertTrue(squares[2][2].getWorker() == w2);
        changePosition.isUsable(game);
        System.out.println(changePosition.getAvailableSquare());
        System.out.println(squares[2][2].getLevel());
        System.out.println(squares[1][1].getLevel());
        System.out.println(w1.getCanMoveUp());
        System.out.println(squares[2][2]);
        changePosition.use(game);
        System.out.println(w1.getHistoryPos().size());
        assertEquals(prova + 1, w1.getHistoryPos().size());
        assertFalse(squares[1][1].getWorker() == w1);
        assertTrue(squares[2][2].getWorker() == w1);
    }

}
