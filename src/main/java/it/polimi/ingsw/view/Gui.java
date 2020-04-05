package it.polimi.ingsw.view;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Gui extends  Application implements View {
    private Stage primaryStage;
    private Client client;
    private SelectBox exitGame;
    private GuiScreen state;
    private SettingsWindow settings;

    private  double widthScreen = Screen.getPrimary().getBounds().getWidth();
   private double heightScreen = Screen.getPrimary().getBounds().getHeight()-40.00;


    public static void main() {
        launch();
    }

    public double getWidthScreen() {
        return widthScreen;
    }

    public double getHeightScreen() {
        return heightScreen;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {





        this.primaryStage = primaryStage;

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("launcher Santorini");
        primaryStage.setResizable(true);
        //primaryStage.setFullScreen(true);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeApplication();
        });
         Scene startMenu;

        exitGame = new SelectBox("Vuoi uscire dal gioco?");
        GridPane menu = new GridPane();
        menu.setPadding(new Insets(50, 50, 50, 50));
        menu.setHgap(1);
        menu.setVgap(1);

         boolean continua;
        StackPane stackPane = new StackPane();


        startMenu = new Scene(stackPane,widthScreen,heightScreen );
        Image intro = new Image("SantoriniIntro.jpg", widthScreen,heightScreen,true, true); //modificare percorso.

           //Image intro = new Image("SantoriniIntro.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(intro);
         //   ImageViewPane imageViewPane = new ImageViewPane(imageView);
           // stackPane.getChildren().add(imageViewPane);
        stackPane.getChildren().add(menu);
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        menu.setBackground(new Background(backgroundImage));
        continua = false;

        menu.setAlignment(Pos.CENTER);
        primaryStage.setScene(startMenu);
        Stage second = new Stage();
        second.initOwner(primaryStage);
        TextField ipInput = new TextField("localhost");
        ipInput.setPromptText("ip default: localhost");

        GridPane.setConstraints(ipInput, 150, 120);

        TextField portInput = new TextField("8080");
        portInput.setPromptText("Default port:8080");
        GridPane.setConstraints(portInput, 150, 128);
        Button connectionButton = new Button("Vai al Login");
        GridPane.setConstraints(connectionButton, 150, 140);
        primaryStage.show();
       connectionButton.setOnAction(e -> {
                    if (ipInput.getText().equals("") || portInput.getText().equals("")) {
                        Label error = new Label("        Completa i campi errati, scemo");
                        GridPane.setConstraints(error, 150, 80);
                        menu.getChildren().add(0, error);
                    } else {
                         try {
                        client = new Client(ipInput.getText(), Integer.decode(portInput.getText()),this);

                             Event event = new ConnectionSuccessful();
                             event.send(this);
                         } catch (Exception e1) {
                             e1.printStackTrace();
                            }
                        }
                    }

                     );


        Button close = new Button("exit");
        GridPane.setConstraints(close, 151, 140);
        menu.getChildren().addAll(connectionButton, close, ipInput, portInput);
       // close.setOnAction(e -> closeApplication());


    }

    private void closeApplication() {


        if (exitGame.displayMessage(primaryStage)) {
            primaryStage.close();
            Platform.exit();
            System.out.println("Sei uscito dal gioco con successo. \n Per fare l'upgrade mettere un do while nel launcher per riproporre la scelta");
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //show login window

    public void update(ConnectionSuccessful e){
        Platform.runLater(() -> {
            this.state = new LoginWindow(this);
            state.setScene();
        });
    }


    public void update(LoginSuccessful loginSuccessful){
        Platform.runLater(() -> {
            this.state = new LobbyWindow(this,loginSuccessful);
            state.setScene();
        });
    }

    @Override
    public void update(Event event) {

    }

    public void update (SettingsEvent settingsEvent){
        settings = new SettingsWindow(this);
        settings.displayMessage(primaryStage);

    }

    public void update(StartGameEvent startGameEvent){

        Platform.runLater(() -> {
            this.state = new SelectGodWindow(this,startGameEvent);
            state.setScene();
        });


    }


}


