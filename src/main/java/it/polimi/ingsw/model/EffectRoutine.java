package it.polimi.ingsw.model;

import java.util.List;

/**
 * this class is used to commute the json file effect in the corresponding sub action
 */

public class EffectRoutine {
    /**
     * it is the subaction of the json file effect
     */
    private SubAction effect;

    /**
     * it is true if the sub action can be skip by the player
     */

    private boolean isSkippable;
    /**
     * it is a list of levels that need to subaction
     */
    private List<Integer> levels;

    /**
     * Constructor
     * @param eff is the effect taken from json file
     * @param isSkippable is a boolean that is true if the effect is skippable, false otherwise
     */

    public EffectRoutine(String eff, boolean isSkippable) {
        this.levels=null;
        switchClass(eff);
        this.isSkippable = isSkippable;
    }

    /**
     * it is the switch that assign the corresponding subaction
     * @param eff is the effect taken from json file
     */

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
            case "checkVictory":
                effect=new CheckVictory(this.levels);


            default:
               System.out.println("\n");
        }
    }

    /**
     * Constructor
     * @param effect is the effect taken from json file
     * @param isSkippable is a boolean that is true if the effect can be skip, false otherwise
     * @param levels is the list of levels used in the corresponding sub action
     */

    public EffectRoutine(String effect, boolean isSkippable, List<Integer> levels) {
        this.levels=levels;
        switchClass(effect);
        this.isSkippable = isSkippable;

    }


    public boolean isSkippable() {
        return isSkippable;
    }

    public List<Integer> getLevels() {
        return levels;
    }

    public SubAction getEffect() { return effect; }

    public void setLevels(List<Integer> levels) {
        this.levels = levels;
    }

    public void setSkippable(boolean skippable) {
        isSkippable = skippable;
    }
}
