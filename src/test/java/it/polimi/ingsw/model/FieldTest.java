package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;


public class FieldTest {

    private Field field;

    @Before
    public void initField(){
        field=new Field();
    }

    @Test
    public void correctSquareManagement(){
        Square [][]s=field.getSquares();
        assertTrue(s[1][1].getAdjacentSquares().contains(s[0][0]));
        assertFalse(s[1][1].getAdjacentSquares().contains(s[3][3]));
    }

}
