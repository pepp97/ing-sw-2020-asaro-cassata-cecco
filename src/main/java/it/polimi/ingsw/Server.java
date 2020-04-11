package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.view.VirtualView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static final int port=8080;


    public static void main(String[] args) throws IOException {
        Controller controller=new Controller();
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
