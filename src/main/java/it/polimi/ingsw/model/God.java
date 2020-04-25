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
        return cantDo;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getTextEffect() {
        return textEffect;
    }

    public void setTextEffect(String textEffect) {
        this.textEffect = textEffect;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<EffectRoutine> getRoutine() {
        return Routine;
    }

    public void setRoutine(List<EffectRoutine> routine) {
        Routine = routine;
    }


}
