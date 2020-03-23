package it.polimi.ingsw.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class ParserJson {

    private ObjectMapper p;
    private final String path = "/god.json";

    public void apriFile(){

        FileLoader fileLoader = new FileLoader();
        ObjectMapper mapper = new ObjectMapper();

        InputStream jsonFile=fileLoader.getResource(path);
        try{
            ArrayList<God> usableGod=new ArrayList<>();
            JsonNode rootNode=mapper.readTree(jsonFile);
            JsonNode name=rootNode.path("name");
            Iterator<String> godIterator= rootNode.fieldNames();


            while(godIterator.hasNext()){
                String godName=godIterator.next();
                for(int i = 0;rootNode.path(godName).asInt()>i ; i++)
                    System.out.println(godName);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
