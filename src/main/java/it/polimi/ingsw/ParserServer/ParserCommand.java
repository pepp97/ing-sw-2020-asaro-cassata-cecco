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

        try{
            JsonNode rootNode=mapper.readTree(json);
            JsonNode commandName=rootNode.path("commandName");
            JsonNode attributes =rootNode.path("attributes");
            selectType(commandName.toString(),attributes);


        }catch(IOException e){
            e.printStackTrace();
        }

        return commandReturn;
    }

    public void selectType(String command, JsonNode a){
        String s=null;
        if(command.equals("\"ChooseGods\"")){
            JsonNode namesGod=a.path("namesGod");
            List<String> namesGodList=new ArrayList<>();
            for(int i=0;i<namesGod.size();i++){
                JsonNode b=namesGod.get(i);
                namesGodList.add(b.toString().replace("\"",""));
            }
            commandReturn=new ChooseGods(namesGodList);
        }else if(command.equals("\"ChooseInitialPosition\"")){
            JsonNode posX=a.path("coordinateX");
            JsonNode posY=a.path("coordinateY");
            commandReturn=new ChooseInitialPosition(posX.asInt(),posY.asInt());
        }else if(command.equals("\"ChooseSettings\"")){
            JsonNode nplayer=a.path("nplayer");
            commandReturn=new ChooseSettings(nplayer.asInt());
        }else if(command.equals("\"ChooseTarget\"")){
            JsonNode posX=a.path("coordinateX");
            JsonNode posY=a.path("coordinateY");
            commandReturn=new ChooseTarget(posX.asInt(),posY.asInt());
        }else if(command.equals("\"ChooseYourGod\"")){
            JsonNode name=a.path("name");
            commandReturn=new ChooseYourGod(name.toString().replace("\"",""));
        } else if(command.equals("\"ChooseYourWorker\"")){
            JsonNode posX=a.path("coordinateX");
            JsonNode posY=a.path("coordinateY");
            commandReturn=new ChooseYourWorker(posX.asInt(),posY.asInt());
        }else if(command.equals("\"Connection\"")){
            commandReturn=new Connection();
        }else if(command.equals("\"Disconnection\"")){
            commandReturn=new Disconnection();
        }else if(command.equals("\"LoginCommand\"")){
            JsonNode nickname=a.path("nickname");
            JsonNode col=a.path("color");
            String cal=col.toString().replace("\"","");
            Color c=null;
            if(cal.equals("BLACK")){
                c=Color.BLACK;
            }else if(cal.equals("WHITE")){
                c=Color.WHITE;
            }else if(cal.equals("BROWN")){
                c=Color.BROWN;
            }
            commandReturn=new LoginCommand(nickname.toString().replace("\"",""),c);
        }else if(command.equals("\"UseEffect\"")){
            JsonNode reply=a.path("reply");
            commandReturn=new UseEffect(reply.asBoolean());
        }
    }
}
