package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
/**
 * It is the micro-effect that have the role to check if the player have win
 *  @author  Salvatore Cassata
 */

public class God {
    public List<Integer> getCantDo() {
        return cantDo;
    }

    public void setCantDo(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    private List<Integer> cantDo = new ArrayList<>();

    public List<SubAction> getEffect() {
        return effect;
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

    private String name;

    public String getTextEffect() {
        return textEffect;
    }

    public void setTextEffect(String textEffect) {
        this.textEffect = textEffect;
    }

    private String textEffect;


}
