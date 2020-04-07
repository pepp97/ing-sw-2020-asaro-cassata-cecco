package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;

import java.util.List;

public class ChooseYourGodEvent implements Event {
    private List<String> gods;
    private List<String> effects;

    public ChooseYourGodEvent(List<String> gods, List <String> effects) {
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
    public void send(View view) {
        view.update(this);
    }

}
