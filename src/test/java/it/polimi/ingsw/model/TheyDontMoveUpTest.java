package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheyDontMoveUpTest {

    private Field field;
    private Move move=new Move();
    private Game game=new Game();
    private TheyDontMoveUp theyDontMoveUp=new TheyDontMoveUp();
    private Player p=new Player("nick",Color.BLACK);
    private Player p2=new Player("john",Color.BROWN);


    @Test
    void theyDontMoveUpTest(){
        Worker w1=new Worker();
        Worker w2=new Worker();
        game.getPlayerList().add(p);
        game.getPlayerList().add(p2);
        game.setCurrentPlayer(p);
        p.add(w1);
        p.add(new Worker());
        p.add(new Worker());
        p.add(new Worker());
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
        game.setTargetSelected(squares[1][2]);
        game.setTargetInUse(w1);
        move.isUsable(game);

            move.use(game);

        game.setTargetSelected(squares[3][2]);
        game.setTargetInUse(w2);
        move.isUsable(game);

            move.use(game);

        game.setTargetInUse(w1);
        assertTrue(theyDontMoveUp.isUsable(game));
        game.setTargetInUse(w2);
        assertFalse(theyDontMoveUp.isUsable(game));
        game.setTargetInUse(w1);
        game.setTargetSelected(w2);
        theyDontMoveUp.use(game);
        game.setTargetInUse(w2);
        game.setTargetSelected(w1);
        theyDontMoveUp.use(game);
        assertEquals(false, w2.getCanMoveUp());
        assertEquals(true, w1.getCanMoveUp());
    }
}
