package it.polimi.ingsw.view;

import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class VirtualView extends Thread implements View  {

    private Player owner;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public VirtualView(Socket socket){
        this.socket=socket;

        try{
            InputStreamReader input= new InputStreamReader(socket.getInputStream());
            this.in=new Scanner(input);
            this.out=new PrintWriter(socket.getOutputStream());
            System.out.println("Utente connesso, IP: "+socket.getInetAddress()+ "; Port: "+socket.getPort());
        } catch (IOException e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }


    public void setOwner(Player owner) {
        this.owner = owner;
    }


    public void update(Event event) {
        //prepara JSon e manda
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
                    if(s.equals("END")) break;
                    System.out.println("Messaggio: "+ s);
                }
            }
    }
}
