package it.polimi.ingsw.ParserServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.events.*;

import java.io.IOException;

public class BuilderEvent {

    public void builder(Event event)  {
        ObjectMapper mapper = new ObjectMapper();
        String attributes=null;
        String type=selectType(event);
        String json=null;
        try{
            if(!type.equals("askUser") && !type.equals("ConnectionSuccessful") && !type.equals("LogoutSuccessful") && !type.equals("SettingsEvent"))
                attributes = mapper.writeValueAsString(event);
        }catch(IOException e){
            e.printStackTrace();
        }
        if(attributes==null)
            json="{" + " \" eventName\" :\"" + type+ "\" }";
        else{
            json="{" + " \" eventName\" :\"" + type+ "\"," + "\"attributi\" :"+attributes+"}";
        }
        System.out.println(json);
    }

    public String selectType(Event event){
        String s=null;
        if(event instanceof askUser){
            s="askUser";
        }else if(event instanceof ChooseTarget){
            s="ChooseTarget";
        }else if(event instanceof ChooseWorker){
            s="ChooseWorker";
        }else if(event instanceof ConnectionSuccessful){
            s="ConnectionSuccessful";
        }else if(event instanceof DeathPlayer){
            s="DeathPlayer";
        } else if(event instanceof EndGame){
            s="EndGame";
        }else if(event instanceof ExceptionEvent){
            s="ExceptionEvent";
        }else if(event instanceof LoginSuccessful){
            s="LoginSuccessful";
        }else if(event instanceof LogoutSuccessful){
            s="LogoutSuccessful";
        }else if(event instanceof SettingsEvent){
            s="SettingsEvent";
        }else if(event instanceof StartGameEvent){
            s="StartGameEvent";
        }else if(event instanceof UpdateEvent){
            s="UpdateEvent";
        }
        return s;
    }
}
