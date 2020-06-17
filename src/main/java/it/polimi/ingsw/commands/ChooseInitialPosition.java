package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

/**
 * This Class is the command that send to the model the initial position of the worker
 */

public class ChooseInitialPosition implements Command {

    private int coordinateX;
    private int coordinateY;

    /**
     * Default constructor
     * @param coordinateX is the column selected
     * @param coordinateY is the raw selected
     */

    public ChooseInitialPosition(int coordinateX, int coordinateY) {
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
     * @return the coordinate x of the initial position of the worker
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
        controller.apply(this,view);
    }

}
