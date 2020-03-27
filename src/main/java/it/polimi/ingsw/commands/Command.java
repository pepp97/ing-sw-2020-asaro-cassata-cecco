package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;

import it.polimi.ingsw.view.VirtualView;

public interface Command {


    void execute(Controller controller, VirtualView view);
}
