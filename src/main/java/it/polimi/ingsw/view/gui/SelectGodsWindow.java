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
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class SelectGodsWindow implements GuiScreen {
    private Gui gui;
    private StartGameEvent startGameEvent;
    private List<String> selected;
    static int k = 1;

    public SelectGodsWindow(Gui gui, StartGameEvent startGameEvent) {
        this.gui = gui;
        this.startGameEvent = startGameEvent;
    }

    public void setScene() {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-alignment: center;");
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());

        Text testo = new Text("Select " +startGameEvent.getNumPlayers() + " Gods!");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);
        pane.setTop(title);

        GridPane structure = new GridPane();
        structure.setStyle("-fx-alignment: center;");
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        r2.setMinHeight(70);
        structure.getRowConstraints().addAll(r1, r2);

        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        structure.setBackground(confirmBackground);
        pane.setCenter(structure);

        GridPane gods = new GridPane();
        gods.setStyle("-fx-alignment:  center;");
        RowConstraints gr1 = new RowConstraints();
        RowConstraints gr2 = new RowConstraints();
        ColumnConstraints gc1 = new ColumnConstraints();
        ColumnConstraints gc2 = new ColumnConstraints();
        ColumnConstraints gc3 = new ColumnConstraints();
        ColumnConstraints gc4 = new ColumnConstraints();
        ColumnConstraints gc5 = new ColumnConstraints();
        gods.getRowConstraints().addAll(gr1, gr2);
        gods.getColumnConstraints().addAll(gc1, gc2, gc3, gc4, gc5);

        int i = 0;
        int j = 0;
        for (String s : startGameEvent.getGods()) {
            StackPane stack = new StackPane();
            stack.setAlignment(Pos.CENTER);
            stack.setStyle("-fx-border-color: black; -fx-border-width:10px;");
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            stack.getChildren().add(imageView);
            stack.setAlignment(imageView,Pos.CENTER);
            gods.add(stack, i, j);
            if (i == 4) {
                i = -1;
                j = 1;
            }
            i++;
        }

        structure.add(gods,0,0);

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
        gods.add(button1,0,0);

        Hyperlink button2=new Hyperlink();
        button2.setMaxSize(180.0,240.0);
        button2.setId(list.get(1));
        gods.add(button2,1,0);
        button2.setOnAction(f->{
            System.out.println(button2.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button2.getId()))
                selected.add(button2.getId());
        });

        Hyperlink button3=new Hyperlink();
        button3.setMaxSize(180.0,240.0);
        button3.setId(list.get(2));
        gods.add(button3,2,0);
        button3.setOnAction(f->{
            System.out.println(button3.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button3.getId()))
                selected.add(button3.getId());

        });

        Hyperlink button4=new Hyperlink();
        button4.setMaxSize(180.0,240.0);
        button4.setId(list.get(3));
        gods.add(button4,3,0);
        button4.setOnAction(f->{
            System.out.println(button4.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button4.getId()))
                selected.add(button4.getId());
        });
        Hyperlink button5=new Hyperlink();
        button5.setMaxSize(180.0,240.0);
        button5.setId(list.get(4));
        gods.add(button5,4,0);
        button5.setOnAction(f->{
            System.out.println(button5.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button5.getId()))
                selected.add(button5.getId());
        });
        Hyperlink button6=new Hyperlink();
        button6.setMaxSize(180.0,240.0);
        button6.setId(list.get(5));
        gods.add(button6,0,1);
        button6.setOnAction(f->{
            System.out.println(button6.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button6.getId()))
                selected.add(button6.getId());
        });
        Hyperlink button7=new Hyperlink();
        button7.setMaxSize(180.0,240.0);
        button7.setId(list.get(6));
        gods.add(button7,1,1);
        button7.setOnAction(f->{
            System.out.println(button7.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button7.getId()))
                selected.add(button7.getId());
        });
        Hyperlink button8=new Hyperlink();
        button8.setMaxSize(180.0,240.0);
        button8.setId(list.get(7));
        gods.add(button8,2,1);
        button8.setOnAction(f->{
            System.out.println(button8.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button8.getId()))
                selected.add(button8.getId());
        });
        Hyperlink button9=new Hyperlink();
        button9.setMaxSize(180.0,240.0);
        button9.setId(list.get(8));
        gods.add(button9,3,1);
        button9.setOnAction(f->{
            System.out.println(button9.getId());
            if(selected.size()<startGameEvent.getNumPlayers() && !selected.contains(button9.getId()))
                selected.add(button9.getId());
        });

        StackPane stack = new StackPane();
        stack.setAlignment(Pos.CENTER);
        Button view=new Button("View selected Gods");
        String style="-fx-alignment: center; -fx-text-fill: #ffffff;" +"-fx-background-color: #000000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        view.setStyle(style);
        stack.getChildren().add(view);
        stack.setAlignment(view,Pos.CENTER);
        structure.add(stack, 0,1);
        view.setOnAction(f->{
            System.out.println(selected.size());
            displaySelected(selected);});

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose gods");

    }

    private void displaySelected(List<String> selected) {
        k = 1;
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label("Do you want to use these gods?");
        confirmMessage.setMinWidth(300);
        String style="-fx-text-alignment:left; -fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: black;"+"-fx-opacity: 1;";
        confirmMessage.setStyle(style);
        GridPane gridPane = new GridPane();
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        RowConstraints r4 = new RowConstraints();
        RowConstraints r5 = new RowConstraints();
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
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
        gridPane.add(confirmMessage, 1, 0);
        int i = 1;
        int j = 1;
        for (String s : selected) {
            Label l = new Label(s);
            String styleLabel = "-fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: black;" + "-fx-opacity: 1;";
            l.setStyle(styleLabel);
            gridPane.add(l, i, j);
            j++;
        }


        String styleDelete = "-fx-text-fill: #000000;" + "-fx-background-color: #ff0000;" + "-fx-font-size: 12pt;" + "-fx-font-weight: bold;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";

        if (selected.size() >= 1) {
            Button button1 = new Button("Delete");
            button1.setStyle(styleDelete);
            gridPane.add(button1, 2, 1);
            button1.setOnAction(f -> {
                selected.remove(0);
                k = 0;
                button1.setVisible(false);
            });
        }

        if (selected.size() >= 2) {
            Button button2 = new Button("Delete");
            button2.setStyle(styleDelete);
            gridPane.add(button2, 2, 2);
            button2.setOnAction(f -> {
                selected.remove(k);
                button2.setVisible(false);
            });
        }

        if (selected.size() == 3) {
            Button button3 = new Button("Delete");
            button3.setStyle(styleDelete);
            gridPane.add(button3, 2, 3);
            button3.setOnAction(f -> {
                selected.remove(selected.size() - 1);
                button3.setVisible(false);
            });
        }

        Button confirm = new Button("Confirm");
        String styleconfirm = "-fx-text-fill: #000000;" + "-fx-background-color: #00ff00;" + "-fx-font-size: 12pt;" + "-fx-font-weight: bold;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";
        confirm.setStyle(styleconfirm);
        gridPane.add(confirm, 3, 4);
        confirm.setOnAction(f -> {

            Command command;
            if (selected.size() == startGameEvent.getNumPlayers()) {
                command = new ChooseGods(selected);
                gui.getClient().send(command);

                stage.close();
                confirm.setVisible(false);

            }
        });
        scene = new Scene(gridPane, 800, 400, Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();

    }

}
