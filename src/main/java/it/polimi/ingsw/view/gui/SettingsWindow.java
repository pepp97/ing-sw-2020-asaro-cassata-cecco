package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseSettings;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.LoginSuccessful;
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

import java.util.ArrayList;
import java.util.List;

public class SettingsWindow {

    private Stage stage;
    private Gui gui;
    private Scene scene;


    public SettingsWindow(Gui gui) {
        this.gui = gui;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("How many players?");
        confirmMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt; -fx-font-family: \"Segoe UI Semibold\";");
        Button twoButton = new Button("2");
        String style2="-fx-text-fill: #000000;" +"-fx-background-color: #8f00ff;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        twoButton.setStyle(style2);
        Button threeButton = new Button("3");
        String style3="-fx-text-fill: #000000;" +"-fx-background-color: #ffa500;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        threeButton.setStyle(style3);

        twoButton.setOnAction(e -> {
            stage.close();
            ChooseSettings chooseSettings = new ChooseSettings(2);
            gui.getClient().send(chooseSettings);
        });
        threeButton.setOnAction(e -> {
            ChooseSettings chooseSettings = new ChooseSettings(3);
            gui.getClient().send(chooseSettings);
            stage.close();



        });

        VBox layoutMessage = new VBox(20);
        HBox buttonLine = new HBox(20);
        buttonLine.getChildren().addAll(twoButton, threeButton);
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

