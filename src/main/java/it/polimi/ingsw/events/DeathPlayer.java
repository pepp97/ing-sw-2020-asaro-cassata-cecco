package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

public class DeathPlayer implements Event {

    private String nickname;

    public DeathPlayer(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void send(Gui view) {

        view.update(this);

    }

    @Override
    public void send(VirtualView view) {
        view.update(this);
    }


}
