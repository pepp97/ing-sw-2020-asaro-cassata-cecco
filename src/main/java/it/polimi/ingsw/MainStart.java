package it.polimi.ingsw;

import it.polimi.ingsw.view.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainStart {

    public static void main(String[] args) throws IOException {
        System.out.println("Insert --Client to launch GUI, Insert --Server to launch Server");
        BufferedReader initPort = new BufferedReader(new InputStreamReader(System.in));
        String param = initPort.readLine();
        while(true){
            if (param.equals("--Client")) {
                Gui.main();
                break;
            } else if (param.equals("--Server")) {
                Server.main();
                break;
            }else{
                System.out.println("Insert --Client to launch GUI, Insert --Server to launch Server");
                param = initPort.readLine();
            }
        }
    }
}