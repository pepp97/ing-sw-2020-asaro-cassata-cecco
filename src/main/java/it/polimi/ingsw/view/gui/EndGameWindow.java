package it.polimi.ingsw.view.gui;


import it.polimi.ingsw.events.EndGame;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * this window notifies the player who has won
 */
public class EndGameWindow implements GuiScreen {

    private Gui gui;
    private EndGame endGame;

    public EndGameWindow(Gui gui, EndGame endGame) {
        this.gui = gui;
        this.endGame = endGame;
    }

    @Override
    public void setScene() {

        StackPane stackPane=new StackPane();
        Scene scene = new Scene(stackPane, gui.getWidthScreen(), gui.getHeightScreen());
        Image intro = new Image("win.png", 500, 500, true, true);
        ImageView imageView = new ImageView();
        imageView.setImage(intro);
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        stackPane.setBackground(new Background(backgroundImage));
        stackPane.setAlignment(Pos.CENTER);
        scene.setFill(Color.BROWN);

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("Winner");


    }

}
