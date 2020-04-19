package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.ChooseYourGodEvent;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.model.Field;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;
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

public class SelectYourGod implements GuiScreen {
    private Gui gui;
    private ChooseYourGodEvent event;


    public SelectYourGod(Gui gui, ChooseYourGodEvent event) {
        this.gui=gui;
        this.event=event;
    }

    @Override
    public void setScene() {
        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);
        Scene scene = new Scene(form, gui.getWidthScreen(), gui.getHeightScreen());
        Text confirmMessage = new Text();
        confirmMessage.setText("Which god do you want to use?");

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


        r1.setPercentHeight(10);
        r2.setPercentHeight(20);
        r3.setPercentHeight(10);
        r4.setPercentHeight(35);
        r5.setPercentHeight(5);
        r6.setPercentHeight(10);
        r7.setPercentHeight(10);
        c1.setPercentWidth(15);
        c2.setPercentWidth(17.0);
        c3.setPercentWidth(10);
        c4.setPercentWidth(17.0);
        c5.setPercentWidth(10);
        c6.setPercentWidth(17.0);
        c7.setPercentWidth(14);

        form.getRowConstraints().addAll(r1, r2, r3, r4, r5, r6, r7);
        form.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7);
        // form.setGridLinesVisible(true);

        // mettere il for

        int i = 1;
        int j = 1;

        for (String s : event.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            form.add(imageView, i, j, 1, 1);
            i = i + 2;
        }

        List<String> list=event.getGods();
        List <String> list1=event.getEffects();

        Hyperlink button1=new Hyperlink();
        button1.setMaxSize(180.0,240.0);
        button1.setId(list.get(0));
        button1.setOnAction(f->{
            System.out.println(button1.getId());
            display(list1.get(0),list.get(0));
        });
        form.add(button1,1,1);

        Hyperlink button2=new Hyperlink();
        button2.setMaxSize(180.0,240.0);
        button2.setId(list.get(1));
        form.add(button2,3,1);
        button2.setOnAction(f->{
            System.out.println(button2.getId());
            display(list1.get(1),list.get(1));

        });

        if (list.size()==3){
        Hyperlink button3=new Hyperlink();
        button3.setMaxSize(180.0,240.0);
        button3.setId(list.get(2));
        form.add(button3,5,1);
        button3.setOnAction(f->{
            System.out.println(button3.getId());
            display(list1.get(2),list.get(2));
        });}

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose gods");
    }

    private void display(String s,String s1) {
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("Do you want to use this god?" +
                "\n \n                 this is " + s1+ "'s effect: ");

        GridPane gridPane=new GridPane();
        RowConstraints r1=new RowConstraints();
        RowConstraints r2=new RowConstraints();
        RowConstraints r3=new RowConstraints();

        ColumnConstraints c1=new ColumnConstraints();
        ColumnConstraints c2=new ColumnConstraints();
        ColumnConstraints c3=new ColumnConstraints();

        r1.setPercentHeight(20);
        r2.setPercentHeight(60);
        r3.setPercentHeight(20);

        c1.setPercentWidth(20);
        c2.setPercentWidth(60);
        c3.setPercentWidth(20);

        gridPane.getRowConstraints().addAll(r1, r2, r3);
        gridPane.getColumnConstraints().addAll(c1, c2, c3);
        gridPane.add(confirmMessage, 1,0);

        Label effect=new Label ();
        effect.setText(s);
        effect.setTextFill(Color.RED);

        gridPane.add(effect,1,1);

        Button confirm=new Button("Confirm");
        confirm.setMaxSize(150,40);
        gridPane.add(confirm,2,2);

        confirm.setOnAction(f->{
            Command command;
            command=new ChooseYourGod(s1);
            gui.getClient().send(command);
            stage.close();
        });

        scene=new Scene(gridPane,800,400,Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();
    }
}
