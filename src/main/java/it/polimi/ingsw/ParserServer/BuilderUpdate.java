package it.polimi.ingsw.ParserServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.polimi.ingsw.model.Square;
import com.fasterxml.jackson.databind.*;
import it.polimi.ingsw.model.Worker;

import java.io.IOException;
import java.util.ArrayList;


public class BuilderUpdate {


    private Square[][] squares;

    public BuilderUpdate(Square [][] squares) {
        this.squares = squares;
    }

    public void builder(){
        int levels;
        Worker work=null;
        ArrayList<SquareToJson> list=new ArrayList<>();
        for(int i=0;i<squares.length;i++) {
            for (int j = 0; j < squares[i].length; j++) {
                levels = squares[i][j].getLevel();
                work = squares[i][j].getWorker();
                SquareToJson pass;
                if(work!=null)
                    pass = new SquareToJson(levels, work.getC().toString(), i, j);
                else
                    pass = new SquareToJson(levels, "", i, j);
                list.add(pass);
            }
        }
        createJson(list);


    }


    public void createJson(ArrayList<SquareToJson> list){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(list);
            System.out.println(jsonStr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


/*
        for(int i=0;i<squares.length;i++) {
            for (int j = 0; j < squares[i].length; j++) {
                levels = squares[i][j].getLevel();
                work = squares[i][j].getWorker();
                SquareToJson pass = new SquareToJson(levels, c, i, j);
                if(i!=(squares.length-1)){
                    System.out.println(pass.toString()+",");
                }else{
                    System.out.println(pass.toString());
                }

            }
        }
*/