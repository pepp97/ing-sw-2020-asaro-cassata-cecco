package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

public class WaitYourGodEvent implements Event {
    private List<String> gods;
    private List<String> effects;

    public WaitYourGodEvent(List<String> gods, List <String> effects) {
        this.gods = gods;
        this.effects=effects;
    }


    public List<String> getGods() {
        return gods;
    }

    public List<String> getEffects() {
        return effects;
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
