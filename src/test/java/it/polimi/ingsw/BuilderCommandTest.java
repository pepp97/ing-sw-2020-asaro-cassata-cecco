package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.BuilderCommand;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.model.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuilderCommandTest {

    private BuilderCommand p;

    @Test
    void parser(){
        p=new BuilderCommand();

        //variabili test Choose Gods
        List<String> namesGods= new ArrayList<>();
        namesGods.add("Appollo");
        namesGods.add("Artemis");

        //variabili test ChooseInitialPosition, ChooseTarget, ChooseYourWorker
        int x=1;
        int y=2;

        //variabile test ChooseSettings
        int nplayer=3;

        //variabile test ChooseYourGod
        String name="Appollo";

        //variabili test LoginCommand
        String nickname="Mario";
        Color c= Color.BLACK;

        //variabile test UseEffect
        boolean reply=true;

        ChooseGods chooseGods=new ChooseGods(namesGods);
        ChooseInitialPosition initialPosition=new ChooseInitialPosition(x,y);
        ChooseSettings chooseSettings=new ChooseSettings(nplayer);
        ChooseTarget chooseTarget= new ChooseTarget(x,y);
        ChooseYourGod chooseYourGod=new ChooseYourGod(name);
        ChooseYourWorker chooseYourWorker=new ChooseYourWorker(x,y);
        Connection connection= new Connection();
        Disconnection disconnection=new Disconnection();
        LoginCommand loginCommand=new LoginCommand(nickname,c);
        UseEffect useEffect=new UseEffect(reply);

        p.builder(chooseGods);
        p.builder(initialPosition);
        p.builder(chooseSettings);
        p.builder(chooseTarget);
        p.builder(chooseYourGod);
        p.builder(chooseYourWorker);
        p.builder(connection);
        p.builder(disconnection);
        p.builder(loginCommand);
        p.builder(useEffect);

    }
}
