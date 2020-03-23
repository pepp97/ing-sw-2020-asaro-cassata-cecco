package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * rapresent the players
 * @author Riccardo Cecco
 */

public class Player {

    private String username;
    private List<Worker> workers=new ArrayList<>(2);
    private God god;


    /**
     * this metod is the builder of the class
     * @param username to identify the player
     */
    public Player(String username) {
        this.username = username;

    }


    /**
     * this method is used to assign a worker to the respective player
     * @param worker worker to assing
     */

   //questo metodo serve per assegnare un worker ad un player
   public void add(Worker worker){
        if(workers.size()<2)
            workers.add(worker);
    }

    //Metodi Setter
    public void setUsername(String username) { this.username = username; }

    public void setWorkers(List <Worker> workers) { this.workers = workers; }

    public void setGod(God god) { this.god = god; }

    //Metodi Getter
    public String getUsername() { return username; }

    public List <Worker>  getWorkers() { return workers; }

    public God getGod() { return god; }
}
