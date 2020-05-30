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

public class DeathPlayerWindow implements GuiScreen{
    private Stage stage;
    private Gui gui;
    private DeathPlayer event;


    public DeathPlayerWindow(Gui gui, DeathPlayer event) {
        this.gui = gui;
        this.event = event;
    }

    @Override
    public void setScene() {
        VBox layoutMessage = new VBox(20);
        Scene scene = new Scene(layoutMessage, 400, 200);
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("You have lost");
        confirmMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 20pt;");

        HBox buttonLine = new HBox(20);
        buttonLine.setAlignment(Pos.CENTER);
        buttonLine.backgroundProperty().setValue(confirmBackground);
        layoutMessage.getChildren().addAll(confirmMessage, buttonLine);
        layoutMessage.setAlignment(Pos.CENTER);
        layoutMessage.backgroundProperty().setValue(confirmBackground);

        scene.setFill(Color.BROWN);

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("DeathPlayer");
    }

}
