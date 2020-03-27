package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

public class ChooseYourGod implements Command {

    private String name;

    public ChooseYourGod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this,view);
    }


}
