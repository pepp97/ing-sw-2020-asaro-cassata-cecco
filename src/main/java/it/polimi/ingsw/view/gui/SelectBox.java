package it.polimi.ingsw.view.gui;

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

public class SelectBox {
    private  boolean answer;
    private Stage stage;
    private Scene boxMessage;
    public SelectBox (String message) {
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText(message);
        confirmMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt;");
        Button yesButton = new Button("Yes");
        String styleYes="-fx-text-fill: #000000;" +"-fx-background-color: #ff0000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        yesButton.setStyle(styleYes);
        Button noButton = new Button("No");
        String styleNo="-fx-text-fill: #000000;" +"-fx-background-color: #00ff00;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        noButton.setStyle(styleNo);
        yesButton.setOnAction(e -> {
            answer = true;
            stage.close();
        });
        noButton.setOnAction(e -> {
            answer = false;
            stage.close();
        });
        yesButton.setDefaultButton(false);
        noButton.setDefaultButton(false);
        VBox layoutMessage = new VBox(20);
        HBox buttonLine = new HBox(20);
        buttonLine.getChildren().addAll(yesButton, noButton);
        buttonLine.setAlignment(Pos.CENTER);
        buttonLine.backgroundProperty().setValue(confirmBackground);
        layoutMessage.getChildren().addAll(confirmMessage, buttonLine);
        layoutMessage.setAlignment(Pos.CENTER);
        layoutMessage.backgroundProperty().setValue(confirmBackground);
        boxMessage = new Scene(layoutMessage, 400, 200, Color.BLACK);
        boxMessage.setFill(Color.BROWN);

    }

    public boolean displayMessage(Stage owner) {
        stage = new Stage(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        answer = false;
        stage.setScene(boxMessage);
        stage.showAndWait();
        return answer;
    }
}
