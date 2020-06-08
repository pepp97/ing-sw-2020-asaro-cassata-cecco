package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.view.VirtualView;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Server {
    public static int port=8080;
    static int portSelected;

    public static void main() throws IOException {
        Controller controller=new Controller();

      /*  try {
            System.out.println("YOUR IP: "+ InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
        }*/

        System.out.println("\nDefault Port: "+"8080");
        BufferedReader initPort = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("Insert a port number, insert 1 for the default port.\n");
            String portNumber = initPort.readLine();
            try {
                portSelected = Integer.parseInt(portNumber);
            }catch(NumberFormatException e){
                System.err.println("string not valid.");
            }
            if(portSelected==1)
                break;
            if (portSelected <= 1023 || portSelected > 49151) {
                System.err.println("try again, port not valid.");
            }
        }while(portSelected <= 1023 || portSelected > 49151);

        if(portSelected!=1)
            port=portSelected;

        System.out.println("port selected: "+ port);

        while(true){
            try(ServerSocket clientGatherer=new ServerSocket(port)){
                VirtualView virtualView=new VirtualView(clientGatherer.accept(),controller);
                virtualView.start();
            }catch (IOException exp){
                exp.printStackTrace();
            }

        }
        
    }


}
