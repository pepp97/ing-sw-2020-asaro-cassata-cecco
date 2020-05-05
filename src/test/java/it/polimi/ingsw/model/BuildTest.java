package it.polimi.ingsw.model;


import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



public class BuildTest {

    private Build build=new Build();
    private Game game=new Game(new Controller());
    private Field field=game.getField();
    Worker w1=new Worker();
    Worker w2=new Worker();
    Worker w3=new Worker();
    Square [][] squares=field.getSquares();

    @BeforeEach
    void setUp(){
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.BROWN);
        Player p= new Player("john",Color.BLACK);
        game.setCurrentPlayer(p);
        VirtualView view = new VirtualView();
        view.setOwner(p);
        game.setCurrentView(view);
        List<EffectRoutine> list=new ArrayList<>();
        list.add(new EffectRoutine("prova",false));
        God g=new God("prova","prova","prova",list);
        p.setGod(g);
        List<Integer> cantdo=new ArrayList<>();
        cantdo.add(2);
        g.setCantDo(cantdo);
        field=game.getField();
        squares[1][1].setLevel(1);
        squares[1][2].setLevel(4);
        squares[1][3].setLevel(4);
        squares[2][1].setLevel(4);
        squares[2][2].setLevel(3);
        squares[1][1].setWorker(w1);
        squares[2][3].setLevel(4);
        squares[3][1].setLevel(4);
        squares[3][2].setLevel(4);
        squares[3][3].setLevel(2);
        squares[2][2].setWorker(w2);
        squares[0][0].setWorker(w3);
        squares[1][0].setLevel(1);
        squares[0][1].setLevel(4);
    }

    @AfterEach
    void reset(){
        squares[1][1].removeWorker();
        squares[1][2].removeWorker();
        squares[1][3].removeWorker();
        squares[2][1].removeWorker();
        squares[2][2].removeWorker();
        squares[2][3].removeWorker();
        squares[3][1].removeWorker();
        squares[3][2].removeWorker();
        squares[3][3].removeWorker();
        build.getAvailableSquare().clear();
        w1.setCanBuild(true);
        w2.setCanBuild(true);
        w3.setCanBuild(true);
    }


    @Test
    void usabilityBuildTest_correct(){
        game.setTargetInUse(w1);
        assertTrue(build.isUsable(game));
        //System.out.println(build.getAvailableSquare());
    }

    @Test
    void usabilityBuildTest_notCorrect(){
        game.setTargetInUse(w2);
        build.isUsable(game);
        System.out.println(build.getAvailableSquare());
        w2.setSquareNotAvailable(squares[3][3]);
      //  System.out.println(squares[1][1]);
        assertFalse(build.isUsable(game));
    }

    @Test
    void usabilityUnderConstraint(){
        game.setTargetInUse(w3);
        //build.isUsable(game);
       // System.out.println(build.getAvailableSquare());
        assertFalse(build.isUsable(game));
    }



    @Test
    void buildTest_correct(){
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][2]);
        build.isUsable(game);
        int level=game.getTargetSelected().getSquare().getLevel();
        build.use(game);
        assertTrue(level+1==squares[0][2].getLevel());
    }

    @Test
    void buildTest_KO(){
        game.setTargetInUse(w1);
        game.setTargetSelected(squares[0][4]);
        build.isUsable(game);
        int level=game.getTargetSelected().getSquare().getLevel();
        build.use(game);
        assertFalse(level+1==squares[0][4].getLevel());
    }

    @Test
    void buildTestMandatorySquareBuildCorrectly(){
        game.setTargetInUse(w1);
        w1.setMandatorySquare(squares[0][2]);
        build.isUsable(game);
        int level=w1.getMandatorySquare().getLevel();
        build.use(game);
        assertTrue(level+1==squares[0][2].getLevel());
    }

    @Test
    void buildTestMandatorySquareNotBuild(){
        game.setTargetInUse(w1);
        w1.setMandatorySquare(squares[1][2]);
        build.isUsable(game);
        int level=w1.getMandatorySquare().getLevel();
        build.use(game);
        assertFalse(level+1==squares[1][2].getLevel());
    }



}
