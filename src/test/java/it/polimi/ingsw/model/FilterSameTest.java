package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterSameTest {

    private Game game;
    private Field field;
    private FilterSame filterSame=new FilterSame();
    private Player p = new Player("nick", Color.BLACK);
    private Worker w1 = new Worker();
    private Worker w2 = new Worker();
    private Square[][] squares;
    private Build build=new Build();


    @BeforeEach
    void setUp() {
        Controller controller = new Controller();
        game = controller.getGame();
        field = game.getField();
        squares = field.getSquares();
        List <EffectRoutine> list=new ArrayList<>();
        list.add(new EffectRoutine("", true));
        God g=new God("", "", "", list);
        g.setCantDo(new ArrayList<>());
        p.setGod(g);
        w1.setC(Color.BLACK);
        w2.setC(Color.BLACK);
        game.setCurrentPlayer(p);
        VirtualView view = new VirtualView();
        view.setOwner(p);
        game.setCurrentView(view);
        p.setWorkers(w1);
        p.setWorkers(w1);
        game.setPlayerInList(p);
        game.setCurrentView(view);
        squares[1][1].setLevel(1);
        squares[0][0].setWorker(w1);
        squares[0][1].setWorker(w2);
        game.startMytimer();
    }

    @AfterEach
    void tearDown() {
        game.removePlayerInList(p);
        squares[1][1].removeWorker();
    }

    @Test
    void FilterSameTestOK() {
        game.setTargetSelected(squares[1][1]);
        game.setTargetInUse(w1);
        int level = squares[1][1].getLevel();
        build.isUsable(game);
        build.use(game);
        filterSame.isUsable(game);
        filterSame.use(game);
        assertTrue(level+1==squares[1][1].getLevel());
        assertTrue(w1.getMandatorySquare().equals(squares[1][1]));
        build.isUsable(game);
        build.use(game);
        assertTrue(level+2==squares[1][1].getLevel());
    }


    @Test
    void FilterSameTestKO() {
        game.setTargetSelected(squares[1][1]);
        game.setTargetInUse(w2);
        build.isUsable(game);
        build.use(game);
        filterSame.isUsable(game);
        filterSame.use(game);
        assertTrue(w2.getMandatorySquare().equals(squares[1][1]));
        assertTrue(w1.getMandatorySquare()==null);
    }
}
