package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It is the micro-effect that have the role to check if the player have win
 *
 * @author Salvatore Cassata
 */

public class God {

    private String name;

    private String textEffect;

    private String surname;

    private List<EffectRoutine> Routine = new ArrayList<>();

    private List<Integer> cantDo = new ArrayList<>();

    public List<SubAction> getEffect() {
        return effect;
    }

    public List<Integer> getCantDo() {
       return List.copyOf(cantDo);
    }

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
