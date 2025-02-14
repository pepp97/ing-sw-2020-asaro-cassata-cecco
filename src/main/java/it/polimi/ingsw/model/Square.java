package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represents a cell of square's matrix contained in field
 *
 * @author Giuseppe Asaro
 */

public class Square implements Target {

    /**
     * coordinate of  column in the matrix
     */
    private int coordinateX;

    /**
     * coordinate of raw in the matrix
     */
    private int coordinateY;

    /**
     * the real-time level of the square, the value is in (0,1,2,3,4)
     */
    private int level;

    /**
     * the level at the begin of the turn of this square
     */
    private int start_level;

    /**
     * the worker contained in this square
     */
    private Worker worker;

    /**
     * a list containing each square adjacent to this
     */
    private List<Square> adjacentSquares = new ArrayList<>();

    /**
     * @param coordinateX
     * @param coordinateY default constructor, the level at first is set to 0
     */
    public Square(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.level = 0;
    }


    public void setLevel(int level) {
        if (level >= 0 && level < 5)
            this.level = level;
    }

    public int getLevel() {
        return level;
    }

    /**
     * this metod is called to build a new level on the field
     */
    public void upgrade() {
        if (level <= 3)
            level++;
    }

    public int getStart_level() {
        return start_level;
    }

    public void setStart_level(int start_level) {
        this.start_level = start_level;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        if(worker!=null){
        this.worker = worker;
        worker.setActualPos(this);
        }
    }

    /**
     * @param square the actual adjacent square
     */
    public void setAdjacentSquares(Square square) {
        if (square.level == 0)
            adjacentSquares.add(square);
    }

    public List<Square> getAdjacentSquares() {
        return List.copyOf(adjacentSquares);
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public Square getSquare() {
        return this;
    }

    /**
     * this method is used to remove a worker that decide to change is position
     */
    public void removeWorker() {
        this.worker = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square square = (Square) o;
        return coordinateX == square.coordinateX &&
                coordinateY == square.coordinateY;
    }

}
