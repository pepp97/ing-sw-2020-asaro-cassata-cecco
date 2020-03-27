package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.view.VirtualView;


public class UseEffect implements Command {

    private boolean reply;


    public UseEffect(boolean reply) {
        this.reply = reply;
    }

    public boolean getReply() {
        return reply;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this);
    }
}
