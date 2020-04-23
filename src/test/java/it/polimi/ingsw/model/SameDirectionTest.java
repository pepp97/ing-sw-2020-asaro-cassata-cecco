package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SameDirectionTest {



    private Game game=new Game(new Controller());
    private Field field=game.getField();
    Worker w1=new Worker();
    Worker w2=new Worker();
    private MoveInSameDirection moveInSameDirection=new MoveInSameDirection();
    private ChangePosition changePosition=new ChangePosition();
    Square [][] squares=field.getSquares();
    Player p1=new Player("prova", Color.BLACK);
    Player p2=new Player("provaa",Color.WHITE);

    @BeforeEach
    void setup(){
        p1.setWorkers(w1);
        p2.setWorkers(w2);
    }

    @Test
    void lowAndUp(){
        squares[2][2].setWorker(w1);
        squares[1][3].setWorker(w2);
        game.setTargetSelected(squares[1][3]);
        game.setTargetInUse(w1);
        System.out.println(w1.getHistoryPos());
        changePosition.isUsable(game);
        System.out.println(changePosition.getAvailableSquare());
        System.out.println(squares[1][3]);
        changePosition.use(game);
       // System.out.println(w1.getSquare().getCoordinateX());
        //System.out.println(w1.getSquare().getCoordinateY());
        System.out.println(w1.getHistoryPos());
        moveInSameDirection.isUsable(game);
        moveInSameDirection.use(game);
        assertTrue(squares[0][4].getWorker()==w2);
    }

    @Test
    void UpAndLow(){
        squares[2][2].setWorker(w1);
        squares[3][1].setWorker(w2);
        game.setTargetSelected(squares[3][1]);
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        changePosition.use(game);
        // System.out.println(w1.getSquare().getCoordinateX());
        //System.out.println(w1.getSquare().getCoordinateY());
        System.out.println(w1.getHistoryPos());
        moveInSameDirection.isUsable(game);
        moveInSameDirection.use(game);
        assertTrue(squares[4][0].getWorker()==w2);
    }

    @Test
    void SameDirectionKO(){
        squares[2][2].setWorker(w1);
        squares[3][1].setWorker(w2);
        game.setTargetSelected(squares[3][1]);
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        changePosition.use(game);
        // System.out.println(w1.getSquare().getCoordinateX());
        //System.out.println(w1.getSquare().getCoordinateY());
        System.out.println(w1.getHistoryPos());
        squares[4][0].setWorker(new Worker());
       assertFalse( moveInSameDirection.isUsable(game));
    }

}
