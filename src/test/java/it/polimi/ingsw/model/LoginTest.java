package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private Game g;
    private Controller c;
    private Player p1, p2, p3, p4, p5;

    @BeforeEach
    void setup() {
        c = new Controller();
        g = new Game(c);
        VirtualView v = new VirtualView();

        p1 = new Player("Rick", Color.BLACK);
        p2 = new Player("Salvo", Color.WHITE);
        p3 = new Player("Peppe", Color.BROWN);


        p4 = new Player("Rick", Color.BLACK);
        g.setPlayerInList(p1);
        g.setPlayerInList(p2);
        g.setPlayerInList(p3);
        g.selectNplayer(3, v);
    }

    @Test
    void nameIsAlreadyPresent() {
        assertFalse(g.nicknameAvailable(p2.getUsername()));
    }

    @Test
    void colorIsAlreadyPresent() {
        assertFalse(g.colorAvailable(p2.getColor()));
    }

    @Test
    void gameAlreadyStarted() {
        assertTrue(g.gameAlreadyStarted());
    }
}
