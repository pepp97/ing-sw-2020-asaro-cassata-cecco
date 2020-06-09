package it.polimi.ingsw.controller;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.VirtualView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.VolatileImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {
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
        for (Observer o : game.getObservers()){
            VirtualView v=(VirtualView)o;
            v.setPing(true);}
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
        w1.setCanBuild(true);
        w2.setCanBuild(true);
        w3.setCanBuild(true);
        for (Observer o : game.getObservers()){
            VirtualView v=(VirtualView)o;
            v.setPing(false);
        }
        game.unregister(view2);
    }

    @Test
    void loginCommandApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        assertTrue(loginCommand.getNickname() == game.getPlayerList().get(1).getUsername());
        assertTrue(loginCommand.getColor() == game.getPlayerList().get(1).getColor());

    }

    @Test
    void chooseSettingCommandApplyTest() {
        System.out.println(" la size è " + controller.getGame().getPlayerList().size());
        ChooseSettings command = new ChooseSettings(2);
        controller.apply(command, view);
        assertTrue(command.getNplayer() == game.getNumplayer());
    }

    @Test
    void chooseGodsCommandApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        gods.add("Apollo");
        gods.add("Athena");
        ChooseGods command = new ChooseGods(gods);
        controller.apply(command);
        assertTrue(game.getStartGods().get(0).getName().equals(command.getNamesGod().get(0)));
        assertTrue(game.getStartGods().get(1).getName().equals(command.getNamesGod().get(1)));
    }

    @Test
    void chooseYourGodCommandApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        gods.add("Apollo");
        ChooseGods command2 = new ChooseGods(gods);
        controller.apply(command2);
        ChooseYourGod command = new ChooseYourGod("Apollo");
        controller.apply(command, view);
        assertTrue(command.getName().equals(game.getPlayerList().get(0).getGod().getName()));
    }

    @Test
    void starterCommandApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        StarterCommand command3 = new StarterCommand("john");
        controller.apply(command3, view2);
        assertTrue(controller.getTurnManager().size() == 2);
        assertTrue(controller.getGame().getCurrentPlayer().getUsername() == "john");
        assertTrue(controller.getState() instanceof SetWorkerState);

    }


    // vedere se testare altro
    @Test
    void chooseInitialPositionCommandTest() {

        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        StarterCommand command3 = new StarterCommand("john");
        controller.apply(command3, view2);
        ChooseInitialPosition command1 = new ChooseInitialPosition(2, 2);
        controller.apply(command1, view2);
        assertTrue(game.getPlayerList().get(1).getWorkers().get(0) == game.getField().getSquares()[2][2].getWorker());
        ChooseInitialPosition command2 = new ChooseInitialPosition(3, 4);
        controller.apply(command2, view2);
        assertTrue(game.getPlayerList().get(1).getWorkers().get(1) == game.getField().getSquares()[3][4].getWorker());
        assertTrue(controller.getState() instanceof ExecuteRoutineState);
    }

    @Test
    void chooseYourWorkerApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        gods.add("Apollo");
        ChooseGods command2 = new ChooseGods(gods);
        controller.apply(command2);
        ChooseYourGod command = new ChooseYourGod("Apollo");
        controller.apply(command, view2);
        StarterCommand command3 = new StarterCommand("giovi");
        controller.apply(command3, view2);
        ChooseInitialPosition command1 = new ChooseInitialPosition(2, 2);
        controller.apply(command1, view2);
        ChooseInitialPosition command5 = new ChooseInitialPosition(3, 4);
        controller.apply(command5, view2);
        ChooseYourWorker command4 = new ChooseYourWorker(2, 2);
        controller.getGame().setCurrentPlayer(controller.getGame().getPlayerList().get(1));
        controller.apply(command4);
        assertTrue(controller.getGame().getTargetInUse() == controller.getGame().getPlayerList().get(1).getWorkers().get(0));
    }

    @Test
    void chooseTargetApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        ArrayList<EffectRoutine> effectRoutines = new ArrayList<>();
        effectRoutines.add(new EffectRoutine("move", false));
        God god = new God("God", "God", "God", effectRoutines);
        game.getPlayerList().get(1).setGod(god);
        StarterCommand command3 = new StarterCommand("giovi");
        controller.apply(command3, view2);
        ChooseInitialPosition command1 = new ChooseInitialPosition(2, 2);
        controller.apply(command1, view2);
        ChooseInitialPosition command5 = new ChooseInitialPosition(3, 4);
        controller.apply(command5, view2);
        ChooseYourWorker command6 = new ChooseYourWorker(2, 2);
        controller.apply(command6);
        ChooseTarget command4 = new ChooseTarget(2, 2);
        controller.apply(command4);
        // assertTrue(controller.getGame().getTargetSelected().getSquare().getCoordinateX() == 2);
        // assertTrue(controller.getGame().getTargetSelected().getSquare().getCoordinateY() == 2);
    }

    @Test
    void useEffectApplyTest() {
        LoginCommand loginCommand = new LoginCommand("giovi", Color.WHITE);
        controller.apply(loginCommand, view2);
        ArrayList<EffectRoutine> effectRoutines = new ArrayList<>();
        effectRoutines.add(new EffectRoutine("move", false));
        God god = new God("God", "God", "God", effectRoutines);
        game.getPlayerList().get(1).setGod(god);
        StarterCommand command3 = new StarterCommand("giovi");
        controller.apply(command3, view2);
        ChooseInitialPosition command1 = new ChooseInitialPosition(2, 2);
        controller.apply(command1, view2);
        ChooseInitialPosition command5 = new ChooseInitialPosition(3, 4);
        controller.apply(command5, view2);
        ChooseYourWorker command6 = new ChooseYourWorker(2, 2);
        controller.apply(command6);
        controller.getGame().setTargetSelected(w4);
        UseEffect command7 = new UseEffect(true);
        controller.apply(command7);
        assertTrue(controller.isCanSkip() == !command7.getReply());
    }

   @Test
    void undoTest(){
        game.setUndo(true);
        game.setCurrentPlayer(p);
        //game.login("prova", Color.BROWN,new VirtualView());
        Move move=new Move();
        game.setTargetInUse(p.getWorkers().get(1));
        game.setTargetSelected(squares[0][0]);
        controller.saveAll();
        move.isUsable(game);
        move.use(game);
        controller.getTurnManager().add(p);
        assertFalse(squares[0][0].getWorker().equals(w1));
        controller.apply(new UndoCommand(), view2);
        assertTrue(squares[1][1].getWorker().equals(w1));
       controller.startTimer();
    }

}
