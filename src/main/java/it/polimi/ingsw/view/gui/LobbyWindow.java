package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.events.LoginSuccessful;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.List;

public class LobbyWindow implements GuiScreen {
    private Gui gui;
    private LoginSuccessful event;

    public LobbyWindow(Gui gui, LoginSuccessful loginSuccessful) {
        this.gui=gui;
        this.event=loginSuccessful;
    }

    @Override
    public void setScene() {
        BorderPane structure=new BorderPane();
        createGUI(structure);
    }

    private void createGUI(BorderPane structure) {

        Scene scene=new Scene(structure,gui.getWidthScreen(),gui.getHeightScreen());

        Text testo = new Text("LOBBY");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);

        structure.setTop(title);
        structure.setMaxWidth(gui.getWidthScreen());
        structure.setMaxHeight(gui.getHeightScreen());
        structure.setStyle("-fx-alignment: top-center");
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        structure.setBackground(confirmBackground);

        StackPane center = new StackPane();

        GridPane centralGrid=new GridPane();
        centralGrid.setMinHeight(300);

        centralGrid.setStyle("-fx-alignment: top-center;");
        RowConstraints r1=new RowConstraints();
        RowConstraints r2=new RowConstraints();
        centralGrid.getRowConstraints().addAll(r1,r2);

        GridPane player=new GridPane();
        player.setStyle("-fx-alignment: center; -fx-border-color: black; -fx-border-width:30px;");
        player.setMinHeight(200);
        player.setMinWidth(80);
        RowConstraints p1=new RowConstraints();
        RowConstraints p2=new RowConstraints();
        RowConstraints p3=new RowConstraints();
        player.getRowConstraints().addAll(p1,p2,p3);

        Label welcome= new Label("Welcome! \n Wait the start of the match, they're playing:");

        String styleWelcome="-fx-text-alignment:center; -fx-font-size: 18pt;"+ "-fx-text-fill: black;"+"-fx-font-weight: bold;"+"-fx-opacity: 1;"+"-fx-alignment: center;-fx-border-color: black;";
        welcome.setStyle(styleWelcome);
        centralGrid.add(welcome,0,0);

        List<String> color= new ArrayList<>();
        color.add("#4169e1");
        color.add("#228B22");
        color.add("#ff7f50");
        int i=0;
        for(String p: event.getNickname()){
            StackPane panePlayer= new StackPane();
            panePlayer.setMinHeight(60);
            panePlayer.setMinWidth(60);
            panePlayer.setStyle("-fx-background-color:"+color.get(i)+";"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;");
            Label newPlayer= new Label(p);
            newPlayer.setMinHeight(40);
            String stylenewPlayer= "-fx-text-alignment:center; -fx-font-size: 16pt;"+"-fx-font-weight: bold;"+"-fx-opacity: 1;"+"-fx-text-fill: #000000;";
            newPlayer.setStyle(stylenewPlayer);
            panePlayer.getChildren().add(newPlayer);
            panePlayer.setAlignment(newPlayer, Pos.CENTER);
            player.add(panePlayer,0,i);
            i++;
        }


        centralGrid.add(player,0,1);
        center.getChildren().add(centralGrid);
        center.setAlignment(centralGrid, Pos.CENTER);
        structure.setCenter(center);

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("WaitRoom Window");
    }

}

/*
Platform.runLater(()->{
            AnchorPane anchorPane=new AnchorPane();
            createAnchor(anchorPane);

        });
 */



/*
Scene scene=new Scene(anchorPane,gui.getWidthScreen(),gui.getHeightScreen());
        Label welcome=new Label ("Benvenuto! \n Attendere l'inizio della partita, stanno giocando: ");
        AnchorPane.setLeftAnchor(welcome, 600.0);
        AnchorPane.setTopAnchor(welcome, 100.0);

        anchorPane.getChildren().add(0, welcome);
        HBox hbox=new HBox(50.0);
        VBox vBox=new VBox(25.0);
        hbox.getChildren().add(vBox);

        for(String p: event.getNickname()){

            Label newPlayer=new Label(p);
            vBox.getChildren().add(newPlayer);


        }

        AnchorPane.setTopAnchor(hbox, 200.0);
        AnchorPane.setLeftAnchor(hbox, 550.0);
        anchorPane.getChildren().add(0, hbox);
        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("WaitRoom Window");
 */
