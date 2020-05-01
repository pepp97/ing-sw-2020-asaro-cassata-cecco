package it.polimi.ingsw.view;

import it.polimi.ingsw.Client;
import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Connection;
import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.gui.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Gui extends  Application implements View {
    private Stage primaryStage;
    private Client client;
    private SelectBox exitGame;
    private GuiScreen state;
    private SettingsWindow settings;
    private AskUserWindow askUs;
    private DeathPlayerWindow death;
    private EndGameWindow end;
    private List<String> gods=new ArrayList<>();
    private List<String> nicknames = new ArrayList<>();
    private ErrorWindow error;



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

    public List<String> getGods() {
        return gods;
    }

    public void setGods(List<String> gods) {
        this.gods = gods;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
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


        exitGame = new SelectBox("Do you really want to exit??");


        boolean continua;

        StackPane stackPane = new StackPane();
        startMenu = new Scene(stackPane,widthScreen,heightScreen );

        GridPane structure = new GridPane();
        RowConstraints l1 = new RowConstraints();
        l1.setValignment(VPos.CENTER);
        RowConstraints l2 = new RowConstraints();
        structure.getRowConstraints().addAll(l1, l2);

        structure.setStyle("-fx-alignment: top-center;");

        StackPane top= new StackPane();
        top.setStyle("-fx-background-color: #000000;");
        StackPane bottom= new StackPane();

        structure.add(top,0,0);
        structure.add(bottom,0,1);


        Image intro = new Image("SantoriniIntro.jpg", widthScreen,heightScreen,true, true); //modificare percorso.
        ImageView imageView = new ImageView();
        imageView.setImage(intro);
        stackPane.getChildren().add(structure);
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        structure.setBackground(new Background(backgroundImage));


        Text testo = new Text("CONNECTION");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);



        top.getChildren().add(title);
        top.setMinWidth(structure.getLayoutX());
        top.setStyle("-fx-alignment: center; -fx-border-color: black;");
        top.setAlignment(title,Pos.CENTER);


        GridPane menu=new GridPane();
        menu.setMinWidth(250);
        menu.setMinHeight(180);
        RowConstraints r1 = new RowConstraints();
        r1.setMinHeight(30);
        RowConstraints r2 = new RowConstraints();
        r2.setMinHeight(30);
        RowConstraints r3 = new RowConstraints();
        r3.setMinHeight(30);
        RowConstraints r4 = new RowConstraints();
        r3.setMinHeight(30);
        menu.getRowConstraints().addAll(r1, r2,r3,r4);

        menu.setStyle("-fx-alignment: center;  -fx-border-color: black;");

        TextField ipInput = new TextField("localhost");
        ipInput.setPromptText("IP Default: localhost");
        menu.add(ipInput,0,0);

        TextField portInput = new TextField("8080");
        portInput.setPromptText("Default port:8080");
        menu.add(portInput,0,1);


        GridPane bottomGrid=new GridPane();
        bottomGrid.setMinWidth(150);
        bottomGrid.setMinHeight(60);
        ColumnConstraints c11= new ColumnConstraints();
        c11.setMinWidth(120);
        ColumnConstraints c22= new ColumnConstraints();
        c22.setMinWidth(120);
        c11.setHalignment(HPos.LEFT);
        c22.setHalignment(HPos.RIGHT);
        bottomGrid.getColumnConstraints().addAll(c11,c22);





        Button connectionButton = new Button("Vai al Login");
        connectionButton.setStyle("-fx-alignment: center;");
        String styleLogin="-fx-text-fill: #ffffff;" +"-fx-background-color: #000000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        connectionButton.setStyle(styleLogin);
        StackPane left=new StackPane();
        left.setStyle("-fx-alignment: center;");
        left.getChildren().add(connectionButton);
        left.setAlignment(connectionButton,Pos.CENTER);
        bottomGrid.add(left,0,0);

        Button close = new Button("exit");
        String styleExit="-fx-text-fill: #000000;" +"-fx-background-color: #ff0000;"+"-fx-font-size: 12pt;"+"-fx-font-weight: bold;"+"-fx-border-radius: 20;"+"-fx-background-radius: 20;";
        close.setStyle(styleExit);
        StackPane right=new StackPane();
        right.setStyle("-fx-alignment: center;");
        right.getChildren().add(close);
        right.setAlignment(close,Pos.CENTER);
        bottomGrid.add(right,1,0);

        menu.add(bottomGrid,0,2);

        structure.add(menu,0,1);

        primaryStage.setScene(startMenu);
        Stage second = new Stage();
        second.initOwner(primaryStage);

        primaryStage.show();

        connectionButton.setOnAction(e -> {
                    if (ipInput.getText().equals("") || portInput.getText().equals("")) {
                        Label error = new Label("COMPLETE THE FIELDS!!");
                        String styleError="-fx-alignment: center;-fx-font-size: 14pt;" + "-fx-font-family: \"Segoe UI Semibold\";" + "-fx-text-fill: red;"+"-fx-opacity: 1;";
                        error.setStyle(styleError);

                        menu.add(error,0,3);

                    } else {
                        try {
                            client = new Client(ipInput.getText(), Integer.decode(portInput.getText()),this);
                            client.start();
                            Connection command=new Connection();
                            client.send(command);


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }

        );


    }

    /*
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

        String style="-fx-border-color: black; -fx-background-color:white;";
        GridPane.setStyle();
        GridPane.setConstraints(ipInput, 150, 120);


        TextField portInput = new TextField("8080");
        portInput.setPromptText("Default port:8080");
        GridPane.setConstraints(portInput, 150, 128);
        Button connectionButton = new Button("Vai al Login");
        GridPane.setConstraints(connectionButton, 150, 140);
        primaryStage.show();
        connectionButton.setOnAction(e -> {
                    if (ipInput.getText().equals("") || portInput.getText().equals("")) {
                        Label error = new Label("        Completa i campi errati");
                        GridPane.setConstraints(error, 150, 80);
                        menu.getChildren().add(0, error);
                    } else {
                        try {
                            client = new Client(ipInput.getText(), Integer.decode(portInput.getText()),this);
                            client.start();
                            Connection command=new Connection();
                            client.send(command);


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

     */

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

    public void update(StartMatchEvent startMatchEvent){
        Platform.runLater(() -> {

            for(Map.Entry iterator : startMatchEvent.getGodPlayer().entrySet()){
                nicknames.add((String) iterator.getKey());
                gods.add((String) iterator.getValue());
            }

            this.state = new ChooseStarterWindow(this,startMatchEvent);
            state.setScene();
        });

    }

    public void update(ExceptionEvent exceptionEvent){
        Platform.runLater(()->{
            error= new ErrorWindow(this,exceptionEvent);
            error.displayMessage(primaryStage);});
    }
    public void update (SettingsEvent settingsEvent){
        Platform.runLater(()->{
            settings = new SettingsWindow(this);
            settings.displayMessage(primaryStage);});

    }

    public void update(StartGameEvent startGameEvent){
        System.out.println("STARTGAME PROVA!");
        Platform.runLater(() -> {
            this.state = new SelectGodsWindow(this,startGameEvent);
            state.setScene();
        });
    }


    public void update(ChooseYourGodEvent event){
        Platform.runLater(() -> {
            this.state = new SelectYourGod(this,event);
            state.setScene();
        });
    }

    public void update (UpdateEvent event){
        Platform.runLater(() -> {
            this.state = new GameWindow(this,event);
            state.setScene();
        });
    }

    public void update (askUser ask){
        Platform.runLater(()->{
        askUs = new AskUserWindow(this);
        askUs.displayMessage(primaryStage);});

    }

    public void update (DeathPlayer deathPlayer){
        death = new DeathPlayerWindow(this, deathPlayer);
        death.displayMessage(primaryStage);

    }

    public void update (EndGame endGame){


        Platform.runLater(() -> {
            this.state = new EndGameWindow(this, endGame);
            state.setScene();
        });


    }

    public void update(ChooseTarget event){
        Platform.runLater(() -> {
            this.state = new ChooseTargetWindow(this,event);
            state.setScene();
        });
    }

    public void update(SetWorkerEvent event){
        Platform.runLater(() -> {
            this.state = new StartMatchWindow(this,event);
            state.setScene();
        });
    }

    public void update(ChooseWorker event){
        Platform.runLater(() -> {
            this.state = new ChooseWorkerWindow(this,event);
            state.setScene();
        });
    }

    /*public void update(LogoutSuccessful event){
        Platform.runLater(() -> {
            this.state = new LogoutWindow(this,event);
            state.setScene();
        });
    }*/


}