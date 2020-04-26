package it.polimi.ingsw.model;

import java.io.InputStream;

/**
 * This class is use to get Resource from the right package
 * @author cecco
 */
public class FileLoader {

    public InputStream getResource(String path){
        return getClass().getResourceAsStream(path);
    }
}
