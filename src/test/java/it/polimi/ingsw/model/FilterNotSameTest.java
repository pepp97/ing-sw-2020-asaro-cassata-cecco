package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterNotSameTest {
    private Game game;
    private Field field;
    private FilterNotSame filterNotSame=new FilterNotSame();
    private Player p = new Player("nick", Color.BLACK);
    private Worker w1 = new Worker();
    private Worker w2 = new Worker();
    private Square[][] squares;
    private Build build=new Build();
    private Move move=new Move();


    @BeforeEach
    void setUp() {
        Controller controller = new Controller();
        game = controller.getGame();
        field = game.getField();
        squares = field.getSquares();
        List<EffectRoutine> list=new ArrayList<>();
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
        squares[1][1].setStart_level(1);
        squares[1][1].setLevel(1);
        squares[0][0].setWorker(w1);
        squares[0][1].setWorker(w2);
    }

    @AfterEach
    void tearDown() {
        game.removePlayerInList(p);
        squares[1][1].removeWorker();
    }

    @Test
    void FilterNotSameTestOKBuild() {
        game.setTargetSelected(squares[1][1]);
        game.setTargetInUse(w1);
        int level = squares[1][1].getLevel();
        build.isUsable(game);
        build.use(game);
        filterNotSame.isUsable(game);
        filterNotSame.use(game);
        assertTrue(level+1==squares[1][1].getLevel());
        assertTrue(w1.getSquareNotAvailable().equals(squares[1][1]));
    }


    @Test
    void FilterNotSameTestOKMove() {
        game.setTargetSelected(squares[1][0]);
        game.setTargetInUse(w1);
        move.isUsable(game);
        move.use(game);
        filterNotSame.isUsable(game);
        filterNotSame.use(game);
        assertTrue(w1.getSquareNotAvailable().equals(squares[0][0]));
    }


    @Test
    void FilterSameTestKO() {
        game.setTargetSelected(squares[1][1]);
        game.setTargetInUse(w2);
        build.isUsable(game);
        build.use(game);
        filterNotSame.isUsable(game);
        filterNotSame.use(game);
        assertTrue(w1.getSquareNotAvailable()==null);
    }
}
