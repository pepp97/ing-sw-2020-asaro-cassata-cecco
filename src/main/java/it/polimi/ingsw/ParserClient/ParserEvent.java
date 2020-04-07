package it.polimi.ingsw.ParserClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Square;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserEvent {
    private Event event;

    public void parser(String json){
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootNode=mapper.readTree(json);
            JsonNode commandName=rootNode.path("eventName");
            JsonNode attributes=rootNode.path("attributes");
            selectType(commandName.toString(),attributes);


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void selectType(String event, JsonNode a){
        String s=null;
        if(event.equals("\"askUser\"")){
            askUser askUser= new askUser();
        }
        else if(event.equals("\"ChooseTarget\"")){
            JsonNode message=a.path("message");
            JsonNode squares=a.path("S");
            List<Square> squaresList=new ArrayList<>();
            for(int i=0;i<squares.size();i++){
                JsonNode b=squares.get(i);
                Square square= new Square(b.get(0).asInt(),b.get(1).asInt());
                squaresList.add(square);
            }
            ChooseTarget chooseTarget=new ChooseTarget(message.toString().replace("\"",""),squaresList);
        }
        else if(event.equals("\"ChooseWorker\"")){
            JsonNode squares=a.path("S");
            List<Square> squaresList=new ArrayList<>();
            for(int i=0;i<squares.size();i++){
                JsonNode b=squares.get(i);
                Square square= new Square(b.get(0).asInt(),b.get(1).asInt());
                squaresList.add(square);
            }
            ChooseWorker chooseWorker=new ChooseWorker(squaresList);
        }else if(event.equals("\"ChooseYourGodEvent\"")){
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
            ChooseYourGodEvent chooseYourGodEvent=new ChooseYourGodEvent(godsList,effectsList);
        }
        else if(event.equals("\"ConnectionSuccessful\"")){
            ConnectionSuccessful connectionSuccessful=new ConnectionSuccessful();
        }
        else if(event.equals("\"DeathPlayer\"")){
            JsonNode nick=a.path("nickname");
            DeathPlayer deathPlayer=new DeathPlayer(nick.toString().replace("\"",""));
        }
        else if(event.equals("\"EndGame\"")){
            JsonNode result=a.path("result");
            EndGame endGame=new EndGame(result.toString().replace("\"",""));
        }
        else if(event.equals("\"ExceptionEvent\"")){
            JsonNode exp=a.path("exception");
            ExceptionEvent endGame=new ExceptionEvent(exp.toString().replace("\"",""));
        }
        else if(event.equals("\"LoginSuccessful\"")){
            JsonNode nicknames=a.path("nickname");
            List<String> nickList=new ArrayList<>();
            for(int i=0;i<nicknames.size();i++){
                JsonNode b=nicknames.get(i);
                nickList.add(b.toString().replace("\"",""));
            }
            LoginSuccessful loginSuccessful=new LoginSuccessful(nickList);
        }
        else if(event.equals("\"LogoutSuccessful\"")){
            LogoutSuccessful logoutSuccessful=new LogoutSuccessful();
        }
        else if(event.equals("\"SettingsEvent\"")){
            SettingsEvent settingsEvent=new SettingsEvent();
        }
        else if(event.equals("\"StartGameEvent\"")){
            JsonNode gods=a.path("gods");
            List<String> namesGod=new ArrayList<>();
            for(int i=0;i<gods.size();i++){
                namesGod.add(gods.get(i).toString().replace("\"",""));
            }
            JsonNode numPlayers=a.path("numPlayers");
            StartGameEvent startGameEvent= new StartGameEvent(namesGod,numPlayers.asInt());
        }
        else if(event.equals("\"UpdateEvent\"")){
            s="UpdateEvent";
        }
    }
}
