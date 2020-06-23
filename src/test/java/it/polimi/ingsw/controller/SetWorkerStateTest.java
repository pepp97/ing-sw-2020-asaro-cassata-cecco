package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetWorkerStateTest {
    private Controller controller = new Controller();
    private Game game = controller.getGame();
    private Field field = game.getField();
    private VirtualView view;
    Worker w1 = new Worker();
    Worker w2 = new Worker();
    Worker w3 = new Worker();
    Worker w4 = new Worker();
    Square[][] squares = field.getSquares();
    Player p;
    VirtualView view2;
    List<String> gods;
    private TurnState state;

    @BeforeEach
    void setUp() {
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.BROWN);
        w4.setC(Color.BLACK);
        p = new Player("john", Color.BLACK);
        p.setWorkers(w1);
        p.setWorkers(w4);
        view = new VirtualView();
        view.setOwner(p);
        game.register(view);
        game.setNumplayer(2);
        game.add(p);
        game.register(view);
        List<EffectRoutine> list = new ArrayList<>();
        list.add(new EffectRoutine("move", false));
        God g = new God("prova", "prova", "prova", list);
        p.setGod(g);
        field = game.getField();
        squares[1][1].setWorker(w1);
        squares[0][0].setWorker(w4);
        view2 = new VirtualView();
        gods = new ArrayList<>();
        for (Observer o : game.getObservers()) {
            VirtualView v = (VirtualView) o;
            v.setPing(true);
        }
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
        state = new SetWorkerState();
        game.startMytimer();
    }


    @AfterEach
    void reset() {
        squares[1][1].removeWorker();
        squares[1][2].removeWorker();
        squares[1][3].removeWorker();
        squares[2][1].removeWorker();
        squares[2][2].removeWorker();
        squares[2][3].removeWorker();
        squares[3][1].removeWorker();
        squares[3][2].removeWorker();
        squares[3][3].removeWorker();
        for (Observer o : game.getObservers()) {
            VirtualView v = (VirtualView) o;
            v.setPing(false);
        }


    }

    @Test
    void setWorkerStatetTest() {
        controller.getGame().setCurrentView(view);
        controller.getGame().setCurrentPlayer(p);
        state.executeState(controller);
        //assertTrue();
    }
}
