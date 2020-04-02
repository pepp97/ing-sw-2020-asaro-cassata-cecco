package it.polimi.ingsw.model;

import java.util.List;

public class EffectRoutine {
    private Object effect;
    private boolean isSkippable;
    private List<Integer> levels;

    public EffectRoutine(String eff, boolean isSkippable) {
        switchClass(eff);
        this.isSkippable = isSkippable;
        this.levels=null;
    }

    public void switchClass(String eff){
        switch (eff){
            case "move":
                effect=new Move();
                break;
            case "build":
                effect=new Build();
                break;
            case "askUser":
                effect=new AskUser();
                break;
            case "filterNotSame":
                effect=new FilterNotSame();
                break;
            case "filterLevel":
                effect=new LevelFilter(this.levels);
                break;
            case "theyDoNotMoveUp":
                effect=new TheyDontMoveUp();
                break;
            case "changePosition":
                effect=new ChangePosition();
                break;
            case "moveSameDirection":
                effect=new MoveInSameDirection();
                break;
            case "youDontMoveUp":
                effect=new YouDontMoveUp();
                break;
            case "changeTarget":
                effect=new SwapTarget();
                break;
            case "filterSame":
                effect=new FilterSame();
                break;
            default:
                System.out.println("Class not found");
        }
    }

    public EffectRoutine(String effect, boolean isSkippable, List<Integer> levels) {
        this.effect = effect;
        this.isSkippable = isSkippable;
        this.levels=levels;
    }


    public boolean isSkippable() {
        return isSkippable;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public Object getEffect() { return effect; }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }

    public void setSkippable(boolean skippable) {
        isSkippable = skippable;
    }
}
