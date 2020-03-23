package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserJsonTest {

        private ParserJson p;

        @Before
        public void initField(){
            p=new ParserJson();
        }

        @Test
        public void parser(){
            p.apriFile();
        }
}


