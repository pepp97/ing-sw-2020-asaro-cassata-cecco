package it.polimi.ingsw.view.gui;


import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class EndGameWindow implements GuiScreen {

    private Gui gui;
    private EndGame endGame;

    public EndGameWindow(Gui gui, EndGame endGame) {
        this.gui = gui;
        this.endGame = endGame;
    }

    @Override
    public void setScene() {

        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);

        Scene scene=new Scene(form,gui.getWidthScreen(),gui.getHeightScreen());
        Image intro = new Image("SantoriniIntro.jpg", gui.getWidthScreen(),gui.getHeightScreen(),true, true); //modificare percorso.

        //Image intro = new Image("SantoriniIntro.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(intro);
        imageView.setFitHeight(gui.getHeightScreen());
        imageView.setFitWidth(gui.getWidthScreen());
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        form.setBackground(new Background(backgroundImage));
        Label nameLabel=new Label("The winner of this match is: " + endGame.getResult());
        nameLabel.setTextFill(javafx.scene.paint.Color.RED);
        nameLabel.setFont(new Font(25.00));
        form.addRow(1,nameLabel);

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("End Game");


    }
}
