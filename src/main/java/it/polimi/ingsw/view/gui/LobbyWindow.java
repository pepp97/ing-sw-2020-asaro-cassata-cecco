package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseGods;
import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.LoginSuccessful;
import it.polimi.ingsw.events.SettingsEvent;
import it.polimi.ingsw.events.StartGameEvent;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.view.Gui;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class LobbyWindow implements GuiScreen {
    private Gui gui;
    private LoginSuccessful event;

    public LobbyWindow(Gui gui, LoginSuccessful loginSuccessful) {
        this.gui=gui;
        this.event=loginSuccessful;
    }

    @Override
    public void setScene() {
        Platform.runLater(()->{
            AnchorPane anchorPane=new AnchorPane();
            createAnchor(anchorPane);

        });
    }

    private void createAnchor(AnchorPane anchorPane) {
        Scene scene=new Scene(anchorPane,gui.getWidthScreen(),gui.getHeightScreen());
        Label welcome=new Label ("Benvenuto! \n Attendere l'inizio della partita, stanno giocando: ");
        AnchorPane.setLeftAnchor(welcome, 600.0);
        AnchorPane.setTopAnchor(welcome, 100.0);

        anchorPane.getChildren().add(0, welcome);
        HBox hbox=new HBox(50.0);
        VBox vBox=new VBox(25.0);
        hbox.getChildren().add(vBox);

        for(String p: event.getNickname()){

            Label newPlayer=new Label(p);
            vBox.getChildren().add(newPlayer);


        }

        AnchorPane.setTopAnchor(hbox, 200.0);
        AnchorPane.setLeftAnchor(hbox, 550.0);
        anchorPane.getChildren().add(0, hbox);
        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("WaitRoom Window");
    }

}

