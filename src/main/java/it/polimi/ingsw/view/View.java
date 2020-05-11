package it.polimi.ingsw.view;

import it.polimi.ingsw.Observer;
import it.polimi.ingsw.events.ConnectionSuccessful;
import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.model.Player;

import java.io.IOException;

public interface View extends Observer {
    public Player getOwner();

    void closeAll() throws IOException;
}
