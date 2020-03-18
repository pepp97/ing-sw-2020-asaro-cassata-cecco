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
        return null;
    }


    /**
     * questo metodo mi serve per aggiornare la posizione del worker ad ogni suo spostamento
     */

    public void setActualPos(Square pos){
        this.actualPos=pos;
        aggiornaPos(pos); //aggiungo alla lista della cronologia delle posizioni quella corrente
    }

    /**
     * questo metodo mi serve per aggiornare  la lista delle cronologia delle posizioni del worker nel turno
     */

    public void aggiornaPos(Square pos){ this.historyPos.add(pos); }

    /**
     * questo metodo verrà chiamato dal controller ogni turno per resettare la cronologia delle posizioni del worker
     * prima dell'inizio di un nuovo turno turno e terrà nella lista solo la posizione attuale del worket
     */

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

    public Square getActualPos() { return actualPos; }

    public List<Square> getHistoryPos() { return historyPos; }


}
