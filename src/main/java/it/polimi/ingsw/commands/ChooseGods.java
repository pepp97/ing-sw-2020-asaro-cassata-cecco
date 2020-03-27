package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;


import java.util.ArrayList;
import java.util.List;

public class ChooseGods implements Command {

    private List<String> namesGod;

    public ChooseGods(List<String> namesGod) {
        this.namesGod = namesGod;
    }


    public List<String> getNamesGod() {
        return namesGod;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this);
    }
}
