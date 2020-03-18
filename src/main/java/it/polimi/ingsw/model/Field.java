package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Field {

    public static void main(String[] args) {
        Field field=new Field();
        System.out.println("aaaaaaaaaaa");
       // System.out.println(field.getAvailableSquares().size());
    }

    private Square[][] squares = new Square[5][5];
    private List<Square> availableSquares = new ArrayList<>();


    public Field() {
            this.initSquare();
        }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Square> getAvailableSquares() {
        return availableSquares;
    }

    private void initSquare () {

            for (int i = 0; i <= 4; i++)
                for (int j = 0; j <= 4; j++)
                    squares[i][j] = new Square(i, j);

            for (int i = 0; i <= 4; i++)
                for (int j = 0; j <= 4; j++) {
                    if(i>0 && j>0)
                     squares[i][j].setAdjacentSquares(0, squares[i - 1][j - 1]);
                    if(j>0)
                     squares[i][j].setAdjacentSquares(1, squares[i][j - 1]);
                    if(j>0 && i<4)
                        squares[i][j].setAdjacentSquares(2, squares[i + 1][j - 1]);
                    if(i>0)
                        squares[i][j].setAdjacentSquares(3, squares[i - 1][j]);
                    if(i<4)
                        squares[i][j].setAdjacentSquares(4, squares[i + 1][j]);
                     if(i>0 && j<4)
                        squares[i][j].setAdjacentSquares(5, squares[i - 1][j + 1]);
                    if(j<4)
                        squares[i][j].setAdjacentSquares(6, squares[i][j + 1]);
                    if(i<4 && j<4)
                        squares[i][j].setAdjacentSquares(7, squares[i + 1][j + 1]);
                }

            //availableMovement(squares[0][0]);
        }

        public void availableMovement(Square square){
            for(Square s: square.getAdjacentSquares())
                if(s.getWorker()==null)
                    availableSquares.add(s);
        }

    }

