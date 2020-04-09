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
import java.net.UnknownHostException;

public class Client {


    private  View view;
    /*    public static void main(String[] args) throws IOException {
                Socket socket=null;
                BufferedReader in=null,stdin=null;
                BufferedWriter writer=null;
                PrintWriter out=null;
                try{
                    socket=new Socket("127.0.0.1",Server.port);
                    Gui gui=new Gui();
                    gui.main();
                    System.out.println("Client started "+socket);

                    //Stream del client
                    in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    out=new PrintWriter(writer,true);
                    //stream input da tastiera
                    stdin= new BufferedReader(new InputStreamReader(System.in));
                    String userInput;

                    while ((userInput = stdin.readLine()) != null) {
                        if(userInput.equals("END")) break;
                        out.println(userInput);
                        System.out.println(  in.readLine());
                    }

                }catch (UnknownHostException exp2){
                    System.err.println("Errore: "+ exp2.getMessage());
                    System.exit(1);
                }catch (IOException exp1){
                    System.err.println("Errore: "+ exp1.getMessage());
                    System.exit(1);
                }
                System.out.println("Client closing");
                out.close();
                stdin.close();
                in.close();
                socket.close();
            }*/
    private Socket socket=null;
   private BufferedReader in=null,stdin=null;
    private BufferedWriter writer=null;
    private PrintWriter out=null;
    private int portNumber;
    private String ipAddress;



    public Client(String text, int port, View gui) {
        this.view=gui;
        this.ipAddress=text;
        this.portNumber=port;
        try {
            startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() throws IOException {

           try{
               socket=new Socket(ipAddress,portNumber);

               System.out.println("Client started "+socket);

               //Stream del client
               in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
               writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
               out=new PrintWriter(writer,true);
               //stream input da tastiera
               stdin= new BufferedReader(new InputStreamReader(System.in));
               //String userInput;





           }catch (UnknownHostException exp2){
               System.err.println("Errore: "+ exp2.getMessage());
               System.exit(1);
           }
           System.out.println("Client closing");
           //out.close();
           //stdin.close();
           //in.close();
          // socket.close();
       }

    public void send(Command cmd){
        BuilderCommand b=new BuilderCommand();
        String json=b.builder(cmd);

        this.out.println(json);
        System.out.println("Command: " +cmd.toString());

    }

    public Event receive(String json){
        ParserEvent p=new ParserEvent();
        Event event=p.parser(json);
        return event;
    }
}

