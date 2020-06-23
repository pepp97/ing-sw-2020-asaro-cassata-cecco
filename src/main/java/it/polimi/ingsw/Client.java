package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.BuilderCommand;
import it.polimi.ingsw.ParserClient.ParserEvent;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.view.Gui;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * it is the class that handle the client side
 */

public class Client extends Thread {

    /**
     * it is the view of the player
     */
    private Gui view;
    /**
     * it is the socket whwere communicate with server
     */
    private Socket socket = null;
    private BufferedWriter writer = null;
    private PrintWriter out = null;
    /**
     * it is the port number
     */
    private int portNumber;
    /**
     * it is the ip address of the server
     */
    private String ipAddress;
    private InputStreamReader input;
    private Scanner scanner;
    /**
     * it is true if is connect, false otherwise
     */
    private boolean isConnected = true;

    /**
     * default constructor
     * @param text it is the ip address
     * @param port it is the port where communicate
     * @param gui it is the corresponding gui of the player
     */
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

    /**
     * this method is called to send a command to the virtual view
     * @param cmd is the command to send
     */

    public void send(Command cmd) {
        if (socket != null) {
            BuilderCommand b = new BuilderCommand();
            String json = b.builder(cmd);
            this.out.println(json);
            System.out.println(json);
            System.out.println("Command: " + cmd.toString());
        }
    }

    /**
     * this method is called when Client receive an event from the model
     * @param json is the string json that contains the event
     */

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

    /**
     * this method is called when player disconnects
     * @throws IOException
     */

    public void disconnect() throws IOException {
        isConnected = false;
        input.close();
        writer.close();
        out.close();
        scanner.close();
        socket.close();
    }

}







