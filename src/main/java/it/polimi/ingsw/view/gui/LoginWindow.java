package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.ArrayList;

/**
 * This window allows users to login into the game.
 * Users must be registered to the game with an unique name and color.
 */
public class LoginWindow implements GuiScreen {
    private Gui gui;

    public LoginWindow(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void setScene() {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());
        pane.setMaxWidth(gui.getWidthScreen());
        pane.setMaxHeight(gui.getHeightScreen());

        Text testo = new Text("LOGIN");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);

        pane.setTop(title);
        StackPane center = new StackPane();

        center.setMaxHeight(600);
        center.setMinHeight(300);
        center.setMaxWidth(500);
        center.setMinWidth(300);
        //center.setStyle("-fx-border-color: black");

        Background back = new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY));
        GridPane structure = new GridPane();
        structure.setBackground(back);
        RowConstraints l1 = new RowConstraints();
        RowConstraints l2 = new RowConstraints();
        l2.setMinHeight(120);
        RowConstraints l3 = new RowConstraints();
        RowConstraints l4 = new RowConstraints();
        structure.getRowConstraints().addAll(l1, l2, l3, l4);

        l2.setValignment(VPos.CENTER);

        Background background = new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        StackPane top = new StackPane();
        top.setBackground(background);
        top.setMaxHeight(80);
        top.setMinHeight(80);
        top.setMaxWidth(260);
        top.setMinWidth(260);
        //top.setStyle("-fx-alignment: center");
        top.setStyle("-fx-border-color: black; -fx-border-width:3px;");


        StackPane medium = new StackPane();
        medium.setBackground(background);
        medium.setMaxHeight(100);
        medium.setMinHeight(100);
        medium.setMaxWidth(260);
        medium.setMinWidth(260);
        //medium.setStyle("-fx-alignment: center");
        medium.setStyle("-fx-border-color: black; -fx-border-width:3px;");

        StackPane bottom = new StackPane();
        bottom.setBackground(background);
        bottom.setMaxHeight(80);
        bottom.setMinHeight(80);
        bottom.setMaxWidth(260);
        bottom.setMinWidth(260);
        //bottom.setStyle("-fx-alignment: center");
        bottom.setStyle("-fx-border-color: black; -fx-border-width:3px;");

        StackPane error = new StackPane();
        error.setMaxHeight(40);
        error.setMinHeight(50);
        error.setMaxWidth(260);
        error.setMinWidth(260);
        //structure.setMaxHeight(370);
        //structure.setMaxWidth(100);


        GridPane topGrid = new GridPane();
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        c1.setMinWidth(130);
        c1.setMaxWidth(130);
        c2.setMinWidth(130);
        c2.setMaxWidth(130);
        topGrid.setMaxHeight(80);
        topGrid.setMinHeight(80);
        c1.setHalignment(HPos.CENTER);
        c2.setHalignment(HPos.CENTER);
        topGrid.getColumnConstraints().addAll(c1, c2);
        topGrid.setStyle("-fx-alignment:center");
        //topGrid.setStyle("-fx-border-color: black");

        GridPane mediumGrid = new GridPane();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
        c3.setMinWidth(130);
        c3.setMaxWidth(130);
        c4.setMinWidth(130);
        c4.setMaxWidth(130);
        mediumGrid.setMaxHeight(100);
        mediumGrid.setMinHeight(100);
        c3.setHalignment(HPos.CENTER);
        c4.setHalignment(HPos.CENTER);
        mediumGrid.getColumnConstraints().addAll(c3, c4);
        //mediumGrid.setStyle("-fx-border-color: black");
        mediumGrid.setStyle("-fx-alignment:center-right");


        TextField nameInput = new TextField("");
        Label istr1 = new Label("Nickname:");
        istr1.setMinWidth(100);
        istr1.setMaxWidth(100);
        nameInput.setMaxWidth(100);
        nameInput.setMinWidth(100);
        istr1.setTextFill(Color.BLACK);
        istr1.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        //istr1.setStyle("-fx-alignment: left");
        //istr1.setStyle("-fx-border-color: black");
        istr1.setLabelFor(nameInput);
        topGrid.add(istr1, 0, 0);
        topGrid.add(nameInput, 1, 0);


        Label istr2 = new Label("Color:");
        istr2.setTextFill(Color.BLACK);
        istr2.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        //istr2.setStyle("-fx-alignment: left");
        //istr2.setStyle("-fx-border-color: black");
        mediumGrid.add(istr2, 0, 0);


        ToggleGroup group = new ToggleGroup();
        RadioButton black = new RadioButton("       ");
        black.setToggleGroup(group);
        RadioButton brown = new RadioButton("       ");
        brown.setToggleGroup(group);
        RadioButton white = new RadioButton("       ");
        white.setToggleGroup(group);
        ArrayList<RadioButton> Bottoni = new ArrayList<>();
        Bottoni.add(black);
        Bottoni.add(white);
        Bottoni.add(brown);
        black.setId("black");
        white.setId("white");
        brown.setId("brown");


        GridPane colorGrid = new GridPane();
        RowConstraints r11 = new RowConstraints();
        RowConstraints r22 = new RowConstraints();
        RowConstraints r33 = new RowConstraints();
        r11.setMaxHeight(60);
        r11.setMinHeight(30);
        r22.setMaxHeight(60);
        r22.setMinHeight(30);
        r33.setMaxHeight(60);
        r33.setMinHeight(30);
        r11.setValignment(VPos.TOP);
        r22.setValignment(VPos.CENTER);
        r33.setValignment(VPos.BOTTOM);
        colorGrid.add(black, 0, 0);
        colorGrid.add(white, 0, 1);
        colorGrid.add(brown, 0, 2);
        black.setStyle("-fx-background-color: Black");
        white.setStyle("-fx-background-color: White");
        brown.setStyle("-fx-background-color: Brown;");

        colorGrid.setStyle("-fx-alignment:center");


        mediumGrid.add(colorGrid, 1, 0);


        Button connect = new Button("CONNECT");
        //bottomGrid.add(connect, 1, 0);
        String style = "-fx-text-fill: #ffffff;" + "-fx-background-color: #000000;" + "-fx-font-size: 12pt;" + "-fx-font-weight: bold;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";
        connect.setStyle(style);


        connect.setOnAction(f -> {
            String color = null;
            if (!nameInput.getText().equals("")) {
                for (RadioButton button : Bottoni)
                    if (button.isSelected()) {
                        color = button.getId();

                        try {
                            LoginCommand command = new LoginCommand(nameInput.getText(), it.polimi.ingsw.model.Color.valueOf(color.toUpperCase()));
                            gui.getClient().send(command);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                if (color == null) {
                    Label tryAgain = new Label("Seleziona un colore!");
                    String styleError = "-fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: red;" + "-fx-opacity: 1;";
                    tryAgain.setStyle(styleError);
                    error.getChildren().add(tryAgain);
                    error.setAlignment(tryAgain, Pos.CENTER);
                }
            }
        });


        top.getChildren().add(topGrid);
        top.setAlignment(topGrid, Pos.CENTER);
        top.setAlignment(Pos.CENTER);

        medium.getChildren().add(mediumGrid);
        medium.setAlignment(mediumGrid, Pos.CENTER);
        medium.setAlignment(Pos.CENTER);

        bottom.getChildren().add(connect);
        bottom.setAlignment(connect, Pos.CENTER);

        //bottom.getChildren().add(bottomGrid);
        //bottom.setAlignment(bottomGrid, Pos.CENTER);

        bottom.setAlignment(Pos.CENTER);


        structure.add(top, 0, 0);
        structure.add(medium, 0, 1);
        structure.add(bottom, 0, 2);
        structure.add(error, 0, 3);
        structure.setStyle("-fx-alignment: center");

        center.getChildren().add(structure);
        center.setAlignment(structure, Pos.CENTER);
        center.setStyle("-fx-border-color: black; -fx-border-width:15px;");


        pane.setCenter(center);
        pane.getCenter().setStyle("-fx-alignment: center; -fx-border-color: black");
        pane.setStyle("-fx-alignment: center");

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("Login Window");
    }
}
