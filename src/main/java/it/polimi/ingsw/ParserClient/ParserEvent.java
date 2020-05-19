package it.polimi.ingsw.ParserClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParserEvent {
    private Event eventReturn;

    public Event parser(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode commandName = rootNode.path("eventName");
            JsonNode attributes = rootNode.path("attributes");
            selectType(commandName.toString(), attributes);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventReturn;
    }

    public void selectType(String event, JsonNode a) {
        String s = null;
        switch (event) {
            case "\"askUser\"":
                eventReturn = new askUser();
                break;
            case "\"ChooseTarget\"":
                eventReturn = typeChooseTarget(a);
                break;
            case "\"ChooseWorker\"":
                eventReturn = typeChooseWorker(a);
                break;
            case "\"ChooseYourGodEvent\"":
                eventReturn = typeChooseYourGodEvent(a);
                break;
            case "\"ConnectionSuccessful\"":
                eventReturn = new ConnectionSuccessful();
                break;
            case "\"DeathPlayer\"":
                JsonNode nick = a.path("nickname");
                eventReturn = new DeathPlayer(nick.toString().replace("\"", ""));
                break;
            case "\"EndGame\"":
                JsonNode result = a.path("result");
                eventReturn = new EndGame(result.toString().replace("\"", ""));
                break;
            case "\"ExceptionEvent\"":
                JsonNode exp = a.path("exception");
                eventReturn = new ExceptionEvent(exp.toString().replace("\"", ""));
                break;
            case "\"StartGameEvent\"":
                eventReturn = typeStartGameEvent(a);
                break;
            case "\"LoginSuccessful\"":
                eventReturn = typeLogin(a);
                break;
            case "\"LogoutSuccessful\"":
                eventReturn = new LogoutSuccessful();
                break;
            case "\"SettingsEvent\"":
                eventReturn = new SettingsEvent();
                break;
            case "\"SetWorkerEvent\"":
                eventReturn = typeSetWorkerEvent(a);
                break;
            case "\"StartMatchEvent\"":
                eventReturn = typeStartMatchEvent(a);
                break;
            case "\"UpdateEvent\"":
                ParserUpdate p = new ParserUpdate();
                JsonNode fields = a.path("squares");
                eventReturn = p.parser(fields.toString());
                break;
            case "\"WaitYourGodEvent\"":
                eventReturn = typeWaitYourGodEvent(a);
                break;
            case "\"Pong\"":
                eventReturn = new Pong();
                break;
        }
    }

    private ChooseTarget typeChooseTarget(JsonNode a) {
        JsonNode message = a.path("message");
        JsonNode squares = a.path("S");
        List<SquareToJson> squaresList = new ArrayList<>();
        SquareToJson[][] mappa = new SquareToJson[5][5];
        for (int i = 0; i < squares.size(); i++) {
            JsonNode b = squares.get(i);
            String color = b.get(1).toString().replace("\"", "");
            SquareToJson square = new SquareToJson(b.get(0).asInt(), color, b.get(2).asInt(), b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p = new ParserUpdate();
        JsonNode fields = a.path("squares");
        mappa = p.parser(fields.toString()).getSquares();
        return new ChooseTarget(message.toString().replace("\"", ""), squaresList, mappa);
    }

    private ChooseWorker typeChooseWorker(JsonNode a) {
        JsonNode squares = a.path("S");
        List<SquareToJson> squaresList = new ArrayList<>();
        SquareToJson[][] mappa = new SquareToJson[5][5];
        for (int i = 0; i < squares.size(); i++) {
            JsonNode b = squares.get(i);
            String color = b.get(1).toString().replace("\"", "");
            SquareToJson square = new SquareToJson(b.get(0).asInt(), color, b.get(2).asInt(), b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p = new ParserUpdate();
        JsonNode fields = a.path("squares");
        mappa = p.parser(fields.toString()).getSquares();
        return new ChooseWorker(squaresList, mappa);
    }

    private ChooseYourGodEvent typeChooseYourGodEvent(JsonNode a) {
        JsonNode gods = a.path("gods");
        JsonNode effects = a.path("effects");
        List<String> godsList = new ArrayList<>();
        List<String> effectsList = new ArrayList<>();
        for (int i = 0; i < gods.size(); i++) {
            JsonNode b = gods.get(i);
            godsList.add(b.toString().replace("\"", ""));
        }
        for (int i = 0; i < effects.size(); i++) {
            JsonNode b = effects.get(i);
            effectsList.add(b.toString().replace("\"", ""));
        }
        return new ChooseYourGodEvent(godsList, effectsList);
    }

    private SetWorkerEvent typeSetWorkerEvent(JsonNode a) {
        JsonNode squares = a.path("S");
        List<SquareToJson> squaresList = new ArrayList<>();
        SquareToJson[][] mappa;
        for (int i = 0; i < squares.size(); i++) {
            JsonNode b = squares.get(i);
            String color = b.get(1).toString().replace("\"", "");
            SquareToJson square = new SquareToJson(b.get(0).asInt(), color, b.get(2).asInt(), b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p = new ParserUpdate();
        JsonNode fields = a.path("squares");
        mappa = p.parser(fields.toString()).getSquares();
        return new SetWorkerEvent(squaresList, mappa);
    }

    private StartMatchEvent typeStartMatchEvent(JsonNode a) {
        JsonNode link = a.path("linking");
        SquareToJson[][] mappa;
        LinkedHashMap<String, String> godPlayer = new LinkedHashMap<>();
        for (int i = 0; i < link.size(); i++) {
            JsonNode linking = link.get(i);
            JsonNode name = linking.path("nickname");
            JsonNode god = linking.path("godName");
            godPlayer.put(name.toString().replace("\"", ""), god.toString().replace("\"", ""));
        }
        ParserUpdate p = new ParserUpdate();
        JsonNode fields = a.path("squares");
        mappa = p.parser(fields.toString()).getSquares();
        return new StartMatchEvent(godPlayer);
    }

    private StartGameEvent typeStartGameEvent(JsonNode a) {
        JsonNode gods = a.path("gods");
        List<String> namesGod = new ArrayList<>();
        for (int i = 0; i < gods.size(); i++) {
            namesGod.add(gods.get(i).toString().replace("\"", ""));
        }
        JsonNode numPlayers = a.path("numPlayers");
        return new StartGameEvent(namesGod, numPlayers.asInt());
    }

    private LoginSuccessful typeLogin(JsonNode a) {
        JsonNode nicknames = a.path("nickname");
        List<String> nickList = new ArrayList<>();
        for (int i = 0; i < nicknames.size(); i++) {
            JsonNode b = nicknames.get(i);
            nickList.add(b.toString().replace("\"", ""));
        }
        return new LoginSuccessful(nickList);
    }

    private WaitYourGodEvent typeWaitYourGodEvent(JsonNode a) {
        JsonNode gods = a.path("gods");
        JsonNode effects = a.path("effects");
        List<String> godsList = new ArrayList<>();
        List<String> effectsList = new ArrayList<>();
        for (int i = 0; i < gods.size(); i++) {
            JsonNode b = gods.get(i);
            godsList.add(b.toString().replace("\"", ""));
        }
        for (int i = 0; i < effects.size(); i++) {
            JsonNode b = effects.get(i);
            effectsList.add(b.toString().replace("\"", ""));
        }
        return new WaitYourGodEvent(godsList, effectsList);
    }
}
