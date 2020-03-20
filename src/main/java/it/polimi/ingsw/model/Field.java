package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
/**
 * represents the entire game's field
 * @author Giuseppe Asaro
 *
 */

public class Field {

    public static void main(String[] args) {
        Field field=new Field();
    }

    /**
     * the matrix of the squares that are in the field
     */
    private Square[][] squares = new Square[5][5];

    /**
     * this list contains the available square used for the management of the main action (build & move)
     * it is given from "availableUpgrade" and "availableMovement" methods
     */
    private List<Square> availableSquares = new ArrayList<>();

    /**
     * through the default constructor is called the initSquare method.
     */
    public Field() {
            this.initSquare();
        }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Square> getAvailableSquares() {
        return availableSquares;
    }

    /**
     * this private method is used to init the square's matrix and fill the list of adjacent squares for each square
     */
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


        }

}





