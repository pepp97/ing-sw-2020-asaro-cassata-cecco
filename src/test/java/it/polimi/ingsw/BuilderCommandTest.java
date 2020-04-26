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
    void builder() {
        p = new BuilderCommand();

        //variabili test Choose Gods
        List<String> namesGods = new ArrayList<>();
        namesGods.add("Appollo");
        namesGods.add("Artemis");

        //variabili test ChooseInitialPosition, ChooseTarget, ChooseYourWorker
        int x = 1;
        int y = 2;

        //variabile test ChooseSettings
        int nplayer = 3;

        //variabile test ChooseYourGod
        String name = "Appollo";

        //variabili test LoginCommand
        String nickname = "Mario";
        Color c = Color.BLACK;

        //variabile test UseEffect
        String nick = "RICK";

        //variabile test UseEffect
        boolean reply = true;

        ChooseGods chooseGods = new ChooseGods(namesGods);
        ChooseInitialPosition initialPosition = new ChooseInitialPosition(x, y);
        ChooseSettings chooseSettings = new ChooseSettings(nplayer);
        ChooseTarget chooseTarget = new ChooseTarget(x, y);
        ChooseYourGod chooseYourGod = new ChooseYourGod(name);
        ChooseYourWorker chooseYourWorker = new ChooseYourWorker(x, y);
        Connection connection = new Connection();
        Disconnection disconnection = new Disconnection();
        LoginCommand loginCommand = new LoginCommand(nickname, c);
        StarterCommand starterCommand = new StarterCommand(nick);
        UseEffect useEffect = new UseEffect(reply);

        System.out.println(p.builder(chooseGods));
        System.out.println(p.builder(initialPosition));
        System.out.println(p.builder(chooseSettings));
        System.out.println(p.builder(chooseTarget));
        System.out.println(p.builder(chooseYourGod));
        System.out.println(p.builder(chooseYourWorker));
        System.out.println(p.builder(connection));
        System.out.println(p.builder(disconnection));
        System.out.println(p.builder(loginCommand));
        System.out.println(p.builder(starterCommand));
        System.out.println(p.builder(useEffect));

    }
}
