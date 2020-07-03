package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class askUserTest {

    private AskUser askUser=new AskUser();
    private Controller controller=new Controller();
    private Game game=controller.getGame();
    private Player p1=new Player("peppe", Color.BROWN);

    @Test
    void test(){
        game.setMaxRetries(10000);
        game.setCurrentPlayer(p1);
        game.setCurrentView(new VirtualView());
        assertFalse(controller.isGoOn());
        askUser.isUsable(game);
        assertTrue(controller.isGoOn());
        askUser.use(game);
        assertFalse(controller.isGoOn());

    }

}
