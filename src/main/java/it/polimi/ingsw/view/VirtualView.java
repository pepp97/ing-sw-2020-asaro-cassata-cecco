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

public class VirtualView extends Thread implements View {

    private Player owner;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Controller controller;
    private InputStreamReader input;
    private boolean ping = true;
    private boolean stop = false;

    public boolean isPing() {
        return ping;
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

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


    public void update(Event event) {
        BuilderEvent b = new BuilderEvent();
        String json = b.builder(event);
        this.out.println(json);
        System.out.println("Inviato Event: " + event.toString());


    }

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

    @Override
    public void closeAll() throws IOException {
        stop = true;
        out.close();
        in.close();
        input.close();
        socket.close();
    }




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
