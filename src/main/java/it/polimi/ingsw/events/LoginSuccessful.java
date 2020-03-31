package it.polimi.ingsw.events;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class LoginSuccessful implements Event {

    private List<String> nickname;

    public LoginSuccessful(List<String> nickname) {
        this.nickname = nickname;
    }

    @Override
    public void send(View view) {
        view.update(this);
    }


}
