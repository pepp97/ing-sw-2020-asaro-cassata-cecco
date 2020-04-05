package it.polimi.ingsw.ParserClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.commands.*;
import java.io.IOException;


public class BuilderCommand {

    public void builder(Command command)  {
        ObjectMapper mapper = new ObjectMapper();
        String attributes=null;
        String type=selectType(command);
        String json=null;
        try{
            if(!type.equals("Connection") && !type.equals("Disconnection"))
                attributes = mapper.writeValueAsString(command);
        }catch(IOException e){
            e.printStackTrace();
        }
        if(attributes==null)
            json="{" + " \" commandName\" :\"" + type+ "\" }";
        else{
            json="{" + " \" commandName\" :\"" + type+ "\"," + "\"attributi\" :"+attributes+"}";
        }
        System.out.println(json);
    }

    public String selectType(Command command){
        String s=null;
        if(command instanceof ChooseGods){
            s="ChooseGods";
        }else if(command instanceof ChooseInitialPosition){
            s="ChooseInitialPosition";
        }else if(command instanceof ChooseSettings){
            s="ChooseSettings";
        }else if(command instanceof ChooseTarget){
            s="ChooseTarget";
        }else if(command instanceof ChooseYourGod){
            s="ChooseYourGod";
        } else if(command instanceof ChooseYourWorker){
            s="ChooseYourWorker";
        }else if(command instanceof Connection){
            s="Connection";
        }else if(command instanceof Disconnection){
            s="Disconnection";
        }else if(command instanceof LoginCommand){
            s="LoginCommand";
        }else if(command instanceof UseEffect){
            s="UseEffect";
        }
        return s;
    }
}
