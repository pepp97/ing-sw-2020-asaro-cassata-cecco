package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.commands.ChooseGods;
import it.polimi.ingsw.commands.ChooseYourGod;
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

public class DefeateStateTest {
    private Controller controller = new Controller();
    private Game game= controller.getGame();
    private Field field=game.getField();
    private VirtualView view, view2;
    private List<String> gods;
    private Player p1, p2;
    private Square [][] squares=field.getSquares();
    private Worker w1=new Worker();
    private Worker w2=new Worker();
    private Worker w3=new Worker();
    private Worker w4 = new Worker();
    private TurnState state;

    @BeforeEach
    void setUp(){
        w1.setC(Color.BLACK);
        w2.setC(Color.WHITE);
        w3.setC(Color.WHITE);
        w4.setC(Color.BLACK);
        p1= new Player("john",Color.BLACK);
        p1.setWorkers(w1);
        p1.setWorkers(w4);
        view = new VirtualView();
        view.setOwner(p1);
        game.register(view);
        game.setNumplayer(2);
        game.add(p1);
        field=game.getField();
        p2= new Player("giovi",Color.BLACK);
        p2.setWorkers(w2);
        p2.setWorkers(w3);
        squares[0][1].setWorker(w1);
        squares[0][0].setWorker(w4);
        squares[1][0].setWorker(w2);
        squares[1][1].setWorker(w3);
        view2 = new VirtualView();
        view2.setOwner(p2);
        game.register(view2);
        gods = new ArrayList<>();
        List<EffectRoutine> list1=new ArrayList<>();
        list1.add(new EffectRoutine("move",false));
        God god1=new God("prova1","prova1","prova1",list1);
        p1.setGod(god1);
        controller.getGame().getStartGods().add(god1);
        List<EffectRoutine> list2=new ArrayList<>();
        list2.add(new EffectRoutine("move",false));
        God god2=new God("prova2","prova2","prova2",list2);
        p2.setGod(god2);
        controller.getGame().getStartGods().add(god2);
        state = new DefeatState();
        controller.getTurnManager().add(p1);
        controller.getTurnManager().add(p2);
        controller.getGame().setTargetInUse(w4);
        controller.getGame().setCurrentView(view);
        controller.getGame().setCurrentPlayer(p1);
        game.add(p2);
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
        for (Observer o : game.getObservers()) {
            VirtualView v = (VirtualView) o;
            v.setPing(true);
        }
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
        game.setMaxRetries(10000);
        game.startMytimer();
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
        for (Observer o : game.getObservers()) {
            VirtualView v = (VirtualView) o;
            v.setPing(false);
        }


    }

    @Test
    void defeatWithThreePlayerTest(){


        controller.getGame().setNumplayer(3);
        Player p3 = new Player("frank", Color.BROWN);
        VirtualView view3 = new VirtualView();
        List<EffectRoutine> list=new ArrayList<>();
        list.add(new EffectRoutine("prova",false));
        God god=new God("prova","prova","prova",list);
        controller.getGame().getStartGods().add(god);
        controller.getGame().register(view3);
        view3.setOwner(p3);
        controller.getTurnManager().add(p3);
        game.add(p3);
        state.executeState(controller);
        assertTrue(controller.getGame().getObservers().size() == 2);
        assertTrue(controller.getGame().getPlayerList().size() == 2);
        assertTrue(controller.getGame().getField().getSquares()[0][0].getWorker() == null);
        assertTrue(controller.getGame().getField().getSquares()[0][1].getWorker() == null);




    }

    @Test
    void defeatWithTwoPlayerTest(){

        controller.getGame().setNumplayer(2);
        state.executeState(controller);
        assertTrue(controller.getGame().getWinner().getUsername().equals("giovi"));



    }
}
