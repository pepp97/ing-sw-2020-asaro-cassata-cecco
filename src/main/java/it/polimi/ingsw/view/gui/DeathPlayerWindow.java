package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.events.DeathPlayer;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DeathPlayerWindow {
    private Stage stage;
    private Gui gui;
    private Scene scene;


    public DeathPlayerWindow(Gui gui, DeathPlayer death) {
        this.gui = gui;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText(death.getNickname() + " has lose all Worker");


        VBox layoutMessage = new VBox(20);
        HBox buttonLine = new HBox(20);
        buttonLine.setAlignment(Pos.CENTER);
        buttonLine.backgroundProperty().setValue(confirmBackground);
        layoutMessage.getChildren().addAll(confirmMessage, buttonLine);
        layoutMessage.setAlignment(Pos.CENTER);
        layoutMessage.backgroundProperty().setValue(confirmBackground);
        scene = new Scene(layoutMessage, 400, 200, Color.BLACK);
        scene.setFill(Color.BROWN);

    }

    public void displayMessage(Stage owner) {
        stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
