package it.polimi.ingsw.model;

import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheyDontMoveUpTest {


    private Move move=new Move();
    private Game game=new Game();
    private Field field=game.getField();
    private TheyDontMoveUp theyDontMoveUp=new TheyDontMoveUp();
    private Player p=new Player("nick",Color.BLACK);
    private Player p2=new Player("john",Color.BROWN);
    private Worker w1=new Worker();
   private Worker w2=new Worker();
    private Square [][] squares=field.getSquares();



    @BeforeEach
        void setUp(){
        game.setPlayerInList(p);
        game.setPlayerInList(p2);
        game.setCurrentView(new VirtualView());
        p.setWorkers(w1);
        p2.setWorkers(w2);
        squares[1][1].setLevel(1);
        squares[0][0].setWorker(w1);
    }

    @AfterEach
    void tearDown(){
        game.removePlayerInList(p);
        game.removePlayerInList(p2);
        squares[1][1].removeWorker();
    }

    @Test
    void theyDontMoveUpTestOK(){
        game.setTargetSelected(squares[1][1]);
        game.setTargetInUse(w1);
        move.isUsable(game);
        move.use(game);
        theyDontMoveUp.isUsable(game);
        theyDontMoveUp.use(game);
        assertFalse(w2.getCanMoveUp());
    }

    @Test
    void theyDontMoveUpTestKO(){
        game.setTargetSelected(squares[1][1]);
        squares[1][1].setLevel(0);
        game.setTargetInUse(w1);
        move.isUsable(game);
        move.use(game);
        assertFalse(theyDontMoveUp.isUsable(game));
        assertTrue(w2.getCanMoveUp());
    }
}
