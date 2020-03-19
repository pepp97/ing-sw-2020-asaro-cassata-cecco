package it.polimi.ingsw.model;

import java.util.List;


/**
 * rapresent the pawns of the Player
 * @author Riccardo Cecco
 */

public class Worker implements Target {

    private Color c;
    private boolean canBeMoved;
    private boolean canMoveUp;
    private boolean canBuild;
    private Square actualPos;
    private List<Square> historyPos;

    /**
     * this metod is the builder of the class
     * @param c color of the worker
     */
    public Worker(Color c) {
        this.c = c;
        this.canBeMoved = true;
        this.canMoveUp = true;
        this.canBuild = true;
        this.actualPos = null;
        this.historyPos=null;
    }

    @Override
    public Square getSquare() {
        return this.actualPos;
    }


    /**
     * this method is used to update the position of the worker for every movement
     * @param pos is the position where is the pawn
     */

    //questo metodo mi serve per aggiornare la posizione del worker ad ogni suo spostamento
    public void setActualPos(Square pos){
        this.actualPos=pos;
        aggiornaPos(pos); //aggiungo alla lista della cronologia delle posizioni quella corrente
    }

    /**
     * this method is used to update the list that records each movement in the round of the player
     * @param pos new position of the worker
     */
    //questo metodo mi serve per aggiornare  la lista delle cronologia delle posizioni del worker nel turno
    public void aggiornaPos(Square pos){ this.historyPos.add(pos); }

    /**
     * this method is used by controller to reset the history of the worker position of from the previous round
     *
     */
    //questo metodo verrà chiamato dal controller ogni turno per resettare la cronologia delle posizioni del worker
    //prima dell'inizio di un nuovo turno turno e terrà nella lista solo la posizione attuale del worker

    public void resetHystoricPos(){
        this.historyPos.clear();
        this.historyPos.add(this.actualPos);
    }



    //metodi Setter
    public void setC(Color c) { this.c = c; }

    public void setCanBeMoved(boolean canBeMoved) { this.canBeMoved = canBeMoved; }

    public void setCanMoveUp(boolean canMoveUp) { this.canMoveUp = canMoveUp; }

    public void setCanBuild(boolean canBuild) { this.canBuild = canBuild; }



    //metodi Getter
    public Color getC() { return c; }

    public boolean isCanBeMoved() { return canBeMoved; }

    public boolean isCanMoveUp() { return canMoveUp; }

    public boolean isCanBuild() { return canBuild; }

    public List<Square> getHistoryPos() { return historyPos; }


}
