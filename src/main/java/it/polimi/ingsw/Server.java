package it.polimi.ingsw;

import it.polimi.ingsw.view.VirtualView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;

public class Server {
    public static final int port=8080;

    public static void main(String[] args) throws IOException {
        while(true){
            try(ServerSocket clientGatherer=new ServerSocket(port)){
                VirtualView virtualView=new VirtualView(clientGatherer.accept());
                virtualView.start();
            }catch (IOException exp){
                exp.printStackTrace();
            }

        }
        /*ServerSocket serverSocket= new ServerSocket(port);
        System.out.println("Server started: " +serverSocket);
        Socket clientSocket=null;
        BufferedReader bufferedReader= null;
        PrintWriter printWriter=null;

        try{
            clientSocket= serverSocket.accept();
            System.out.println("Connection accepted: "+clientSocket);
            //Stream del server
            InputStreamReader input= new InputStreamReader(clientSocket.getInputStream());
            bufferedReader=new BufferedReader(input);
            OutputStreamWriter output=new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter writer= new BufferedWriter(output);
            printWriter=new PrintWriter(writer,true);

            while(true){
                String s= bufferedReader.readLine();
                if(s.equals("END")){
                    break;
                }
                System.out.println("Messaggio da Client: "+ s);
                printWriter.println(s);
            }
        }catch (IOException exp){
            System.err.println("Errore: "+ exp.getMessage());
            System.exit(1);
        }

        System.out.println("Server: closing");
        printWriter.close();
        bufferedReader.close();
        clientSocket.close();
        serverSocket.close();

         */
    }
}
