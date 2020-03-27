package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.View;

public interface Command {

    default void execute(Controller controller, View view){
        controller.apply(this,view);
    }
}
