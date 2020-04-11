package it.polimi.ingsw.view;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class VirtualView extends Thread implements View  {

    private Player owner;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Controller controller;

    public VirtualView(Socket socket,Controller controller){
        this.socket=socket;
        this.controller=controller;
        try{
            InputStreamReader input= new InputStreamReader(socket.getInputStream());
            this.in=new Scanner(input);
            this.out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.println("Utente connesso, IP: "+socket.getInetAddress()+ "; Port: "+socket.getPort());

        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public VirtualView(){
        this.out=new PrintWriter(System.out);
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public void update(Event event) {
        BuilderEvent b=new BuilderEvent();
        String json=b.builder(event);

        this.out.println(json);
        System.out.println("Inviato Event: "+event.toString());


    }

    public void receive(String json){
        ParserCommand b=new ParserCommand();
        Command command=b.parser(json);
        command.execute(controller,this);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void run() {

            while(true){
                String s= null;
                while((s = in.nextLine())!=null){
                    receive(s);
                    System.out.println("Ricevuto: "+s);
                }
            }
    }
}
