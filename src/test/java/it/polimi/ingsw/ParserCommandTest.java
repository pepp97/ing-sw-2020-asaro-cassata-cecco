package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.ParserCommand;
import it.polimi.ingsw.commands.Command;
import org.junit.jupiter.api.Test;

public class ParserCommandTest {
    private ParserCommand p;

    @Test
    void parser() {
        p = new ParserCommand();
        String a = "{\"commandName\":\"ChooseGods\",\"attributes\":{\"namesGod\":[\"Appollo\",\"Artemis\"]}}";
        String b = "{\"commandName\":\"ChooseInitialPosition\",\"attributes\":{\"coordinateX\":1,\"coordinateY\":2}}";
        String c = "{\"commandName\":\"ChooseSettings\",\"attributes\":{\"nplayer\":3}}";
        String d = "{\"commandName\":\"ChooseTarget\",\"attributes\":{\"coordinateX\":1,\"coordinateY\":2}}";
        String e = "{\"commandName\":\"ChooseYourGod\",\"attributes\":{\"name\":\"Appollo\"}}";
        String f = "{\"commandName\":\"ChooseYourWorker\",\"attributes\":{\"coordinateX\":1,\"coordinateY\":2}}";
        String g = "{\"commandName\":\"Connection\"}";
        String h = "{\"commandName\":\"Disconnection\"}";
        String i = "{\"commandName\":\"LoginCommand\",\"attributes\":{\"nickname\":\"Mario\",\"color\":\"BLACK\"}}";
        String l = "{\"commandName\":\"StarterCommand\",\"attributes\":{\"nick\":\"RICK\"}}";
        String m = "{\"commandName\":\"UseEffect\",\"attributes\":{\"reply\":true}}";

        Command ea = p.parser(a);
        Command eb = p.parser(b);
        Command ec = p.parser(c);
        Command ed = p.parser(d);
        Command ee = p.parser(e);
        Command ef = p.parser(f);
        Command eg = p.parser(g);
        Command eh = p.parser(h);
        Command ei = p.parser(i);
        Command el = p.parser(l);
        Command em = p.parser(m);
    }

}
