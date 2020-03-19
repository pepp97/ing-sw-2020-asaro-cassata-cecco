package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    public static void main(String[] args) {
        Field field=new Field();
    }

    private Square[][] squares = new Square[5][5];
    private List<Square> availableSquares = new ArrayList<>();


    public Field() {
            this.initSquare();
        }

    public Square[][] getSquares() {
        return squares;
    }

    private void initSquare () {

            for (int i = 0; i <= 4; i++)
                for (int j = 0; j <= 4; j++)
                    squares[i][j] = new Square(i, j);

            for (int i = 0; i <= 4; i++)
                for (int j = 0; j <= 4; j++) {
                    if(i>0 && j>0)
                     squares[i][j].setAdjacentSquares( squares[i - 1][j - 1]);
                    if(j>0)
                     squares[i][j].setAdjacentSquares( squares[i][j - 1]);
                    if(j>0 && i<4)
                        squares[i][j].setAdjacentSquares( squares[i + 1][j - 1]);
                    if(i>0)
                        squares[i][j].setAdjacentSquares( squares[i - 1][j]);
                    if(i<4)
                        squares[i][j].setAdjacentSquares( squares[i + 1][j]);
                     if(i>0 && j<4)
                        squares[i][j].setAdjacentSquares( squares[i - 1][j + 1]);
                    if(j<4)
                        squares[i][j].setAdjacentSquares( squares[i][j + 1]);
                    if(i<4 && j<4)
                        squares[i][j].setAdjacentSquares( squares[i + 1][j + 1]);
                }

            availableMovement(squares[0][0]);
        }

        public void availableMovement(Square square){
            for(Square s: square.getAdjacentSquares())
                if(s.getWorker()==null && (s.getLevel()-square.getLevel())==1 && s.getLevel()!=4)
                    availableSquares.add(s);
        }

        public void availableUpgrade(Square square){
            for(Square s: square.getAdjacentSquares())
                if(s.getLevel()!=4)
                    availableSquares.add(s);
        }

    }

