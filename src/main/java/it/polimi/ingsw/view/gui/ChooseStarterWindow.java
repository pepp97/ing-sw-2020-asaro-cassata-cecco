package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseSettings;
import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.commands.StarterCommand;
import it.polimi.ingsw.events.ChooseYourGodEvent;
import it.polimi.ingsw.events.StartMatchEvent;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class ChooseStarterWindow implements GuiScreen{
    private Gui gui;
    private StartMatchEvent event;


    public ChooseStarterWindow(Gui gui, StartMatchEvent event) {
        this.gui=gui;
        this.event=event;
    }

    @Override
    public void setScene() {

        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        Scene scene = new Scene(form, gui.getWidthScreen(), gui.getHeightScreen());
        Text confirmMessage = new Text();
        confirmMessage.setText("Who will start the match?");

        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        RowConstraints r4 = new RowConstraints();
        RowConstraints r5 = new RowConstraints();

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
        ColumnConstraints c5 = new ColumnConstraints();
        ColumnConstraints c6 = new ColumnConstraints();
        ColumnConstraints c7 = new ColumnConstraints();


        r1.setPercentHeight(10);
        r2.setPercentHeight(35);
        r3.setPercentHeight(10);
        r4.setPercentHeight(20);
        r5.setPercentHeight(25);

        c1.setPercentWidth(15);
        c2.setPercentWidth(17.0);
        c3.setPercentWidth(10);
        c4.setPercentWidth(17.0);
        c5.setPercentWidth(10);
        c6.setPercentWidth(17.0);
        c7.setPercentWidth(14);

        form.getRowConstraints().addAll(r1, r2, r3, r4, r5);
        form.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7);
        // form.setGridLinesVisible(true);

        // mettere il for

        Button b0=new Button(gui.getNicknames().get(0));
        Button b1=new Button (gui.getNicknames().get(1));


        int i = 1;
        int j = 1;

        for (String s : gui.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            form.add(imageView, i, j, 1, 1);
            i = i + 2;
        }



        b0.setOnAction(f->{
            StarterCommand starterCommand=new StarterCommand(b0.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b0,1,3);

        b1.setOnAction(f->{
            StarterCommand starterCommand=new StarterCommand(b1.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b0,3,3);

        if (gui.getNicknames().size()==3){
            Button b2=new Button(gui.getNicknames().get(2));
            b2.setOnAction(f->{
                StarterCommand starterCommand=new StarterCommand(b2.getId());
                gui.getClient().send(starterCommand);
            });
            form.add(b0,5,3);}

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose starter");
    }


}