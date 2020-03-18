package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private Square [][] squares= new Square[5][5];
    private List<Square> availableSquares= new ArrayList<>();

    public Field() {
        this.initSquare();
    }

    private void initSquare(){

        for(int i=0; i<=4;i++)
            for(int j=0; j<=4;j++)
                squares [i][j]=new Square(i, j);

        /*for(int i=0; i<=4;i++)
            for(int j=0; j<=4;j++)*/

    }
}
