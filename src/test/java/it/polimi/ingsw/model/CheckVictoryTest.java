package it.polimi.ingsw.model;

import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckVictoryTest {
    private Move move = new Move();
    private List levels = new ArrayList();
    private Controller controller = new Controller();
    private Game game = controller.getGame();
    private CheckVictory checkVictory;
    private Player p1;
    private Player p2;
    private VirtualView view=new VirtualView();
    private VirtualView view2=new VirtualView();

    @BeforeEach
    void setUp() {
        game.setMaxRetries(1000);
        Socket socket = new Socket();
        InputStreamReader input = new InputStreamReader(new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        });
        view.setIn(new Scanner(input));
        view.setSocket(socket);
        view.setInput(input);
        view.setOut(new PrintWriter(new BufferedWriter(new OutputStreamWriter(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }))));
        view2.setIn(new Scanner(input));
        view2.setSocket(socket);
        view2.setInput(input);
        view2.setOut(new PrintWriter(new BufferedWriter(new OutputStreamWriter(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        }))));
        game.login("peppe", Color.BROWN, view);
        game.setNumplayer(2);
        game.login("peppe2", Color.BLACK, view2);
        System.out.println("size:::::"+game.getPlayerList().size());
        p1 = game.getPlayerList().get(0);
        p2=game.getPlayerList().get(1);
        controller.getTurnManager().add(p1);
        controller.getTurnManager().add(p2);
        levels.add(2);
        checkVictory = new CheckVictory(levels);
        game.setCurrentPlayer(p1);
        game.setCurrentView(new VirtualView());
        Worker w1 = new Worker();
        Worker w2 = new Worker();
        p1.setWorkers(w1);
        p1.setWorkers(w2);
        game.getField().getSquares()[0][0].setWorker(w1);
        game.getField().getSquares()[1][1].setLevel(0);
        game.getField().getSquares()[0][0].setLevel(2);
        game.startMytimer();
    }

    @AfterEach
    void tearDown(){
        game.setWinner(null);
    }

    @Test
    void checkVictoryTestOk() {
        game.setTargetInUse(p1.getWorkers().get(0));
        game.setTargetSelected(game.getField().getSquares()[1][1]);
        move.isUsable(game);
        move.use(game);
        checkVictory.use(game);
        assertEquals(p1, game.getWinner());
        checkVictory.isInterationNeeded();
    }

    @Test
    void checkVictoryTestKO() {
        game.setTargetInUse(p1.getWorkers().get(0));
        game.setTargetSelected(game.getField().getSquares()[1][1]);
        move.isUsable(game);
        move.use(game);
        game.setTargetSelected(game.getField().getSquares()[1][2]);
        move.isUsable(game);
        move.use(game);

        checkVictory.use(game);
        assertEquals(null, game.getWinner());
    }
}
