package it.polimi.ingsw.model;

/**
 * rapresent the players
 * @author Riccardo Cecco
 */

public class Player {

    private String username;
    private Worker[] workers;
    //manca god

    /**
     * costruttore della classe
     * @param username
     */
    public Player(String username) {
        this.username = username;
        this.workers = new Worker[2];

    }


    /**
     * questo metodo serve per assegnare un worker ad un player
     * @param a
     */
    public void assegnaWorker(Worker a){
        if(workers[0]==null) {
            workers[0]=a;
        }else if(workers[1]==null){
            workers[1]=a;
        }
    }


    //Metodi Setter
    public void setUsername(String username) { this.username = username; }

    public void setWorkers(Worker[] workers) { this.workers = workers; }

    //Metodi Getter
    public String getUsername() { return username; }

    public Worker[] getWorkers() { return workers; }
}
