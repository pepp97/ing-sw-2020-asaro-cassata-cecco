package it.polimi.ingsw.commands;


import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

/**
 * Is the command that send to the model the osition of worker to use during the turn
 */

public class ChooseYourWorker implements Command {

    private int coordinateX;
    private int coordinateY;

    /**
     * Default constructor
     * @param coordinateX is the column selected
     * @param coordinateY is the raw selected
     */

    public ChooseYourWorker(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    /**
     *
     * @return the coordinate x of the initial position of the worker
     */

    public int getCoordinateX() {
        return coordinateX;
    }
    /**
     *
     * @return the coordinate y of the initial position of the worker
     */

    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * this metod execute the command
     * @param controller the reference controller
     * @param view the personal VirtualView that identify the player that have sent this command
     */


    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this);
    }

}
