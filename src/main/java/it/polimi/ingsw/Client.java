package it.polimi.ingsw;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket=null;
        BufferedReader in=null,stdin=null;
        BufferedWriter writer=null;
        PrintWriter out=null;
        try{
            socket=new Socket("127.0.0.1",Server.port);
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
    }
}
