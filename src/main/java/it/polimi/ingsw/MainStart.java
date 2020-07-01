package it.polimi.ingsw;

import it.polimi.ingsw.view.Gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class launch the application
 * default value: Client
 * Server parameter: --Server
 */
public class MainStart {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            Gui.main();
        } else if (args[0].equals("--Server")) {
            Server.main();
        } else if (args[0].equals("--Client")) {
            Gui.main();
        }

    }
}