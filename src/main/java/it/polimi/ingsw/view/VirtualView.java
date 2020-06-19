package it.polimi.ingsw.view;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * this Class manage the connection from server to client
 */

public class VirtualView extends Thread implements View {
    /**
     * it is the player owner of the VirtualView
     */
    private Player owner;
    /**
     * it is the communication channel between client and server
     */
    private Socket socket;
    /**
     * it is the buffer where virtual view read message from server
     */
    private Scanner in;
    /**
     * it is the buffer where virtual view write message to server
     */
    private PrintWriter out;
    /**
     * it is the main controller
     */
    private Controller controller;
    /**
     * it is the reader of the input
     */
    private InputStreamReader input;
    /**
     * it is true if server is connect, false otherwise
     */
    private boolean ping = true;
    /**
     * it is true if connection have to end, false otherwise
     */
    private boolean stop = false;

    public boolean isPing() {
        return ping;
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

    /**
     * Default constructor
     * @param socket the channel of cummunication between client and server
     * @param controller the main controller that manage the game
     */

    public VirtualView(Socket socket, Controller controller) {
        this.socket = socket;
        this.controller = controller;
        try {
            input = new InputStreamReader(socket.getInputStream());
            this.in = new Scanner(input);
            this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.println("Utente connesso, IP: " + socket.getInetAddress() + "; Port: " + socket.getPort());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public VirtualView() {
        this.out = new PrintWriter(System.out);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * this method is used to receive the event from the server
     * @param event is the event received
     */


    public void update(Event event) {
        BuilderEvent b = new BuilderEvent();
        String json = b.builder(event);
        this.out.println(json);
        System.out.println("Inviato Event: " + event.toString());


    }

    /**
     * this method is used to receive the json string from the server
     * @param json is the json string that must be converted in the corresponding command
     */

    public void receive(String json) {
        System.out.println(json);
        ParserCommand b = new ParserCommand();
        Command command = b.parser(json);
        command.execute(controller, this);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    /**
     * this method is called when the communication channel have to be closed
     * @throws IOException
     */

    @Override
    public void closeAll() throws IOException {
        stop = true;
        out.close();
        in.close();
        input.close();
        socket.close();
    }

    /**
     * this method is called to run the thread
     */


    @Override
    public void run() {

        while (!stop) {
            String s = null;
            if ((in.hasNext())) {
                s = in.nextLine();
                receive(s);
                System.out.println("Ricevuto: " + s);
            }
        }
        if (controller.getGame().isEnd())
            controller.restart();
    }

    public void setIn(Scanner in) {
        this.in = in;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setInput(InputStreamReader input) {
        this.input = input;
    }
}
