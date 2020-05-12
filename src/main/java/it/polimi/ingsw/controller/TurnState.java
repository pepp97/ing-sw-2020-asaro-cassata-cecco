package it.polimi.ingsw.controller;

public interface TurnState {
     void executeState (Controller  controller);
     void goBack();

    Boolean tryToEscape();
}
