package it.polimi.ingsw.model;

import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

public class PingDelete implements Command {
    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this, view);
    }
}
