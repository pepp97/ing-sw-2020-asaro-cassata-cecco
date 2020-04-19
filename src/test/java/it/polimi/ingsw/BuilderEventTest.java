package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BuilderEventTest {
    private BuilderEvent b;

    @Test
    void parser(){
        b=new BuilderEvent();
        Square a1= new Square(1,1);
        Square a2= new Square(2,1);

        SquareToJson [][]squares=new SquareToJson[5][5];
        squares[0][0]= new SquareToJson(0,"",0,0);
        squares[0][1]= new SquareToJson(0,"",0,1);
        squares[0][2]= new SquareToJson(0,"",0,2);
        squares[0][3]= new SquareToJson(0,"",0,3);
        squares[0][4]= new SquareToJson(0,"",0,4);

        squares[1][0]= new SquareToJson(0,"",1,0);
        squares[1][1]= new SquareToJson(0,"",1,1);
        squares[1][2]= new SquareToJson(0,"",1,2);
        squares[1][3]= new SquareToJson(0,"",1,3);
        squares[1][4]= new SquareToJson(0,"",1,4);

        squares[2][0]= new SquareToJson(0,"",2,0);
        squares[2][1]= new SquareToJson(0,"",2,1);
        squares[2][2]= new SquareToJson(0,"",2,2);
        squares[2][3]= new SquareToJson(0,"",2,3);
        squares[2][4]= new SquareToJson(0,"",2,4);

        squares[3][0]= new SquareToJson(0,"",3,0);
        squares[3][1]= new SquareToJson(0,"",3,1);
        squares[3][2]= new SquareToJson(0,"",3,2);
        squares[3][3]= new SquareToJson(0,"",3,3);
        squares[3][4]= new SquareToJson(0,"",3,4);

        squares[4][0]= new SquareToJson(0,"",4,0);
        squares[4][1]= new SquareToJson(0,"",4,1);
        squares[4][2]= new SquareToJson(0,"",4,2);
        squares[4][3]= new SquareToJson(0,"",4,3);
        squares[4][4]= new SquareToJson(0,"",4,4);

        squares[0][0].setColor(Color.BLACK);
        squares[4][1].setColor(Color.BLACK);

        squares[3][1].setColor(Color.WHITE);
        squares[2][1].setColor(Color.WHITE);

        squares[4][0].setColor(Color.BROWN);
        squares[3][3].setColor(Color.BROWN);



        //variabili test per ChooseTarget
        String message="Test";
        List<Square> availableSquare= new ArrayList<>();
        availableSquare.add(a1);
        availableSquare.add(a2);

        //variabili test per ChooseWorker
        List<Square> posWorker= new ArrayList<>();
        posWorker.add(a1);
        posWorker.add(a2);

        //variabili test per DeathPlayer
        String nickname="Mario";

        //variabili test per EndGame
        String result="win";

        //variabili test per ExceptionEvent
        String exp="exp";

        //variabili test per LoginSuccessful
        String nick1="Mario";
        String nick2="Marco";
        List<String> login=new ArrayList<>();
        login.add(nick1);
        login.add(nick2);

        //variabili test per LoginSuccessful
        List<String> namesGod=new ArrayList<>();
        namesGod.add("Apollo");
        namesGod.add("Artemis");
        int nplayer=3;

        //variabili test per StartMatch
        LinkedHashMap<String,String> godPlayer=new LinkedHashMap<>();
        godPlayer.put("Salvo","Apollo");
        godPlayer.put("Rick","Artmide");
        godPlayer.put("Peppe","Gino");

        askUser askUser=new askUser();
        ChooseTarget chooseTarget= new ChooseTarget(message,availableSquare);
        ChooseWorker chooseWorker= new ChooseWorker(posWorker);
        ChooseYourGodEvent chooseYourGodEvent=new ChooseYourGodEvent(namesGod,login);
        ConnectionSuccessful connectionSuccessful= new ConnectionSuccessful();
        DeathPlayer deathPlayer=new DeathPlayer(nick1);
        EndGame endGame=new EndGame(result);
        ExceptionEvent exceptionEvent=new ExceptionEvent(exp);
        LoginSuccessful loginSuccessful=new LoginSuccessful(login);
        LogoutSuccessful logoutSuccessful=new LogoutSuccessful();
        SettingsEvent settingsEvent=new SettingsEvent();
        StartGameEvent startGameEvent= new StartGameEvent(namesGod,nplayer);
        UpdateEvent updateEvent=new UpdateEvent(squares);
        StartMatchEvent startMatchEvent=new StartMatchEvent(godPlayer);

        System.out.println(b.builder(askUser));
        System.out.println(b.builder(chooseTarget));
        System.out.println(b.builder(chooseWorker));
        System.out.println(b.builder(chooseYourGodEvent));
        System.out.println(b.builder(connectionSuccessful));
        System.out.println(b.builder(deathPlayer));
        System.out.println(b.builder(endGame));
        System.out.println(b.builder(exceptionEvent));
        System.out.println(b.builder(loginSuccessful));
        System.out.println(b.builder(logoutSuccessful));
        System.out.println(b.builder(settingsEvent));
        System.out.println(b.builder(startGameEvent));
        System.out.println(b.builder(updateEvent));
        String c=b.builder(startMatchEvent);
        System.out.println(c);
    }
}
