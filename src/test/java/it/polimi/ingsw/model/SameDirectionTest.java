package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SameDirectionTest {



    private Game game;
    private Field field;
    Worker w1=new Worker();
    Worker w2=new Worker();
    Worker w3=new Worker();
    Worker w4=new Worker();
    private MoveInSameDirection moveInSameDirection=new MoveInSameDirection();
    private ChangePosition changePosition=new ChangePosition();
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
        w3.setC(Color.BLACK);
        w4.setC(Color.WHITE);
        game.setCurrentPlayer(p1);
        VirtualView view = new VirtualView();
        view.setOwner(p1);
        game.setCurrentView(view);
        p1.setWorkers(w1);
        p2.setWorkers(w2);
        p1.setWorkers(w3);
        p2.setWorkers(w4);
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(0);
        squares[2][3].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(4);
    }


    @Test
    void UpAndLow(){
        squares[2][2].setWorker(w1);
        squares[3][1].setWorker(w2);
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        game.setTargetSelected(squares[3][1]);
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
        game.setTargetInUse(w1);
        changePosition.isUsable(game);
        game.setTargetSelected(squares[3][1]);
        changePosition.use(game);
        // System.out.println(w1.getSquare().getCoordinateX());
        //System.out.println(w1.getSquare().getCoordinateY());
        System.out.println(w1.getHistoryPos());
        squares[4][0].setWorker(new Worker());
        System.out.println(w1.getHistoryPos().size());
       //assertFalse( moveInSameDirection.isUsable(game));
    }

}
