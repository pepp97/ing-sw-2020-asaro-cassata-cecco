package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.events.StartGameEvent;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class SelectGodWindow implements GuiScreen {
    private Gui gui;
    private StartGameEvent startGameEvent;

    public SelectGodWindow(Gui gui, StartGameEvent startGameEvent) {
        this.gui = gui;
        this.startGameEvent = startGameEvent;
    }

    public void setScene() {

        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        Scene scene = new Scene(form, gui.getWidthScreen(), gui.getHeightScreen());
        Text confirmMessage = new Text();
        confirmMessage.setText("Which gods do you want?");

        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        RowConstraints r4 = new RowConstraints();
        RowConstraints r5 = new RowConstraints();
        RowConstraints r6 = new RowConstraints();
        RowConstraints r7 = new RowConstraints();

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
        ColumnConstraints c5 = new ColumnConstraints();
        ColumnConstraints c6 = new ColumnConstraints();
        ColumnConstraints c7 = new ColumnConstraints();
        ColumnConstraints c8 = new ColumnConstraints();
        ColumnConstraints c9 = new ColumnConstraints();
        ColumnConstraints c10 = new ColumnConstraints();
        ColumnConstraints c11 = new ColumnConstraints();

        r1.setPercentHeight(10);
        r2.setPercentHeight(35);
        r3.setPercentHeight(10);
        r4.setPercentHeight(35);
        r5.setPercentHeight(5);
        r6.setPercentHeight(0);
        r7.setPercentHeight(0);
        c1.setPercentWidth(2.5);
        c2.setPercentWidth(17.0);
        c3.setPercentWidth(2.5);
        c4.setPercentWidth(17.0);
        c5.setPercentWidth(2.5);
        c6.setPercentWidth(17.0);
        c7.setPercentWidth(2.5);
        c8.setPercentWidth(17.0);
        c9.setPercentWidth(2.5);
        c10.setPercentWidth(17.0);
        c11.setPercentWidth(2.5);

        form.getRowConstraints().addAll(r1, r2, r3, r4, r5, r6, r7);
        form.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7,c8,c9,c10,c11);
        //form.setGridLinesVisible(true);

        // mettere il for

        int i = 1;
        int j = 1;

        for (String s : startGameEvent.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);





            form.add(imageView, i, j, 1, 1);
            i = i + 2;
            if(i == 11){
                i = 1;
                j = j + 2;
            }

        }

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose gods");
    }
}
