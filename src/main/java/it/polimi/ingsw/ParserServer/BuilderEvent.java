package it.polimi.ingsw.ParserServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Square;

import java.io.IOException;
import java.util.List;

public class BuilderEvent {

    public void builder(Event event)  {
        ObjectMapper mapper = new ObjectMapper();
        String attributes=null;
        String type=selectType(event);
        String json=null;
        try {
            if(type.equals("ChooseTarget")) {
                attributes=typeTarget(event);
            } else if(type.equals("ChooseWorker")){
                attributes=typeWorker(event);
            }
            else if(!type.equals("askUser") && !type.equals("ConnectionSuccessful") && !type.equals("LogoutSuccessful") && !type.equals("SettingsEvent"))
                attributes = mapper.writeValueAsString(event);
        }catch(IOException e){
            e.printStackTrace();
        }
        json=buildjsonString(attributes,type);
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
        }else if(event instanceof ChooseYourGodEvent){
            s="ChooseYourGodEvent";
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

    public String typeWorker(Event event){
        ChooseWorker w1=(ChooseWorker) event;
        List<Square> squares=w1.getPosWorker();
        String attributes=null;
        attributes="{";
        attributes=attributes+"\"S\":[";
        for (int i=0;i<squares.size();i++) {
            int x = squares.get(i).getCoordinateX();
            int y = squares.get(i).getCoordinateY();
            if(i==squares.size()-1){
                attributes=attributes+"["+x+","+y+"]";
            }else{
                attributes=attributes+"["+x+","+y+"],";
            }
        }
        attributes=attributes+"]}";
        return attributes;
    }

    public String typeTarget(Event event){
        ChooseTarget t1=(ChooseTarget) event;
        String message=t1.getMessage();
        List<Square> availableSquare=t1.getAvailableSquare();
        String attributes=null;
        attributes="{\"message\":\""+message+"\",";
        attributes=attributes+"\"S\":[";
        for (int i=0;i<availableSquare.size();i++) {
            int x = availableSquare.get(i).getCoordinateX();
            int y = availableSquare.get(i).getCoordinateY();
            if(i==availableSquare.size()-1){
                attributes=attributes+"["+x+","+y+"]";
            }else{
                attributes=attributes+"["+x+","+y+"],";
            }
        }
        attributes=attributes+"]}";
        return attributes;
    }


    public String buildjsonString(String attributes,String type){
        String json;
        if(attributes==null)
            json="{" + "\"eventName\":\"" + type+ "\"}";
        else{
            json="{" + "\"eventName\":\"" + type+ "\"," + "\"attributes\":"+attributes+"}";
        }
        return json;

    }
}
