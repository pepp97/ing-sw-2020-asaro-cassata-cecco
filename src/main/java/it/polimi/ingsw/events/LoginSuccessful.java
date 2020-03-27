package it.polimi.ingsw.events;

import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class LoginSuccessful implements Event {

    private List<String> nickname;

    public LoginSuccessful(List<String> nickname) {
        this.nickname = nickname;
    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }


}
