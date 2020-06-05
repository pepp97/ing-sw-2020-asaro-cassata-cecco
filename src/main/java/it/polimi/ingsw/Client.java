package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.BuilderCommand;
import it.polimi.ingsw.ParserClient.ParserEvent;
import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {


    private Gui view;

    private Socket socket = null;
    private BufferedWriter writer = null;
    private PrintWriter out = null;
    private int portNumber;
    private String ipAddress;
    private InputStreamReader input;
    private Scanner scanner;
    private boolean isConnected = true;


    public Client(String text, int port, Gui gui) {
        this.view = gui;
        this.ipAddress = text;
        this.portNumber = port;
        try {
            socket = new Socket(ipAddress, portNumber);
        } catch (IOException e) {
            System.exit(1);
        }
        try {

            this.input = new InputStreamReader(socket.getInputStream());
            System.out.println(socket.getInputStream());

            System.out.println(input.toString());
            this.scanner = new Scanner(input);
            System.out.println(scanner.toString());
            System.out.println("Client started " + socket);

            //Stream del client
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new PrintWriter(writer, true);
            //stream input da tastiera

        } catch (UnknownHostException exp2) {
            System.err.println("Errore: " + exp2.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client closing");
        //out.close();
        //stdin.close();
        //in.close();
        // socket.close();
    }

    public void send(Command cmd) {
        if (socket != null) {
            BuilderCommand b = new BuilderCommand();
            String json = b.builder(cmd);
            this.out.println(json);
            System.out.println(json);
            System.out.println("Command: " + cmd.toString());
        }
    }

    public void receive(String json) {
        System.out.println(json);
        ParserEvent p = new ParserEvent();
        Event event = p.parser(json);
        event.send(view);

        //System.out.println(event.getClass());
    }


    @Override
    public void run() {
        while (isConnected) {
            String s = null;
            if (scanner.hasNext()) {
                s = scanner.nextLine();
                System.out.println(s);

                receive(s);
            }
        }
        System.out.println("client correctly closed");
        // System.out.println("Event: "+ s);
    }

    public void disconnect() throws IOException {
        isConnected = false;
        input.close();
        writer.close();
        out.close();
        scanner.close();
        socket.close();
    }
}







