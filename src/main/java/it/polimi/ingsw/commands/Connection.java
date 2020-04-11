package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

public class Connection implements Command{





    @Override
    public void execute(Controller controller, VirtualView view) {
            controller.apply(this,view);
    }
}
