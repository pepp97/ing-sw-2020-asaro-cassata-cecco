package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;


public class ChooseSettings implements Command{
    private int nplayer;

    public ChooseSettings(int nplayer) {
        this.nplayer = nplayer;
    }

    public int getNplayer() {
        return nplayer;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this, view);
    }

}
