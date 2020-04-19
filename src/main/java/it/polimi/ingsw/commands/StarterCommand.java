package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;

public class StarterCommand implements Command {
    private String nick;


    public StarterCommand(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this,view);
    }
}
