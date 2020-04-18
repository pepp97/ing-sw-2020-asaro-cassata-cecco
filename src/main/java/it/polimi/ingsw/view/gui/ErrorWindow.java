package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseSettings;
import it.polimi.ingsw.events.ExceptionEvent;
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

public class ErrorWindow{

    private Stage stage;
    private Gui gui;
    private Scene scene;

    public ErrorWindow(Gui gui, ExceptionEvent exceptionEvent) {
        this.gui = gui;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText(exceptionEvent.getException());
        scene = new Scene(confirmMessage, 400, 200, Color.BLACK);
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
