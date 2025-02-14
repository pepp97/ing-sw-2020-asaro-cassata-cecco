package it.polimi.ingsw;

import it.polimi.ingsw.ParserClient.ParserUpdate;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.UpdateEvent;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ParserUpdateTest {
    private ParserUpdate p;
    String json;

    @Test
    void parser()  {
        UpdateEvent update;
        json="[{\"cordinataX\":0,\"cordinataY\":0,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":0,\"cordinataY\":1,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":1,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":1,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":2,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":1,\"levels\":0,\"color\":\"BROWN\"},{\"cordinataX\":3,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":3,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":3,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":0,\"levels\":0,\"color\":\"WHITE\"},{\"cordinataX\":4,\"cordinataY\":1,\"levels\":0,\"color\":\"BLACK\"},{\"cordinataX\":4,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":4,\"levels\":0,\"color\":\"\"}]";
        p=new ParserUpdate();
        update=p.parser(json);
        print(update);
    }

    public void print(UpdateEvent update){
        for (SquareToJson[] squareToJsons : update.getSquares()) {
            for (SquareToJson squareToJson : squareToJsons) {
                System.out.println(squareToJson.toString());
            }
        }
    }
}
