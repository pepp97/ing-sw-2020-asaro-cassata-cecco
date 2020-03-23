package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserJsonTest {

        private ParserJson p;


        @Test
        public void parser(){
            p=new ParserJson();
            p.apriFile();
        }
}


