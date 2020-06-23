package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * it is the class of the god card
 *
 * @author Salvatore Cassata
 */

public class God {
    /**
     * it is the name of the god
     */

    private String name;

    /**
     * it is the text of the effect of the card
     */

    private String textEffect;
    /**
     * it is the nickname of the God
     */

    private String surname;

    /**
     * it is the list of the sub action
     */

    private List<EffectRoutine> Routine = new ArrayList<>();

    /**
     * it is the list of level that needs to some sub action that are not accept
     */

    private List<Integer> cantDo = new ArrayList<>();

    public List<SubAction> getEffect() {
        return effect;
    }

    public List<Integer> getCantDo() {
       return List.copyOf(cantDo);
    }

    /**
     * default constructor
     * @param name is the name of the god
     * @param textEffect is the text of the effect of the god card
     * @param surname is the nickname of the card
     * @param routine is the List of sub action of the god
     */

    public God(String name, String textEffect, String surname, List<EffectRoutine> routine) {
        this.name = name;
        this.textEffect = textEffect;
        this.surname = surname;
        Routine = routine;
    }


    public void setCantDo(List<Integer> cantDo) {
        for (Integer i : cantDo)
            if (i >= 0 && i <= 4)
                this.cantDo.add(i);
    }

    public void setEffect(List<SubAction> effect) {
        this.effect = effect;
    }

    private List<SubAction> effect = new ArrayList<>();

    public String getName() {
        return name;
    }

    public String getTextEffect() {
        return textEffect;
    }

    public String getSurname() {
        return surname;
    }

    public List<EffectRoutine> getRoutine() {
        return List.copyOf(Routine);
    }

    public void clearFilter() {
        cantDo.clear();
    }
}
