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

    public Event parser(String json){
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootNode=mapper.readTree(json);
            JsonNode commandName=rootNode.path("eventName");
            JsonNode attributes=rootNode.path("attributes");
            selectType(commandName.toString(),attributes);


        }catch(IOException e){
            e.printStackTrace();
        }
        return eventReturn;
    }

    public void selectType(String event, JsonNode a){
        String s=null;
        if(event.equals("\"askUser\"")){
            eventReturn= new askUser();
        }
        else if(event.equals("\"ChooseTarget\"")){
            eventReturn=typeChooseTarget(a);
        }
        else if(event.equals("\"ChooseWorker\"")){
            eventReturn=typeChooseWorker(a);
        }else if(event.equals("\"ChooseYourGodEvent\"")){
            eventReturn=typeChooseYourGodEvent(a);
        }
        else if(event.equals("\"ConnectionSuccessful\"")){
            eventReturn=new ConnectionSuccessful();
        }
        else if(event.equals("\"DeathPlayer\"")){
            JsonNode nick=a.path("nickname");
            eventReturn=new DeathPlayer(nick.toString().replace("\"",""));
        }
        else if(event.equals("\"EndGame\"")){
            JsonNode result=a.path("result");
            eventReturn=new EndGame(result.toString().replace("\"",""));
        }
        else if(event.equals("\"ExceptionEvent\"")){
            JsonNode exp=a.path("exception");
            eventReturn=new ExceptionEvent(exp.toString().replace("\"",""));
        }else if(event.equals("\"StartGameEvent\"")){
            eventReturn= typeStartGameEvent(a);
        }
        else if(event.equals("\"LoginSuccessful\"")){
            eventReturn=typeLogin(a);
        }
        else if(event.equals("\"LogoutSuccessful\"")){
            eventReturn=new LogoutSuccessful();
        }
        else if(event.equals("\"SettingsEvent\"")){
            eventReturn=new SettingsEvent();
        }
        else if(event.equals("\"SetWorkerEvent\"")){
            eventReturn=typeSetWorkerEvent(a);
        }
        else if(event.equals("\"StartMatchEvent\"")){
            eventReturn= typeStartMatchEvent(a);
        }
        else if(event.equals("\"UpdateEvent\"")){
            ParserUpdate p=new ParserUpdate();
            JsonNode fields=a.path("squares");
            eventReturn=p.parser(fields.toString());
        }
    }

    private ChooseTarget typeChooseTarget(JsonNode a){
        JsonNode message=a.path("message");
        JsonNode squares=a.path("S");
        List<SquareToJson> squaresList=new ArrayList<>();
        SquareToJson [][] mappa= new SquareToJson[5][5];
        for(int i=0;i<squares.size();i++){
            JsonNode b=squares.get(i);
            String color=b.get(1).toString().replace("\"","");
            SquareToJson square= new SquareToJson(b.get(0).asInt(),color,b.get(2).asInt(),b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p=new ParserUpdate();
        JsonNode fields=a.path("squares");
        mappa=p.parser(fields.toString()).getSquares();
        return new ChooseTarget(message.toString().replace("\"",""),squaresList,mappa);
    }

    private ChooseWorker typeChooseWorker(JsonNode a){
        JsonNode squares=a.path("S");
        List<SquareToJson> squaresList=new ArrayList<>();
        SquareToJson [][] mappa= new SquareToJson[5][5];
        for(int i=0;i<squares.size();i++){
            JsonNode b=squares.get(i);
            String color=b.get(1).toString().replace("\"","");
            SquareToJson square= new SquareToJson(b.get(0).asInt(),color,b.get(2).asInt(),b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p=new ParserUpdate();
        JsonNode fields=a.path("squares");
        mappa=p.parser(fields.toString()).getSquares();
        return new ChooseWorker(squaresList,mappa);
    }

    private ChooseYourGodEvent typeChooseYourGodEvent(JsonNode a){
        JsonNode gods=a.path("gods");
        JsonNode effects=a.path("effects");
        List<String> godsList=new ArrayList<>();
        List<String> effectsList=new ArrayList<>();
        for(int i=0;i<gods.size();i++){
            JsonNode b=gods.get(i);
            godsList.add(b.toString().replace("\"",""));
        }
        for(int i=0;i<effects.size();i++){
            JsonNode b=effects.get(i);
            effectsList.add(b.toString().replace("\"",""));
        }
        return new ChooseYourGodEvent(godsList,effectsList);
    }

    private SetWorkerEvent typeSetWorkerEvent(JsonNode a){
        JsonNode squares=a.path("S");
        List<SquareToJson> squaresList=new ArrayList<>();
        SquareToJson [][] mappa;
        for(int i=0;i<squares.size();i++){
            JsonNode b=squares.get(i);
            String color=b.get(1).toString().replace("\"","");
            SquareToJson square= new SquareToJson(b.get(0).asInt(),color,b.get(2).asInt(),b.get(3).asInt());
            squaresList.add(square);
        }
        ParserUpdate p=new ParserUpdate();
        JsonNode fields=a.path("squares");
        mappa=p.parser(fields.toString()).getSquares();
        return new SetWorkerEvent(squaresList,mappa);
    }

    private StartMatchEvent typeStartMatchEvent(JsonNode a){
        JsonNode link=a.path("linking");
        JsonNode squares=a.path("squares");
        LinkedHashMap<String,String> godPlayer=new LinkedHashMap<>();
        for(int i=0;i<link.size();i++){
            JsonNode linking=link.get(i);
            JsonNode name=linking.path("nickname");
            JsonNode god=linking.path("godName");
            godPlayer.put(name.toString().replace("\"",""),god.toString().replace("\"",""));
        }
        return new StartMatchEvent(godPlayer);
    }

    private StartGameEvent typeStartGameEvent(JsonNode a){
        JsonNode gods=a.path("gods");
        List<String> namesGod=new ArrayList<>();
        for(int i=0;i<gods.size();i++) {
            namesGod.add(gods.get(i).toString().replace("\"", ""));
        }
        JsonNode numPlayers=a.path("numPlayers");
        return new StartGameEvent(namesGod,numPlayers.asInt());
    }

    private LoginSuccessful typeLogin(JsonNode a){
        JsonNode nicknames=a.path("nickname");
        List<String> nickList=new ArrayList<>();
        for(int i=0;i<nicknames.size();i++){
            JsonNode b=nicknames.get(i);
            nickList.add(b.toString().replace("\"",""));
        }
        return new LoginSuccessful(nickList);
    }
}
