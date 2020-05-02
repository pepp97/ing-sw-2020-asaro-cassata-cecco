package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseGods;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.ChooseYourGodEvent;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.SettingsEvent;
import it.polimi.ingsw.events.StartGameEvent;
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

import java.util.ArrayList;
import java.util.List;

public class SelectGodsWindow implements GuiScreen {
    private Gui gui;
    private StartGameEvent startGameEvent;
    private List<String> selected;
    static int k=1;

    public SelectGodsWindow(Gui gui, StartGameEvent startGameEvent) {
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
      // form.setGridLinesVisible(true);

        // mettere il for

        int i = 1;
        int j = 1;

        for (String s : startGameEvent.getGods()) {
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            imageView.setStyle("-fx-border-color: black; -fx-border-width:10px;");
            form.add(imageView, i, j, 1, 1);
            i = i + 2;
            if(i == 11){
                i = 1;
                j = j + 2;
            }
        }
        List<String> list=startGameEvent.getGods();
        selected=new ArrayList<>();

        Hyperlink button1=new Hyperlink();
        button1.setMaxSize(180.0,240.0);
        button1.setId(list.get(0));

        button1.setOnAction(f->{
            System.out.println(button1.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button1.getId()))
            selected.add(button1.getId());
        });
        form.add(button1,1,1);

        Hyperlink button2=new Hyperlink();
        button2.setMaxSize(180.0,240.0);
        button2.setId(list.get(1));
        form.add(button2,3,1);
        button2.setOnAction(f->{
            System.out.println(button2.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button2.getId()))
            selected.add(button2.getId());
        });

        Hyperlink button3=new Hyperlink();
        button3.setMaxSize(180.0,240.0);
        button3.setId(list.get(2));
        form.add(button3,5,1);
        button3.setOnAction(f->{
            System.out.println(button3.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button3.getId()))
            selected.add(button3.getId());

        });

        Hyperlink button4=new Hyperlink();
        button4.setMaxSize(180.0,240.0);
        button4.setId(list.get(3));
        form.add(button4,7,1);
        button4.setOnAction(f->{
            System.out.println(button4.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button4.getId()))
            selected.add(button4.getId());
        });
        Hyperlink button5=new Hyperlink();
        button5.setMaxSize(180.0,240.0);
        button5.setId(list.get(4));
        form.add(button5,9,1);
        button5.setOnAction(f->{
            System.out.println(button5.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button5.getId()))
            selected.add(button5.getId());
        });
        Hyperlink button6=new Hyperlink();
        button6.setMaxSize(180.0,240.0);
        button6.setId(list.get(5));
        form.add(button6,1,3);
        button6.setOnAction(f->{
            System.out.println(button6.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button6.getId()))
            selected.add(button6.getId());
        });
        Hyperlink button7=new Hyperlink();
        button7.setMaxSize(180.0,240.0);
        button7.setId(list.get(6));
        form.add(button7,3,3);
        button7.setOnAction(f->{
            System.out.println(button7.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button7.getId()))
            selected.add(button7.getId());
        });
        Hyperlink button8=new Hyperlink();
        button8.setMaxSize(180.0,240.0);
        button8.setId(list.get(7));
        form.add(button8,5,3);
        button8.setOnAction(f->{
            System.out.println(button8.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button8.getId()))
            selected.add(button8.getId());
        });
        Hyperlink button9=new Hyperlink();
        button9.setMaxSize(180.0,240.0);
        button9.setId(list.get(8));
        form.add(button9,7,3);
        button9.setOnAction(f->{
            System.out.println(button9.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button9.getId()))
            selected.add(button9.getId());
        });
        Button view=new Button("View your Gods");
        String style="-fx-text-fill: #ffffff;" +"-fx-background-color: #000000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        view.setStyle(style);
        form.add(view, 9,3);
        view.setOnAction(f->{
            System.out.println(selected.size());
            displaySelected(selected);});

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose gods");
    }

    private void displaySelected(List<String> selected) {
        k=1;
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("Do you want to use these gods?");
        GridPane gridPane=new GridPane();
        RowConstraints r1=new RowConstraints();
        RowConstraints r2=new RowConstraints();
        RowConstraints r3=new RowConstraints();
        RowConstraints r4=new RowConstraints();
        RowConstraints r5=new RowConstraints();
        ColumnConstraints c1=new ColumnConstraints();
        ColumnConstraints c2=new ColumnConstraints();
        ColumnConstraints c3=new ColumnConstraints();
        ColumnConstraints c4=new ColumnConstraints();
        r1.setPercentHeight(20);
        r2.setPercentHeight(10);
        r3.setPercentHeight(10);
        r4.setPercentHeight(10);
        r5.setPercentHeight(50);
        c1.setPercentWidth(25);
        c2.setPercentWidth(25);
        c3.setPercentWidth(15);
        c4.setPercentWidth(35);
        gridPane.getRowConstraints().addAll(r1, r2, r3, r4, r5);
        gridPane.getColumnConstraints().addAll(c1, c2, c3, c4);
        gridPane.add(confirmMessage, 1,0);
        int i=1;
        int j=1;
        for (String s: selected){
            Label l=new Label (s);
            String styleLabel="-fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: black;"+"-fx-opacity: 1;";
            l.setStyle(styleLabel);
            gridPane.add(l,i,j);
            j++;
        }



        String styleDelete="-fx-text-fill: #000000;" +"-fx-background-color: #ff0000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";

        if (selected.size()>=1) {
            Button button1=new Button("Delete");
            button1.setStyle(styleDelete);
            gridPane.add(button1,2,1);
            button1.setOnAction(f->{
                selected.remove(0);
                k=0;
                button1.setVisible(false); });
        }

        if (selected.size()>=2){
            Button button2=new Button("Delete");
            button2.setStyle(styleDelete);
            gridPane.add(button2,2,2);
            button2.setOnAction(f->{
                selected.remove(k);
                button2.setVisible(false);
            });}

        if (selected.size()==3){
            Button button3=new Button("Delete");
            button3.setStyle(styleDelete);
            gridPane.add(button3,2,3);
            button3.setOnAction(f->{
                selected.remove(selected.size()-1);
                button3.setVisible(false);
            });}

        Button confirm= new Button ("Confirm");
        String styleconfirm="-fx-text-fill: #000000;" +"-fx-background-color: #00ff00;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        confirm.setStyle(styleconfirm);
        gridPane.add(confirm, 3,4);
        confirm.setOnAction(f->{

            Command command;
            if(selected.size()==startGameEvent.getNumPlayers()){
             command=new ChooseGods(selected);
             gui.getClient().send(command);

                stage.close();
             confirm.setVisible(false);

            }
        });
        scene=new Scene(gridPane,800,400,Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();

    }

}
