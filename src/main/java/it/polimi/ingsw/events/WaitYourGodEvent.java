package it.polimi.ingsw.events;

import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.VirtualView;

import java.util.List;

/**
 * This Class is the event sends to the player that have choose your god
 */

public class WaitYourGodEvent implements Event {
    private List<String> gods;
    private List<String> effects;

    /**
     * Default constructor
     * @param gods is the List of Gods remained
     * @param effects is the List of the God's effect
     */

    public WaitYourGodEvent(List<String> gods, List <String> effects) {
        this.gods = gods;
        this.effects=effects;
    }

    /**
     * @return is the List of Gods remained
     */

    public List<String> getGods() {
        return gods;
    }

    /**
     * @return the List of the God's effect
     */

    public List<String> getEffects() {
        return effects;
    }
    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(Gui view) {
        view.update(this);
    }
    /**
     * this method is call to send the event
     * @param view the view of the player that have to receive the event
     */
    @Override
    public void send(VirtualView view) {
        view.update(this);
    }
}
