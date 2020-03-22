package it.polimi.ingsw.model;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FieldTest {

    private Field field;


    @Test
    public void correctSquareManagement(){
        field=new Field();
        Square [][]s=field.getSquares();
        assertTrue(s[1][1].getAdjacentSquares().contains(s[0][0]));
        assertFalse(s[1][1].getAdjacentSquares().contains(s[3][3]));
    }

}
