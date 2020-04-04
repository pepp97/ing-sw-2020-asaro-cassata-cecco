package it.polimi.ingsw;

import it.polimi.ingsw.ParserServer.BuilderUpdate;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.model.ParserJson;

import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuilderUpdateTest {
    private BuilderUpdate p;

    @Test
    void parser(){
        Square squares[][]=new Square[5][5];
        squares[0][0]= new Square(0,0);
        squares[0][1]= new Square(0,0);
        squares[0][2]= new Square(0,0);
        squares[0][3]= new Square(0,0);
        squares[0][4]= new Square(0,0);

        squares[1][0]= new Square(0,0);
        squares[1][1]= new Square(0,0);
        squares[1][2]= new Square(0,0);
        squares[1][3]= new Square(0,0);
        squares[1][4]= new Square(0,0);

        squares[2][0]= new Square(0,0);
        squares[2][1]= new Square(0,0);
        squares[2][2]= new Square(0,0);
        squares[2][3]= new Square(0,0);
        squares[2][4]= new Square(0,0);

        squares[3][0]= new Square(0,0);
        squares[3][1]= new Square(0,0);
        squares[3][2]= new Square(0,0);
        squares[3][3]= new Square(0,0);
        squares[3][4]= new Square(0,0);

        squares[4][0]= new Square(0,0);
        squares[4][1]= new Square(0,0);
        squares[4][2]= new Square(0,0);
        squares[4][3]= new Square(0,0);
        squares[4][4]= new Square(0,0);

        Worker a1=new Worker();
        Worker a2=new Worker();
        Worker b1=new Worker();
        Worker b2=new Worker();
        Worker c1=new Worker();
        Worker c2=new Worker();

        a1.setC(Color.Black);
        a2.setC(Color.Black);
        b1.setC(Color.Brown);
        b2.setC(Color.Brown);
        c1.setC(Color.White);
        c2.setC(Color.White);

        squares[0][0].setWorker(a1);
        squares[4][1].setWorker(a2);

        squares[3][1].setWorker(b1);
        squares[2][1].setWorker(b2);

        squares[4][0].setWorker(c1);
        squares[3][3].setWorker(c2);

        Square square= new Square(0,0);
        String c="Black";
        p=new BuilderUpdate(squares);
        p.parser();
    }
}


