package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

public class ChooseInitialPosition implements Command {

    private int coordinateX;
    private int coordinateY;

    public ChooseInitialPosition(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this,view);
    }

}
