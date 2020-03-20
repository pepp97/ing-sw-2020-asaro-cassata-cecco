package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    private int level;
    private Square square;
    private Worker worker;

    @Before
    public void initSquareTest(){
        square=new Square(1, 1);
        square.setLevel(2);
        worker= new Worker(Color.White);
        worker.setActualPos(square);
        square.setWorker(worker);
    }


    @Test
    public void upgradeTest(){
        square.upgrade();
        assertEquals(square.getLevel(), 3);
        assertFalse(square.getLevel()==2);
    }

    @Test
    public void removeWorkerTest(){
        assertTrue(square.getWorker().equals(worker));
        square.removeWorker();
        assertNull(square.getWorker());
    }

    @Test
    public void setAdjacentCellTest(){
        Square s=new Square(0, 1);
        assertFalse(square.getAdjacentSquares().contains(s));
        square.setAdjacentSquares(s);
        assertTrue(square.getAdjacentSquares().contains(s));
    }



}
