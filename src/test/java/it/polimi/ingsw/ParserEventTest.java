package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.ParserEvent;
import it.polimi.ingsw.events.Event;
import org.junit.jupiter.api.Test;

public class ParserEventTest {
    private ParserEvent p;
    @Test
    void parser(){
        p=new ParserEvent();
        String a="{\"eventName\":\"askUser\"}";
        String b="{\"eventName\":\"ChooseTarget\",\"attributes\":{\"message\":\"Test\",\"S\":[[1,1],[2,1]]}}";
        String c="{\"eventName\":\"ChooseWorker\",\"attributes\":{\"S\":[[1,1],[2,1]]}}";
        String d="{\"eventName\":\"ChooseYourGodEvent\",\"attributes\":{\"gods\":[\"Apollo\",\"Artemis\"],\"effects\":[\"Mario\",\"Marco\"]}}";
        String e="{\"eventName\":\"ConnectionSuccessful\"}";
        String f="{\"eventName\":\"DeathPlayer\",\"attributes\":{\"nickname\":\"Mario\"}}";
        String g="{\"eventName\":\"EndGame\",\"attributes\":{\"result\":\"win\"}}";
        String h="{\"eventName\":\"ExceptionEvent\",\"attributes\":{\"exception\":\"exp\"}}";
        String i="{\"eventName\":\"LoginSuccessful\",\"attributes\":{\"nickname\":[\"Mario\",\"Marco\"]}}";
        String l="{\"eventName\":\"LogoutSuccessful\"}";
        String m="{\"eventName\":\"SettingsEvent\"}";
        String n="{\"eventName\":\"StartGameEvent\",\"attributes\":{\"gods\":[\"Apollo\",\"Artemis\"],\"numPlayers\":3}}";
        Event ea=p.parser(a);
        Event eb=p.parser(b);
        Event ec=p.parser(c);
        Event ed=p.parser(d);
        Event ee=p.parser(e);
        Event ef=p.parser(f);
        Event eg=p.parser(g);
        Event eh=p.parser(h);
        Event ei=p.parser(i);
        Event el=p.parser(l);
        Event em=p.parser(m);
        Event en=p.parser(n);
    }
}
