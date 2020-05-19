package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.ChooseYourGodEvent;
import it.polimi.ingsw.events.WaitYourGodEvent;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Insets;
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

import java.util.List;

public class WaitSelectYourGod implements GuiScreen {
    private Gui gui;
    private WaitYourGodEvent event;


    public WaitSelectYourGod(Gui gui, WaitYourGodEvent event) {
        this.gui = gui;
        this.event = event;
    }

    @Override
    public void setScene() {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-alignment: top-center");
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());

        Text testo = new Text("Wait! It's your opponent turn!");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);
        pane.setTop(title);


        GridPane center = new GridPane();
        center.setStyle("-fx-alignment: top-center");
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        center.getColumnConstraints().addAll(c1, c2, c3);
        center.getRowConstraints().addAll(r1, r2);

        pane.setCenter(center);

        int i = 0;

        for (String s : event.getGods()) {
            StackPane stack = new StackPane();
            stack.setStyle("-fx-alignment: center; -fx-border-color: black; -fx-border-width:10px;");
            Image image = new Image(s + ".jpg");
            ImageView imageView = new ImageView(image);
            stack.getChildren().add(imageView);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            center.add(stack, i, 0);
            i++;
        }

        List<String> list = event.getGods();
        List<String> list1 = event.getEffects();

        Hyperlink button1 = new Hyperlink();
        button1.setMaxSize(180.0, 240.0);
        button1.setId(list.get(0));
        center.add(button1, 0, 0);

        Hyperlink button2 = new Hyperlink();
        button2.setMaxSize(180.0, 240.0);
        button2.setId(list.get(1));
        center.add(button2, 1, 0);


        Hyperlink button3 = new Hyperlink();
        button3.setMaxSize(180.0, 240.0);

        if (list.size() == 3) {
            button3.setId(list.get(2));
            center.add(button3, 2, 0);
        }


        button1.setOnAction(f -> {
            System.out.println(button1.getId());
            display(list1.get(0), list.get(0));
        });

        button2.setOnAction(f -> {
            System.out.println(button2.getId());
            display(list1.get(1), list.get(1));
        });

        button3.setOnAction(f -> {
            System.out.println(button3.getId());
            display(list1.get(2), list.get(2));
        });

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("choose gods");
    }

    private void display(String s, String s1) {
        Stage stage;
        Scene scene;
        stage = new Stage(StageStyle.UTILITY);
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        BorderPane str = new BorderPane();
        Label confirmMessage = new Label("Do you want to use this god?");
        String style = "-fx-text-alignment:center; -fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: black;" + "-fx-opacity: 1;";
        confirmMessage.setStyle(style);
        str.setTop(confirmMessage);

        GridPane grid = new GridPane();
        grid.setStyle("-fx-alignment: center; -fx-border-color: black; -fx-border-width:10px;");
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        grid.getRowConstraints().addAll(r1, r2, r3);

        String styleEffect = "-fx-alignment: center; -fx-text-alignment:center; -fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: black;" + "-fx-opacity: 1;";
        Label effect = new Label("This is " + s1 + "'s effect:");
        Label description = new Label(s);
        effect.setStyle(styleEffect);
        description.setStyle(styleEffect);

        grid.add(effect, 0, 0);
        grid.add(description, 0, 1);


        str.setCenter(grid);
        scene = new Scene(str, 800, 400, Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();

    }

}
