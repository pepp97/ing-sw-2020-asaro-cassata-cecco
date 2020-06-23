package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePositionTest {

    private ChangePosition changePosition=new ChangePosition();
    private Game game;
    private Field field;
    Worker w1=new Worker();
    Worker w2=new Worker();
    Worker w3=new Worker();
    Worker w4=new Worker();
    Square [][] squares;
    Player p1=new Player("prova", Color.BLACK);
    Player p2=new Player("provaa",Color.WHITE);


    @BeforeEach
    void setup(){
        Controller controller = new Controller();
        game = controller.getGame();
        field = game.getField();
        squares=field.getSquares();
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.BROWN);
        p1.setWorkers(w1);
        p2.setWorkers(w2);
        p1.setWorkers(w3);
        p2.setWorkers(w4);
        game.setCurrentPlayer(p1);
        VirtualView view = new VirtualView();
        view.setOwner(p1);
        game.setCurrentView(view);

        VirtualView view2 = new VirtualView();
        view2.setOwner(p2);

        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(2);
        squares[2][2].setLevel(0);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(4);
        squares[2][2].setWorker(w2);
        squares[0][3].setLevel(0);
        squares[0][3].setWorker(w3);
        squares[4][4].setLevel(0);
        squares[4][4].setWorker(w4);
        game.startMytimer();
    }

    @AfterEach
    public void tearDown()
    {
        changePosition.clearList();
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

        //System.out.println(squares[1][1]);
        assertFalse(changePosition.isUsable(game));
        // System.out.println(move.getAvailableSquare());
    }


    @Test
    void CPTestOk () {
        int prova = w1.getHistoryPos().size();
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        game.setTargetSelected(squares[0][0]);
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
        changePosition.isUsable(game);
        game.setTargetSelected(squares[2][1]);
        changePosition.use(game);
        assertTrue(squares[1][1].getWorker() == w1);
        assertFalse(squares[2][1].getWorker() == w1);

    }

    @Test
    void CPtestInBusySquare(){
        int prova = w1.getHistoryPos().size();
        System.out.println(prova);
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        game.setTargetSelected(squares[2][2]);
        assertTrue(squares[2][2].getWorker() == w2);
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


    @Test
    void CPtestWithMandatorySquare(){
        game.setTargetInUse(w1);
        w1.setMandatorySquare(squares[2][2]);
        game.setTargetSelected(squares[0][0]);
        changePosition.isUsable(game);
        changePosition.use(game);
        w1.setMandatorySquare(null);
        assertTrue(squares[2][2].equals(w1.getSquare()));
    }

    @Test
    void VictoryAfterMovement(){
        game.setTargetInUse(w1);
        squares[1][1].setLevel(2);
        squares[2][2].setLevel(3);
        changePosition.isUsable(game);
        game.setTargetSelected(squares[2][2]);
        changePosition.use(game);
        assertTrue(game.getWinner().equals(p1));
    }
}
