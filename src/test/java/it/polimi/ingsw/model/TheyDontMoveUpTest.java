package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheyDontMoveUpTest {

    private Field field;
    private Move move=new Move(true);
    private Game game=new Game();
    private TheyDontMoveUp theyDontMoveUp=new TheyDontMoveUp(false);
    private Player p=new Player("nick");
    private Player p2=new Player("john");


    @Test
    public void theyDontMoveUpTest(){
        Worker w1=new Worker(Color.Black);
        Worker w2=new Worker(Color.Brown);
        game.getPlayerList().add(p);
        game.getPlayerList().add(p2);
        game.setCurrentPlayer(p);
        p.add(w1);
        p.add(new Worker(Color.Black));
        p.add(new Worker(Color.Black));
        p.add(new Worker(Color.Black));
        //System.out.println(p.getWorkers().size());
        p2.add(w2);
        field=game.getField();
        Square [][] squares=field.getSquares();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(2);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(0);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(0);
        squares[3][3].setLevel(4);
        squares[2][2].setWorker(w2);
        move.use(w1, squares[1][2],game);
        move.use(w2, squares[3][2],game);
        assertTrue(theyDontMoveUp.isUsable(w1, game));
        assertFalse(theyDontMoveUp.isUsable(w2, game));
        theyDontMoveUp.use(w1, w2, game);
        theyDontMoveUp.use(w1, w2, game);
        assertEquals(false, w2.getCanMoveUp());
        assertEquals(true, w1.getCanMoveUp());
    }
}
