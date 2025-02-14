package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.UseEffect;
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

/**
 * this scene is a popup asking the user if he wants to use his god's effect
 */
public class AskUserWindow {
    private Stage stage;
    private Gui gui;
    private Scene scene;


    public AskUserWindow(Gui gui) {
        this.gui = gui;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("Do you want use the effect?");
        confirmMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt; -fx-font-family: \"Segoe UI Semibold\";");
        Button yesButton = new Button("YES");
        String styleYes="-fx-text-fill: #000000;" +"-fx-background-color: #ff4500;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        yesButton.setStyle(styleYes);
        Button noButton = new Button("NO");
        String styleNo="-fx-text-fill: #000000;" +"-fx-background-color: #0000ff;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        noButton.setStyle(styleNo);
        yesButton.setOnAction(e -> {
            System.out.println("si");
            UseEffect command=new UseEffect(true);
            gui.getClient().send(command);
            stage.close();
        });
        noButton.setOnAction(e -> {
            System.out.println("no");
            UseEffect command=new UseEffect(false);
            gui.getClient().send(command);
            stage.close();
        });

        VBox layoutMessage = new VBox(20);
        HBox buttonLine = new HBox(20);
        buttonLine.getChildren().addAll(yesButton, noButton);
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
