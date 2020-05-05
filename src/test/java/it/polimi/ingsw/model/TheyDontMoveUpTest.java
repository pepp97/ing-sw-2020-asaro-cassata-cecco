package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TheyDontMoveUpTest {


    private Move move=new Move();
    private Game game;
    private Field field;
    private TheyDontMoveUp theyDontMoveUp=new TheyDontMoveUp();
    private Player p=new Player("nick",Color.BLACK);
    private Player p2=new Player("john",Color.BROWN);
    private Worker w1=new Worker();
   private Worker w2=new Worker();
    private Worker w3=new Worker();
    private Worker w4=new Worker();
    private Square [][] squares;



    @BeforeEach
        void setUp(){
        Controller controller = new Controller();
        game = controller.getGame();
        field = game.getField();
        squares=field.getSquares();
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.BLACK);
        w4.setC(Color.WHITE);
        game.setCurrentPlayer(p);
        VirtualView view = new VirtualView();
        view.setOwner(p);
        game.setCurrentView(view);
        p.setWorkers(w1);
        p2.setWorkers(w2);
        p.setWorkers(w3);
        p2.setWorkers(w4);
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

   /* @Test
    void theyDontMoveUpTestKO(){
        theyDontMoveUp.isUsable(game);
        theyDontMoveUp.use(game);
        game.setTargetInUse(w1);
        squares[1][1].setLevel(0);
        move.isUsable(game);
        game.setTargetSelected(squares[1][1]);
        move.use(game);
        assertFalse(move.isUsable(game));
        assertTrue(w2.getCanMoveUp());
    }*/
}
