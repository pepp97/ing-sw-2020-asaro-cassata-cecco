package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is the class of the player
 *
 * @author Riccardo Cecco
 */

public class Player {
    /**
     * it is the username choosed by the player
     */

    private String username;
    /**
     * it is the list of workers of the player
     */
    private List<Worker> workers = new ArrayList<>();
    /**
     * it is the god of the player
     */
    private God god;
    /**
     * it is the color of the player
     */
    private Color color;
    /**
     * it is true if the player has built during is turn, false otherwise
     */
    private boolean hasBuilt;
    /**
     * it is true if the player has moove during is turn, false otherwise
     */
    private boolean hasBeenMoved;
    /**
     * it is true if the player has loose false otherwise
     */
    private boolean defeat;
    /**
     * it is true if player is in que during the execute routine, false otherwise
     */
    private boolean inQue;


    /**
     * this metod is the builder of the class
     *
     * @param username to identify the player
     */
    public Player(String username, Color color) {
        this.username = username;
        this.color = color;
        this.defeat = false;
    }

    public boolean isInQue() {
        return inQue;
    }

    public void setInQue(boolean inQue) {
        this.inQue = inQue;
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
     *
     * @param worker worker to assign
     */

    //questo metodo serve per assegnare un worker ad un player
    public void setWorkers(Worker worker) {
        if (workers.size() < 2) {
            workers.add(worker);
            worker.setC(this.color);
        }
    }


    public void setGod(God god) {
        if (god.getTextEffect() != null && god.getName() != null && god.getSurname() != null && god.getRoutine().size() >= 1)
            this.god = god;
    }

    public String getUsername() {
        return username;
    }

    public List<Worker> getWorkers() {
        List<Worker> workerstoRet = List.copyOf(workers);
        return workerstoRet;
    }

    public God getGod() {
        return god;
    }


    @Override
    public boolean equals(Object p) {
        Player tmp = (Player) p;
        return this.getUsername().equals(tmp.getUsername()) && this.getColor().equals(tmp.getColor());
    }
}
