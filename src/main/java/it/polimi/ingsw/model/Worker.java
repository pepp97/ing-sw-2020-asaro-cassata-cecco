package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;


/**
 * this class represent the worke assigned to a Player
 *
 * @author Riccardo Cecco
 */

public class Worker implements Target {

    /**
     * it is the color of the worker
     */
    private Color c;
    /**
     * it is true if worker can be moved, false otherwise
     */
    private boolean canBeMoved;
    /**
     * it is true if worker can go up, false otherwise
     */
    private boolean canMoveUp;
    /**
     * it is true if worker can build, false otherwise
     */
    private boolean canBuild;
    /**
     * it is the actual square of the worker
     */
    private Square actualPos;
    /**
     * it is the list of the history pos of the turn of the worker
     */
    private List<Square> historyPos = new ArrayList<>();
    private List<Square> targetNotValid;
    /**
     * it is a square that isn't available for the worke
     */
    private Square squareNotAvailable;
    /**
     * it is a mandatory square of the worker
     */
    private Square mandatorySquare;

    /**
     * this metod is the builder of the class
     */
    public Worker() {
        this.canBeMoved = true;
        this.canMoveUp = true;
        this.canBuild = true;
        this.targetNotValid = new ArrayList<>();
    }



    /**
     * this method is used to find the position of the worker
     *
     * @return the actual position of the worker
     */

    @Override
    public Square getSquare() {
        return this.actualPos;
    }

    public Square getMandatorySquare() {
        return mandatorySquare;
    }

    public void setMandatorySquare(Square mandatorySquare) {
        this.mandatorySquare = mandatorySquare;
    }

    /**
     * this method is used to update the position of the worker for every movement
     *
     * @param pos is the position where is the pawn
     */

    //questo metodo mi serve per aggiornare la posizione del worker ad ogni suo spostamento
    public void setActualPos(Square pos) {
        if (pos.getLevel() < 4 && pos.getCoordinateX() < 5 && 0 <= pos.getCoordinateY() && 0 <= pos.getCoordinateX() && pos.getCoordinateY() < 5) {
            this.actualPos = pos;
            aggiornaPos(pos); //aggiungo alla lista della cronologia delle posizioni quella corrente
        }
    }

    /**
     * this method is used to update the list that records each movement in the round of the player
     *
     * @param pos new position of the worker
     */
    //questo metodo mi serve per aggiornare  la lista delle cronologia delle posizioni del worker nel turno
    private void aggiornaPos(Square pos) {
        if (pos.getLevel() < 4 && pos.getCoordinateX() < 5 && 0 <= pos.getCoordinateY() && 0 <= pos.getCoordinateX() && pos.getCoordinateY() < 5)
            this.historyPos.add(pos);
    }

    /**
     * this method is used by controller to reset the history of the worker position of from the previous round
     */
    //questo metodo verrà chiamato dal controller ogni turno per resettare la cronologia delle posizioni del worker
    //prima dell'inizio di un nuovo turno turno e terrà nella lista solo la posizione attuale del worker
    public void resetHystoricPos() {
        this.historyPos.clear();
        this.historyPos.add(this.actualPos);
    }


    //metodi Setter
    public void setC(Color c) {
        this.c = c;
    }

    public void setCanBeMoved(boolean canBeMoved) {
        this.canBeMoved = canBeMoved;
    }

    public void setCanMoveUp(boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    public void setTargetNotValid(List<Square> targetNotValid) {
        this.targetNotValid = targetNotValid;
    }

    public void setSquareNotAvailable(Square squareNotAvailable) {
        this.squareNotAvailable = squareNotAvailable;
    }

    //metodi Getter
    public Color getC() {
        return c;
    }

    public boolean getCanBeMoved() {
        return canBeMoved;
    }

    public boolean getCanMoveUp() {
        return canMoveUp;
    }

    public boolean getCanBuild() {
        return canBuild;
    }

    public List<Square> getHistoryPos() {
        return List.copyOf(historyPos);
    }



    public Square getSquareNotAvailable() {
        return squareNotAvailable;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return c == worker.c &&
                actualPos.equals(worker.actualPos);
    }

    public void removeSquare(int i) {
        historyPos.remove(i);
    }
}