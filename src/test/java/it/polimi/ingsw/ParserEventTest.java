package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.ParserEvent;
import org.junit.jupiter.api.Test;

public class ParserEventTest {
    private ParserEvent p;
    @Test
    void parser(){
        p=new ParserEvent();
        String json="{\"eventName\":\"ChooseYourGodEvent\",\"attributes\":{\"gods\":[\"Apollo\",\"Artemis\"],\"effects\":[\"Mario\",\"Marco\"]}}";
        p.parser(json);
    }
}
