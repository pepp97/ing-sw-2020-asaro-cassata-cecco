package it.polimi.ingsw.model;

/**
 * rapresent the players
 * @author Riccardo Cecco
 */

public class Player {

    private String username;
    private Worker[] workers;
    private God god;


    /**
     * this metod is the builder of the class
     * @param username to identify the player
     */
    public Player(String username) {
        this.username = username;
        this.workers = new Worker[2];

    }


    /**
     * this method is used to assign a worker to the respective player
     * @param a worker to assing
     */

    //questo metodo serve per assegnare un worker ad un player
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

    public void setGod(God god) { this.god = god; }

    //Metodi Getter
    public String getUsername() { return username; }

    public Worker[] getWorkers() { return workers; }

    public God getGod() { return god; }
}
