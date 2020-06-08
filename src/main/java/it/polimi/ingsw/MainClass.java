package it.polimi.ingsw;

import it.polimi.ingsw.view.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {


    public static void main(String[] args) throws IOException {
        BufferedReader init = new BufferedReader(new InputStreamReader(System.in));

        String side;
        do{
            System.out.println("Insert: \n");
            System.out.println("--Client to run the Gui \n");
            System.out.println("--Server to run the Server \n");
            side = init.readLine();
        }while(!(side.equals("--Client") || side.equals("--Server")));

        if(side.equals("--Client"))
            Gui.main();
        else if(side.equals("--Server"))
            Server.main();
    }


}
