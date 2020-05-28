package it.polimi.ingsw.ParserServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.commands.*;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.Square;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ParserCommand {
    private Command commandReturn;

    public Command parser(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode commandName = rootNode.path("commandName");
            JsonNode attributes = rootNode.path("attributes");
            selectType(commandName.toString(), attributes);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return commandReturn;
    }

    public void selectType(String command, JsonNode a) {
        switch (command) {
            case "\"ChooseGods\"":
                JsonNode namesGod = a.path("namesGod");
                List<String> namesGodList = new ArrayList<>();
                for (int i = 0; i < namesGod.size(); i++) {
                    JsonNode b = namesGod.get(i);
                    namesGodList.add(b.toString().replace("\"", ""));
                }
                commandReturn = new ChooseGods(namesGodList);
                break;
            case "\"ChooseInitialPosition\"": {
                JsonNode posX = a.path("coordinateX");
                JsonNode posY = a.path("coordinateY");
                commandReturn = new ChooseInitialPosition(posX.asInt(), posY.asInt());
                break;
            }
            case "\"ChooseSettings\"":
                JsonNode nplayer = a.path("nplayer");
                commandReturn = new ChooseSettings(nplayer.asInt());
                break;
            case "\"ChooseTarget\"": {
                JsonNode posX = a.path("coordinateX");
                JsonNode posY = a.path("coordinateY");
                commandReturn = new ChooseTarget(posX.asInt(), posY.asInt());
                break;
            }
            case "\"ChooseYourGod\"":
                JsonNode name = a.path("name");
                commandReturn = new ChooseYourGod(name.toString().replace("\"", ""));
                break;
            case "\"ChooseYourWorker\"": {
                JsonNode posX = a.path("coordinateX");
                JsonNode posY = a.path("coordinateY");
                commandReturn = new ChooseYourWorker(posX.asInt(), posY.asInt());
                break;
            }
            case "\"Connection\"":
                commandReturn = new Connection();
                break;
            case "\"Disconnection\"":
                commandReturn = new Disconnection();
                break;
            case "\"LoginCommand\"":
                JsonNode nickname = a.path("nickname");
                JsonNode col = a.path("color");
                String c = col.toString().replace("\"", "");
                commandReturn = new LoginCommand(nickname.toString().replace("\"", ""), typeColor(c));
                break;
            case "\"StarterCommand\"":
                JsonNode nick = a.path("nick");
                commandReturn = new StarterCommand(nick.toString().replace("\"", ""));
                break;
            case "\"UseEffect\"":
                JsonNode reply = a.path("reply");
                commandReturn = new UseEffect(reply.asBoolean());
                break;
            case "\"Ping\"":
                commandReturn = new Ping();
                break;
            case "\"UndoCommand\"":
                commandReturn = new UndoCommand();
                break;
        }
    }

    private Color typeColor(String c) {
        switch (c) {
            case "BLACK":
                return Color.BLACK;
            case "WHITE":
                return Color.WHITE;
            case "BROWN":
                return Color.BROWN;
        }
        return null;
    }
}
