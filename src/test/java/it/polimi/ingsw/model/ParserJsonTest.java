package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserJsonTest {

        private ParserJson p;


        @Test
         void parser(){
            p=new ParserJson();
            System.out.println(p.getUsableGod().size());
        }
}


