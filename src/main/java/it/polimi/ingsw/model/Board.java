package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Field field;
    private List<God> godCardSelected= new ArrayList<>();


    public Board() {
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public List<God> getGodCardSelected() {
        return godCardSelected;
    }

    public void setGodCardSelected(List<God> godCardSelected) {
        this.godCardSelected = godCardSelected;
    }


}
