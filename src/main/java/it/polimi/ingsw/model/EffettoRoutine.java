package it.polimi.ingsw.model;

import java.util.List;

public class EffettoRoutine {
    private String effect;
    private boolean isSkippable;
    private List<Integer> levels;

    public EffettoRoutine(String effect, boolean isSkippable) {
        this.effect = effect;
        this.isSkippable = isSkippable;
        this.levels=null;
    }

    public EffettoRoutine(String effect, boolean isSkippable, List<Integer> levels) {
        this.effect = effect;
        this.isSkippable = isSkippable;
        this.levels=levels;
    }

    public String getEffect() {
        return effect;
    }

    public boolean isSkippable() {
        return isSkippable;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setSkippable(boolean skippable) {
        isSkippable = skippable;
    }
}
