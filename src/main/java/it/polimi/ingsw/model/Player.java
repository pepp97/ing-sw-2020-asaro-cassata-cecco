package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * rapresent the players
 * @author Riccardo Cecco
 */

public class Player {

    private String username;
    private List<Worker> workers=new ArrayList<>();
    private God god;
    private Color color;
    private boolean hasBuilt;
    private boolean hasBeenMoved;
    private boolean defeat;



    /**
     * this metod is the builder of the class
     * @param username to identify the player
     */
    public Player(String username, Color color) {
        this.username = username;
        this.color=color;
        this.defeat=false;
    }

    public boolean isHasBuilt() {
        return hasBuilt;
    }

    public void setHasBuilt(boolean hasBuilt) {
        this.hasBuilt = hasBuilt;
    }

    public boolean isHasBeenMoved() {
        return hasBeenMoved;
    }

    public void setHasBeenMoved(boolean hasBeenMoved) {
        this.hasBeenMoved = hasBeenMoved;
    }

    public boolean isDefeat() {
        return defeat;
    }

    public void setDefeat(boolean defeat) {
        this.defeat = defeat;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * this method is used to assign a worker to the respective player
     * @param worker worker to assing
     */

   //questo metodo serve per assegnare un worker ad un player
   public void setWorkers(Worker worker){
        if(workers.size()<2){
            workers.add(worker);
            worker.setC(this.color);}
    }

    //Metodi Setter
    public void setUsername(String username) { this.username = username; }



    public void setGod(God god) {
        if(god.getTextEffect()!=null && god.getName()!=null && god.getSurname()!=null && god.getRoutine().size()>=1)
       this.god = god;
   }

    //Metodi Getter
    public String getUsername() {
       return username;
   }

    public List <Worker>  getWorkers() {
       List<Worker> workerstoRet=  List.copyOf(workers);
       return workerstoRet; }

    public God getGod() { return god; }

}
