package it.polimi.ingsw.model;

import it.polimi.ingsw.ParserClient.ParserUpdate;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ParserUpdateTest {
    private ParserUpdate p;
    String json;

    @Test
    void parser()  {
        json="[{\"cordinataX\":0,\"cordinataY\":0,\"levels\":0,\"color\":\"Black\"},{\"cordinataX\":0,\"cordinataY\":1,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":0,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":1,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":1,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":1,\"levels\":0,\"color\":\"Brown\"},{\"cordinataX\":2,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":2,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":0,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":1,\"levels\":0,\"color\":\"Brown\"},{\"cordinataX\":3,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":3,\"cordinataY\":3,\"levels\":0,\"color\":\"White\"},{\"cordinataX\":3,\"cordinataY\":4,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":0,\"levels\":0,\"color\":\"White\"},{\"cordinataX\":4,\"cordinataY\":1,\"levels\":0,\"color\":\"Black\"},{\"cordinataX\":4,\"cordinataY\":2,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":3,\"levels\":0,\"color\":\"\"},{\"cordinataX\":4,\"cordinataY\":4,\"levels\":0,\"color\":\"\"}]";
        p=new ParserUpdate(json);
    }
}
