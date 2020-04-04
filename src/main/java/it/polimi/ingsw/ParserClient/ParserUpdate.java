package it.polimi.ingsw.ParserClient;

import it.polimi.ingsw.ParserServer.SquareToJson;
import com.fasterxml.jackson.databind.*;
import it.polimi.ingsw.model.Square;

import java.io.IOException;
import java.util.ArrayList;

public class ParserUpdate {
    private SquareToJson [][] mappa;

    public ParserUpdate(String json)  {
        mappa=new SquareToJson [5][5] ;
        ObjectMapper mapper = new ObjectMapper();
        int k=0,j=0;

        try{
            JsonNode rootNode=mapper.readTree(json);
            for (int i=0;i<25;i++) {
                JsonNode square = rootNode.get(i);

                JsonNode cordinataX = square.path("cordinataX");
                JsonNode cordinataY = square.path("cordinataY");
                JsonNode levels = square.path("levels");
                JsonNode color = square.path("color");

                SquareToJson pass=new SquareToJson(levels.asInt(),color.toString(),cordinataX.asInt(),cordinataY.asInt());
                mappa[k][j]=pass;
                if(j==4){
                    k++;
                    j=0;
                }else{
                    j++;
                }
            }
            print();
        }catch(IOException e){
            e.printStackTrace();
        }



    }

    public void print(){
        for (SquareToJson[] squareToJsons : mappa) {
            for (SquareToJson squareToJson : squareToJsons) {
                System.out.println(squareToJson.toString());
            }
        }
    }

}
