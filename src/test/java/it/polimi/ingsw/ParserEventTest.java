package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.ParserEvent;
import it.polimi.ingsw.events.Event;
import org.junit.jupiter.api.Test;

public class ParserEventTest {
    private ParserEvent p;

    @Test
    void parser() {
        p = new ParserEvent();
        String campo = "\"squares\":[{\"cordinataX\":0,\"cordinataY\":0,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":0,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":2,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":3,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":3,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":3,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":0,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":4,\"cordinataY\":1,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":4,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"}]";
        String a = "{\"eventName\":\"askUser\"}";
        String b = "{\"eventName\":\"ChooseTarget\",\"attributes\":{\"message\":\"Test\",\"S\":[[0,\"\",1,1],[0,\"\",2,1]]," + campo + "}}";
        String c = "{\"eventName\":\"ChooseWorker\",\"attributes\":{\"S\":[[0,\"BROWN\",1,1],[1,\"BLACK\",2,1]]," + campo + "}}";
        String d = "{\"eventName\":\"ChooseYourGodEvent\",\"attributes\":{\"gods\":[\"Apollo\",\"Artemis\"],\"effects\":[\"Mario\",\"Marco\"]}}";
        String e = "{\"eventName\":\"ConnectionSuccessful\"}";
        String f = "{\"eventName\":\"DeathPlayer\",\"attributes\":{\"nickname\":\"Mario\"}}";
        String g = "{\"eventName\":\"EndGame\",\"attributes\":{\"result\":\"win\"}}";
        String h = "{\"eventName\":\"ExceptionEvent\",\"attributes\":{\"exception\":\"exp\"}}";
        String i = "{\"eventName\":\"LoginSuccessful\",\"attributes\":{\"nickname\":[\"Mario\",\"Marco\"]}}";
        String l = "{\"eventName\":\"LogoutSuccessful\"}";
        String m = "{\"eventName\":\"SettingsEvent\"}";
        String n = "{\"eventName\":\"StartGameEvent\",\"attributes\":{\"gods\":[\"Apollo\",\"Artemis\"],\"numPlayers\":3}}";
        String u = "{\"eventName\":\"UpdateEvent\",\"attributes\":{\"squares\":[{\"cordinataX\":0,\"cordinataY\":0,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":0,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":2,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":3,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":3,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":3,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":0,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":4,\"cordinataY\":1,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":4,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"}]}}";
        String k = "{\"eventName\":\"StartMatchEvent\",\"attributes\":{\"linking\":[{\"nickname\":\"Salvo\",\"godName\":\"Apollo\"},{\"nickname\":\"Rick\",\"godName\":\"Artmide\"},{\"nickname\":\"Peppe\",\"godName\":\"Gino\"}],"+campo+"}}";
        String j = "{\"eventName\":\"SetWorkerEvent\",\"attributes\":{\"S\":[[0,null,1,1],[0,null,2,3]],\"squares\":[{\"cordinataX\":0,\"cordinataY\":0,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":0,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":0,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":1,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":1,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":2,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":2,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":0,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":1,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":3,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":3,\"cordinataY\":3,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":3,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":0,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":4,\"cordinataY\":1,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":4,\"cordinataY\":2,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":3,\"levels\":0,\"color\":\"null\"},{\"cordinataX\":4,\"cordinataY\":4,\"levels\":0,\"color\":\"null\"}]}}";

        Event ea = p.parser(a);
        Event eb = p.parser(b);
        Event ec = p.parser(c);
        Event ed = p.parser(d);
        Event ee = p.parser(e);
        Event ef = p.parser(f);
        Event eg = p.parser(g);
        Event eh = p.parser(h);
        Event ei = p.parser(i);
        Event el = p.parser(l);
        Event em = p.parser(m);
        Event en = p.parser(n);
        Event up = p.parser(u);
        Event sm = p.parser(k);
        Event sw = p.parser(j);
    }
}
