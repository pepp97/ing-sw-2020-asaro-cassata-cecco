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

            availableMovement(squares[0][0]);
        }


    /**
     *
     * @param square is the square from which the user's worker executes is his movement
     *               it fills the availableSquare list.
     */
    public void availableMovement(Square square){
            for(Square s: square.getAdjacentSquares())
                if(s.getWorker()==null && (s.getLevel()-square.getLevel())==1 && s.getLevel()!=4)
                    availableSquares.add(s);
        }
    /**
     *
     * @param square is the square from which the user's worker executes is his build
     *               it fills the availableSquare list.
     */
        public void availableUpgrade(Square square){
            for(Square s: square.getAdjacentSquares())
                if(s.getLevel()!=4)
                    availableSquares.add(s);
        }

    /**
     *
     * to clean the list at the end of each action
     */

    public void cleanList(){
            this.availableSquares= new ArrayList<>();
            return;
        }

    }

