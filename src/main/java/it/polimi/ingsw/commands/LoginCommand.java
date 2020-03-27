package it.polimi.ingsw.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.view.VirtualView;

public class LoginCommand implements Command{
    private String nickname;
    private Color color;

    public LoginCommand(String nickname, Color color) {
        this.nickname = nickname;
        this.color = color;
    }

    public String getNickname() {
        return nickname;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void execute(Controller controller, VirtualView view) {
        controller.apply(this, view);
    }
}
