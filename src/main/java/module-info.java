module AM06 {
    requires javafx.graphics;
    requires javafx.controls;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.base;
    exports it.polimi.ingsw.view;
    exports it.polimi.ingsw.model;
    exports it.polimi.ingsw.ParserClient;
    exports it.polimi.ingsw.ParserServer;
    exports it.polimi.ingsw.events;
    exports it.polimi.ingsw.controller;
    exports it.polimi.ingsw.commands;
}