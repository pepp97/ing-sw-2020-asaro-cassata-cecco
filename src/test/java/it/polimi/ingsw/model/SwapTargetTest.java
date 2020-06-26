package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwapTargetTest {
    private SwapTarget swapTarget=new SwapTarget();
    private Game game=new Game(new Controller());

    @BeforeEach
    void setup() {
        Controller controller = new Controller();
        game = controller.getGame();
        Player p = new Player("john", Color.BLACK);
        game.setCurrentPlayer(p);
        VirtualView view = new VirtualView();
        view.setOwner(p);
        game.setCurrentView(view);
        Player p2 = new Player("provaa", Color.WHITE);
        game.startMytimer();
    }

    @Test
     void swapTargetTest(){
        Target a= new Worker();
        Target b=new Worker();
        game.setTargetInUse(a);
        game.setTargetSelected(b);
        assertEquals(a, game.getTargetInUse());
        assertEquals(b, game.getTargetSelected());
        swapTarget.use(game);
        assertEquals(b, game.getTargetInUse());
        assertEquals(a, game.getTargetSelected());
        swapTarget.isInterationNeeded();
    }
}
