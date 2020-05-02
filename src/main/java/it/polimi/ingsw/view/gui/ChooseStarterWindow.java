package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseSettings;
import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.commands.StarterCommand;
import it.polimi.ingsw.events.ChooseYourGodEvent;
import it.polimi.ingsw.events.StartMatchEvent;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;

public class ChooseStarterWindow implements GuiScreen {
    private Gui gui;
    private StartMatchEvent event;


    public ChooseStarterWindow(Gui gui, StartMatchEvent event) {
        this.gui = gui;
        this.event = event;
    }

    @Override
    public void setScene() {
        int nplayer = gui.getNicknames().size();
        if (nplayer == 2) {
            n2player();
        }else{
            n3player();
        }

    }


    public void n2player() {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());
        pane.setMaxWidth(gui.getWidthScreen());
        pane.setMaxHeight(gui.getHeightScreen());
        //pane.getCenter().setStyle("-fx-border-color: black");
        Text testo = new Text("WHO WILL START THE MATCH?");
        testo.setFill(Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ;
        title.setTextAlignment(TextAlignment.CENTER);

        GridPane form = new GridPane();
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();

        form.getRowConstraints().addAll(r1, r2);
        form.getColumnConstraints().addAll(c1, c2);

        StackPane center = new StackPane();
        center.setMaxHeight(800);
        center.setMaxWidth(600);


        pane.setTop(title);
        form.setMaxHeight(400);
        form.setMaxWidth(450);
        r1.setMaxHeight(300);
        r2.setMaxHeight(100);
        c1.setMaxWidth(250);
        c1.setMaxWidth(250);
        c1.setHalignment(HPos.CENTER);
        c2.setHalignment(HPos.CENTER);
        r1.setValignment(VPos.TOP);
        r2.setValignment(VPos.TOP);
        form.setAlignment(Pos.TOP_CENTER);
        form.setMaxWidth(center.getMaxWidth());
        form.setMaxHeight(center.getMaxHeight());
        form.setAlignment(Pos.TOP_CENTER);
        form.setStyle("-fx-border-color: black");
        //pane.getCenter().setStyle("-fx-border-color: black");


        Button b0 = new Button(gui.getNicknames().get(0));
        b0.setId(gui.getNicknames().get(0));
        Button b1 = new Button(gui.getNicknames().get(1));
        b1.setId(gui.getNicknames().get(1));

        int i = 0;
        int j = 0;

        for (String s : gui.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            if (i == 0) {
                form.add(imageView, 0, 0);
            } else {
                form.add(imageView, 1, 0);
            }

            i = i + 1;
        }

        b0.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b0.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b0, 0, 2);

        b1.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b1.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b1, 1, 2);

        center.getChildren().add(form);
        center.setAlignment(form, Pos.TOP_CENTER);


        pane.setCenter(center);
        pane.getCenter().setStyle("-fx-alignment: center");
        pane.setStyle("-fx-alignment: center");


        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose starter");
    }

    public void n3player() {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());
        pane.setMaxWidth(gui.getWidthScreen());
        pane.setMaxHeight(gui.getHeightScreen());

        Text testo = new Text("WHO WILL START THE MATCH?");
        testo.setFill(Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);

        GridPane form = new GridPane();
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();

        form.getRowConstraints().addAll(r1, r2);
        form.getColumnConstraints().addAll(c1, c2, c3);

        StackPane center = new StackPane();
        center.setMaxHeight(800);
        center.setMaxWidth(800);


        pane.setTop(title);
        form.setMaxHeight(400);
        form.setMaxWidth(450);
        r1.setMaxHeight(300);
        r2.setMaxHeight(100);
        r1.setValignment(VPos.TOP);
        r2.setValignment(VPos.TOP);

        c1.setMaxWidth(250);
        c2.setMaxWidth(250);
        c3.setMaxWidth(250);
        c1.setHalignment(HPos.CENTER);
        c2.setHalignment(HPos.CENTER);
        c3.setHalignment(HPos.CENTER);

        form.setAlignment(Pos.TOP_CENTER);
        form.setMaxWidth(center.getMaxWidth());
        form.setMaxHeight(center.getMaxHeight());
        form.setStyle("-fx-border-color: black");
        //pane.getCenter().setStyle("-fx-border-color: black");


        Button b0 = new Button(gui.getNicknames().get(0));
        b0.setId(gui.getNicknames().get(0));
        Button b1 = new Button(gui.getNicknames().get(1));
        b1.setId(gui.getNicknames().get(1));


        int i = 0;
        int j = 0;

        for (String s : gui.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            if (i == 0) {
                form.add(imageView, 0, 0);
            } else if (i == 1) {
                form.add(imageView, 1, 0);
            } else {
                form.add(imageView, 2, 0);
            }
            i = i + 1;
        }

        b0.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b0.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b0, 0, 2);

        b1.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b1.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b1, 1, 2);

        if (gui.getNicknames().size() == 3) {
            Button b2 = new Button(gui.getNicknames().get(2));
            b2.setId(gui.getNicknames().get(2));
            b2.setOnAction(f -> {
                StarterCommand starterCommand = new StarterCommand(b2.getId());
                gui.getClient().send(starterCommand);
            });
            form.add(b2, 5, 3);
        }

        center.getChildren().add(form);
        center.setAlignment(form, Pos.TOP_CENTER);

        pane.setCenter(center);
        pane.getCenter().setStyle("-fx-alignment: center");
        pane.setStyle("-fx-alignment: center");
        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose starter");
    }


}

/*
        GridPane form = new GridPane();
        //form.setAlignment(Pos.CENTER);
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


        // form.setGridLinesVisible(true); questa no

        // mettere il for
        form.add(confirmMessage, 4, 0);


        Button b0 = new Button(gui.getNicknames().get(0));
        Button b1 = new Button(gui.getNicknames().get(1));


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


        b0.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b0.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b0, 1, 3);

        b1.setOnAction(f -> {
            StarterCommand starterCommand = new StarterCommand(b1.getId());
            gui.getClient().send(starterCommand);
        });
        form.add(b1, 3, 3);


        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose starter");
 */