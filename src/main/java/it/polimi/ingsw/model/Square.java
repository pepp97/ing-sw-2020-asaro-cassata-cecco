package it.polimi.ingsw.model;

/**
 * represents a cell of square's matrix contained in feld
 * @author Giuseppe Asaro
 *
 */

public class Square implements Target {

    /**
     *  coordinate of  column in the matrix
     */
    private int coordinateX;

    /**
     *  coordinate of raw in the matrix
     */
    private int coordinateY;

    /**
     * the real-time level of the square, the value is in (0,1,2,3,4)
     */
    private int  level;

    /**
     * the level at the begin of the turn of this square
     */
    private int start_level;

    /**
     * the worker contained in this square
     */
    private Worker worker;

    /**
     * an array containing each square adjacent to this; adjacentSquare [0] is the one on the top-left and so on
     */
    private Square [] adjacentSquares= new Square[8];

    /**
     *
     * @param coordinateX
     * @param coordinateY
     * default constructor, the level at first is set to 0
     */
    public Square(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.level=0;
    }



    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStart_level() {
        return start_level;
    }

    public void setStart_level(int start_level) {
        this.start_level = start_level;
    }

    public Worker getWorker(){
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /**
     *
     * @param index the index in array to order to estabilish the cardinal order
     * @param square the actual adjacent square
     */
    public void setAdjacentSquares (int index, Square square){
        adjacentSquares [index]=square;
    }

    public Square[] getAdjacentSquares() {
        return adjacentSquares;
    }

    @Override
    public Square getSquare() {
        return this;
    }

    /**
     * this method is used to remove a worker that decide to change is position
     */
    public void removeWorker(){
        this.worker=null;
    }
}
