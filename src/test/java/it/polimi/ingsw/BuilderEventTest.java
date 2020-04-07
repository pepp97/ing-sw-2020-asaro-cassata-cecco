package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.BuilderEvent;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Square;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuilderEventTest {
    private BuilderEvent b;

    @Test
    void parser(){
        b=new BuilderEvent();
        Square a1= new Square(1,1);
        Square a2= new Square(2,1);


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

        b.builder(askUser);
        b.builder(chooseTarget);
        b.builder(chooseWorker);
        b.builder(chooseYourGodEvent);
        b.builder(connectionSuccessful);
        b.builder(deathPlayer);
        b.builder(endGame);
        b.builder(exceptionEvent);
        b.builder(loginSuccessful);
        b.builder(logoutSuccessful);
        b.builder(settingsEvent);
        b.builder(startGameEvent);
    }
}
