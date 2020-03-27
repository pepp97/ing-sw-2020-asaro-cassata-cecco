package it.polimi.ingsw.exceptions;

import it.polimi.ingsw.model.Target;

public class TargetNotAvailableException extends Exception {
    /**
     *
     */
    public TargetNotAvailableException() {
        super("Target not available");
    }
}
