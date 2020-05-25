package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.ChooseYourGod;
import it.polimi.ingsw.commands.Command;
import it.polimi.ingsw.events.*;
import it.polimi.ingsw.model.Field;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;
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
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class GameWindow implements GuiScreen {
    private Gui gui;
    private UpdateEvent event;
    private Hyperlink[][] buttons;
    private StackPane[][] stacks;
    private GridPane[][] gridButtons;

    public GameWindow(Gui gui, UpdateEvent event) {
        this.gui = gui;
        this.event = event;
    }

    @Override
    public void setScene() {
        SquareToJson[][] squares = event.getSquares();
        int i;
        int j;
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-alignment:center");
        Scene scene = new Scene(pane, gui.getWidthScreen(), gui.getHeightScreen());

        Text testo = new Text("GAME WINDOW");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 25));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);
        pane.setTop(title);

        StackPane center = new StackPane();
        GridPane grid = new GridPane();
        grid.setStyle("-fx-alignment: center;");


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

        //grid.setGridLinesVisible(true);
        int x=635;
        int y=635;
        int gridX=scalingX(x);
        int gridY=scalingY(y);
        grid.setMinSize(gridX,gridY);
        grid.setMaxSize(gridX,gridY);
        grid.getRowConstraints().addAll(r1, r2, r3, r4, r5);
        grid.getColumnConstraints().addAll(c1, c2, c3, c4, c5);

        Hyperlink button00 = new Hyperlink();
        button00.setVisible(false);
        grid.add(button00, 0, 0);

        Hyperlink button10 = new Hyperlink();
        button10.setVisible(false);
        grid.add(button10, 1, 0);

        Hyperlink button20 = new Hyperlink();
        button20.setVisible(false);
        grid.add(button20, 2, 0);

        Hyperlink button30 = new Hyperlink();
        button30.setVisible(false);
        grid.add(button30, 3, 0);

        Hyperlink button40 = new Hyperlink();
        button40.setVisible(false);
        grid.add(button40, 4, 0);

        Hyperlink button01 = new Hyperlink();
        button01.setVisible(false);
        grid.add(button01, 0, 1);

        Hyperlink button11 = new Hyperlink();
        button11.setVisible(false);
        grid.add(button11, 1, 1);

        Hyperlink button21 = new Hyperlink();
        button21.setVisible(false);
        grid.add(button21, 2, 1);

        Hyperlink button31 = new Hyperlink();
        button31.setVisible(false);
        grid.add(button31, 3, 1);

        Hyperlink button41 = new Hyperlink();
        button41.setVisible(false);
        grid.add(button41, 4, 1);

        Hyperlink button02 = new Hyperlink();
        button02.setVisible(false);
        grid.add(button02, 0, 2);

        Hyperlink button12 = new Hyperlink();
        button12.setVisible(false);
        grid.add(button12, 1, 2);

        Hyperlink button22 = new Hyperlink();
        button22.setVisible(false);
        grid.add(button22, 2, 2);

        Hyperlink button32 = new Hyperlink();
        button32.setVisible(false);
        grid.add(button32, 3, 2);

        Hyperlink button42 = new Hyperlink();
        button42.setVisible(false);
        grid.add(button42, 4, 2);

        Hyperlink button03 = new Hyperlink();
        button03.setVisible(false);
        grid.add(button03, 0, 3);

        Hyperlink button13 = new Hyperlink();
        button13.setVisible(false);
        grid.add(button13, 1, 3);

        Hyperlink button23 = new Hyperlink();
        button23.setVisible(false);
        grid.add(button23, 2, 3);

        Hyperlink button33 = new Hyperlink();
        button33.setVisible(false);
        grid.add(button33, 3, 3);

        Hyperlink button43 = new Hyperlink();
        button43.setVisible(false);
        grid.add(button43, 4, 3);

        Hyperlink button04 = new Hyperlink();
        button04.setVisible(false);
        grid.add(button04, 0, 4);

        Hyperlink button14 = new Hyperlink();
        button14.setVisible(false);
        grid.add(button14, 1, 4);

        Hyperlink button24 = new Hyperlink();
        button24.setVisible(false);
        grid.add(button24, 2, 4);

        Hyperlink button34 = new Hyperlink();
        button34.setVisible(false);
        grid.add(button34, 3, 4);

        Hyperlink button44 = new Hyperlink();
        button44.setVisible(false);
        grid.add(button44, 4, 4);


        Image sfondo = new Image("SantoriniBoard.png", gui.getWidthScreen(), gui.getHeightScreen(), true, true); //modificare percorso.
        ImageView imageView = new ImageView();
        imageView.setImage(sfondo);
        Background image = new Background(new BackgroundImage(sfondo, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
        grid.setBackground(image);
        pane.setBackground(image);


        center.getChildren().add(grid);
        center.setAlignment(grid, Pos.CENTER);
        pane.setCenter(center);


        StackPane bottom = new StackPane();
        Button showGods = new Button("Show player Gods");
        String style = "-fx-alignment: center; -fx-text-fill: #ffffff;" + "-fx-background-color: #000000;" + "-fx-font-size: 12pt;" + "-fx-font-weight: bold;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";
        showGods.setStyle(style);
        bottom.getChildren().add(showGods);
        bottom.setAlignment(showGods, Pos.CENTER);
        pane.setBottom(bottom);

        showGods.setOnAction(f -> {
            display();
        });

        button00.setOnAction(f -> {
            System.out.println("ciao");
            Event event;
            //event = new askUser();
            //event.send(gui);
        });
        button10.setOnAction(f -> {
            System.out.println("ciao");
            Event event;
            String string = "peppe";
            event = new DeathPlayer(string);
            event.send(gui);
        });
        button20.setOnAction(f -> {
            System.out.println("ciao");
            Event event;
            String string = "salvo";
            event = new EndGame(string);
            event.send(gui);
        });
        button30.setOnAction(f -> {
            System.out.println("ciao");
        });
        button40.setOnAction(f -> {
            System.out.println("ciao");
        });
        button01.setOnAction(f -> {
            System.out.println("ciao");
        });
        button11.setOnAction(f -> {
            System.out.println("ciao");
        });
        button21.setOnAction(f -> {
            System.out.println("ciao");
        });
        button31.setOnAction(f -> {
            System.out.println("ciao");
        });
        button41.setOnAction(f -> {
            System.out.println("ciao");
        });
        button02.setOnAction(f -> {
            System.out.println("ciao");
        });
        button12.setOnAction(f -> {
            System.out.println("ciao");
        });
        button22.setOnAction(f -> {
            System.out.println("ciao");
        });
        button32.setOnAction(f -> {
            System.out.println("ciao");
        });
        button42.setOnAction(f -> {
            System.out.println("ciao");
        });
        button03.setOnAction(f -> {
            System.out.println("ciao");
        });
        button13.setOnAction(f -> {
            System.out.println("ciao");
        });
        button23.setOnAction(f -> {
            System.out.println("ciao");
        });
        button33.setOnAction(f -> {
            System.out.println("ciao");
        });
        button43.setOnAction(f -> {
            System.out.println("ciao");
        });
        button04.setOnAction(f -> {
            System.out.println("ciao");
        });
        button14.setOnAction(f -> {
            System.out.println("ciao");
        });
        button24.setOnAction(f -> {
            System.out.println("ciao");
        });
        button34.setOnAction(f -> {
            System.out.println("ciao");
        });
        button44.setOnAction(f -> {
            System.out.println("ciao");
        });


        int xButton=120;
        int yButton=120;
        int v1=scalingX(xButton);
        int y2=scalingY(yButton);
        button00.setMinSize(v1, y2);
        button10.setMinSize(v1, y2);
        button20.setMinSize(v1, y2);
        button30.setMinSize(v1, y2);
        button40.setMinSize(v1, y2);

        button01.setMinSize(v1, y2);
        button11.setMinSize(v1, y2);
        button21.setMinSize(v1, y2);
        button31.setMinSize(v1, y2);
        button41.setMinSize(v1, y2);

        button02.setMinSize(v1, y2);
        button12.setMinSize(v1, y2);
        button22.setMinSize(v1, y2);
        button32.setMinSize(v1, y2);
        button42.setMinSize(v1, y2);

        button03.setMinSize(v1, y2);
        button13.setMinSize(v1, y2);
        button23.setMinSize(v1, y2);
        button33.setMinSize(v1, y2);
        button43.setMinSize(v1, y2);

        button04.setMinSize(v1, y2);
        button14.setMinSize(v1, y2);
        button24.setMinSize(v1, y2);
        button34.setMinSize(v1, y2);
        button44.setMinSize(v1, y2);

        for (j = 0; j < 5; j++)
            for (i = 0; i < 5; i++) {
                GridPane s= new GridPane();
                ColumnConstraints g1 = new ColumnConstraints();
                ColumnConstraints g2 = new ColumnConstraints();
                g1.setHalignment(HPos.CENTER);
                g2.setHalignment(HPos.CENTER);
                s.getColumnConstraints().addAll(g1,g2);
                s.setMaxSize(200,200);
                s.setMaxSize(200,200);
                s.setStyle("-fx-alignment: center;");
                if (squares[i][j].getColor() != null) {
                    System.out.println(squares[i][j].getColor());
                    // settare il colore giusto del player
                    //Image imageColorWorker = new Image(/*sq.getWorker().getC() + ".jpg" commento "Apollo.jpg");

                    Image imageColorWorker = new Image(checkColor(squares[i][j].getColor().toString()));
                    ImageView imageViewColorWorker = new ImageView(imageColorWorker);
                    imageViewColorWorker.setFitHeight(100.0);
                    imageViewColorWorker.setFitWidth(60.0);
                    s.add(imageViewColorWorker,1,0);

                }
                /*if (squares[i][j].getLevels() == 0) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("0.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(100.0);
                    imageViewLevelSquare.setFitWidth(40.0);
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } */
                if (squares[i][j].getLevels() == 1) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("1.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(50);
                    imageViewLevelSquare.setFitWidth(50);
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 2) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("2.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(80);
                    imageViewLevelSquare.setFitWidth(50);
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 3) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("3.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(100.0);
                    imageViewLevelSquare.setFitWidth(50);
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 4) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("4.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(120);
                    imageViewLevelSquare.setFitWidth(50);
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                }
                grid.add(s, i, j);
            }


        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("GameWindow");
    }


    private int scalingX(int x){
        int a=0;
        double width=gui.getWidthScreen();
        double first=1536.0;
        a= (int) ((int)(x*width)/first);
        return a;
    }

    private int scalingY(int x){
        int a=0;
        double first=824.0;
        double high=gui.getHeightScreen();
        a= (int) ((int)(x*high)/first);
        return a;
    }


    private String checkColor(String c) {
        String path = "";
        if (c.equals("BLACK")) {
            path = "pedinanera.png";
        } else if (c.equals("BROWN")) {
            path = "pedinamarrone.png";
        } else if (c.equals("WHITE")) {
            path = "pedinabianca.png";
        }
        return path;
    }

    private void display() {
        BorderPane pane = new BorderPane();
        pane.setStyle("-fx-alignment: center");
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));

        Text testo = new Text("These are the gods selected");
        testo.setFill(javafx.scene.paint.Color.BLACK);
        testo.setFont(Font.font("Helvetica", FontWeight.BOLD, 18));
        TextFlow title = new TextFlow(testo);
        title.setMaxHeight(60);
        title.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        title.setTextAlignment(TextAlignment.CENTER);
        pane.setTop(title);

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-alignment: center");
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        r1.setValignment(VPos.CENTER);
        r2.setValignment(VPos.CENTER);
        c1.setHalignment(HPos.CENTER);
        c2.setHalignment(HPos.CENTER);
        c3.setHalignment(HPos.CENTER);
        gridPane.getRowConstraints().addAll(r1, r2);
        gridPane.getColumnConstraints().addAll(c1, c2, c3);
        pane.setCenter(gridPane);

        int i = 0;
        int k = 0;

        for (String string : gui.getGods()) {
            Image image = new Image(string + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            Label nick = new Label(gui.getNicknames().get(k));
            nick.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt; -fx-alignment: center;");
            gridPane.add(imageView, i, 0);
            gridPane.add(nick, i, 1);
            i++;
            k++;
        }

        Hyperlink button1 = new Hyperlink();
        button1.setMaxSize(180.0, 240.0);
        button1.setId(gui.getGods().get(0));

        button1.setOnAction(f -> {
            System.out.println(button1.getId());
            showEffect(button1.getId());
        });
        gridPane.add(button1, 0, 0);

        Hyperlink button2 = new Hyperlink();
        button2.setMaxSize(180.0, 240.0);
        button2.setId(gui.getGods().get(1));

        button2.setOnAction(f -> {
            System.out.println(button2.getId());
            showEffect(button2.getId());
        });
        gridPane.add(button2, 1, 0);

        if (gui.getGods().size() == 3) {
            Hyperlink button3 = new Hyperlink();
            button3.setMaxSize(180.0, 240.0);
            button3.setId(gui.getGods().get(2));

            button3.setOnAction(f -> {
                System.out.println(button3.getId());
                showEffect(button3.getId());
            });
            gridPane.add(button3, 2, 0);
        }

        scene = new Scene(pane, 800, 400, Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();
    }



    private void showEffect(String str) {
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText(" this is " + str + "'s effect: ");
        confirmMessage.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt; -fx-alignment: center;");

        GridPane gridPane = new GridPane();
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        r1.setValignment(VPos.CENTER);
        r2.setValignment(VPos.CENTER);
        gridPane.getRowConstraints().addAll(r1, r2);

        gridPane.add(confirmMessage, 0, 0);

        Label effect = new Label();
        effect.setText("ciaoooooo"); // andare a prendere l'effetto
        effect.setTextFill(Color.RED);
        effect.setStyle("-fx-font-weight: bold; -fx-font-size: 12pt; -fx-alignment: center; -fx-text-fill: red;");

        gridPane.add(effect, 0, 1);


        scene = new Scene(gridPane, 800, 400, Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();
    }
}


/*
GridPane arena = new GridPane();
        arena.setAlignment(Pos.CENTER);

        Scene scene=new Scene(arena,gui.getWidthScreen(),gui.getHeightScreen());

        RowConstraints r1=new RowConstraints();
        RowConstraints r2=new RowConstraints();
        RowConstraints r3=new RowConstraints();

        ColumnConstraints c1=new ColumnConstraints();
        ColumnConstraints c2=new ColumnConstraints();
        ColumnConstraints c3=new ColumnConstraints();

        r1.setPercentHeight(5);
        r2.setPercentHeight(80);
        r3.setPercentHeight(15);

        c1.setPercentWidth(10);
        c2.setPercentWidth(80);
        c3.setPercentWidth(10);
        arena.getColumnConstraints().addAll(c1,c2,c3);
        arena.getRowConstraints().addAll(r1,r2,r3);

        GridPane field=new GridPane();
        field.setAlignment(Pos.CENTER);


        RowConstraints r4=new RowConstraints();
        RowConstraints r5=new RowConstraints();
        RowConstraints r6=new RowConstraints();
        RowConstraints r7=new RowConstraints();
        RowConstraints r8=new RowConstraints();

        ColumnConstraints c4=new ColumnConstraints();
        ColumnConstraints c5=new ColumnConstraints();
        ColumnConstraints c6=new ColumnConstraints();
        ColumnConstraints c7=new ColumnConstraints();
        ColumnConstraints c8=new ColumnConstraints();



        r4.setPercentHeight(20);
        r5.setPercentHeight(20);
        r6.setPercentHeight(20);
        r7.setPercentHeight(20);
        r8.setPercentHeight(20);

        c4.setPercentWidth(20);
        c5.setPercentWidth(20);
        c6.setPercentWidth(20);
        c7.setPercentWidth(20);
        c8.setPercentWidth(20);

        field.getColumnConstraints().addAll(c4,c5,c6,c7,c8);
        field.getRowConstraints().addAll(r4,r5,r6,r7,r8);
        //field.setMinSize(700.0, 700.0);
        //field.setMaxSize(700.0, 700.0);

        arena.add(field,1,1);

        GridPane board=new GridPane();


        RowConstraints r9=new RowConstraints();
        RowConstraints r10=new RowConstraints();

        ColumnConstraints c9=new ColumnConstraints();
        ColumnConstraints c10=new ColumnConstraints();
        ColumnConstraints c11=new ColumnConstraints();

        r9.setPercentHeight(20);
        r10.setPercentHeight(80);

        c9.setPercentWidth(33);
        c10.setPercentWidth(34);
        c11.setPercentWidth(33);

        board.getColumnConstraints().addAll(c9,c10,c11);
        board.getRowConstraints().addAll(r9,r10);

        //setting field
        int i;
        int j;
        SquareToJson[][] squares = event.getSquares();

        buttons = new Hyperlink[5][5];
        stacks = new StackPane[5][5];
        gridButtons = new GridPane[5][5];


        Hyperlink button00 = new Hyperlink();
        button00.setVisible(false);
        buttons[0][0] = button00;
        Hyperlink button10 = new Hyperlink();
        button10.setVisible(false);
        buttons[1][0] = button10;
        Hyperlink button20 = new Hyperlink();
        button20.setVisible(false);
        buttons[2][0] = button20;
        Hyperlink button30 = new Hyperlink();
        button30.setVisible(false);
        buttons[3][0] = button30;
        Hyperlink button40 = new Hyperlink();
        button40.setVisible(false);
        buttons[4][0] = button40;
        Hyperlink button01 = new Hyperlink();
        button01.setVisible(false);
        buttons[0][1] = button01;
        Hyperlink button11 = new Hyperlink();
        button11.setVisible(false);
        buttons[1][1] = button11;
        Hyperlink button21 = new Hyperlink();
        button21.setVisible(false);
        buttons[2][1] = button21;
        Hyperlink button31 = new Hyperlink();
        button31.setVisible(false);
        buttons[3][1] = button31;
        Hyperlink button41 = new Hyperlink();
        button41.setVisible(false);
        buttons[4][1] = button41;
        Hyperlink button02 = new Hyperlink();
        button02.setVisible(false);
        buttons[0][2] = button02;
        Hyperlink button12 = new Hyperlink();
        button12.setVisible(false);
        buttons[1][2] = button12;
        Hyperlink button22 = new Hyperlink();
        button22.setVisible(false);
        buttons[2][2] = button22;
        Hyperlink button32 = new Hyperlink();
        button32.setVisible(false);
        buttons[3][2] = button32;
        Hyperlink button42 = new Hyperlink();
        button42.setVisible(false);
        buttons[4][2] = button42;
        Hyperlink button03 = new Hyperlink();
        button03.setVisible(false);
        buttons[0][3] = button03;
        Hyperlink button13 = new Hyperlink();
        button13.setVisible(false);
        buttons[1][3] = button13;
        Hyperlink button23 = new Hyperlink();
        button23.setVisible(false);
        buttons[2][3] = button23;
        Hyperlink button33 = new Hyperlink();
        button33.setVisible(false);
        buttons[3][3] = button33;
        Hyperlink button43 = new Hyperlink();
        button43.setVisible(false);
        buttons[4][3] = button43;
        Hyperlink button04 = new Hyperlink();
        button04.setVisible(false);
        buttons[0][4] = button04;
        Hyperlink button14 = new Hyperlink();
        button14.setVisible(false);
        buttons[1][4] = button14;
        Hyperlink button24 = new Hyperlink();
        button24.setVisible(false);
        buttons[2][4] = button24;
        Hyperlink button34 = new Hyperlink();
        button34.setVisible(false);
        buttons[3][4] = button34;
        Hyperlink button44 = new Hyperlink();
        button44.setVisible(false);
        buttons[4][4] = button44;

        button00.setMaxSize(217.0,115.0);
        button00.setMinSize(217.0,115.0);
        button10.setMaxSize(217.0,115.0);
        button10.setMinSize(217.0,115.0);
        button20.setMaxSize(217.0,115.0);
        button20.setMinSize(217.0,115.0);
        button30.setMaxSize(217.0,115.0);
        button30.setMinSize(217.0,115.0);
        button40.setMaxSize(217.0,115.0);
        button40.setMinSize(217.0,115.0);
        button01.setMaxSize(217.0,115.0);
        button01.setMinSize(217.0,115.0);
        button11.setMaxSize(217.0,115.0);
        button11.setMinSize(217.0,115.0);
        button21.setMaxSize(217.0,115.0);
        button21.setMinSize(217.0,115.0);
        button31.setMaxSize(217.0,115.0);
        button31.setMinSize(217.0,115.0);
        button41.setMaxSize(217.0,115.0);
        button41.setMinSize(217.0,115.0);
        button02.setMaxSize(217.0,115.0);
        button02.setMinSize(217.0,115.0);
        button12.setMaxSize(217.0,115.0);
        button12.setMinSize(217.0,115.0);
        button22.setMaxSize(217.0,115.0);
        button22.setMinSize(217.0,115.0);
        button32.setMaxSize(217.0,115.0);
        button32.setMinSize(217.0,115.0);
        button42.setMaxSize(217.0,115.0);
        button42.setMinSize(217.0,115.0);
        button03.setMaxSize(217.0,115.0);
        button03.setMinSize(217.0,115.0);
        button13.setMaxSize(217.0,115.0);
        button13.setMinSize(217.0,115.0);
        button23.setMaxSize(217.0,115.0);
        button23.setMinSize(217.0,115.0);
        button33.setMaxSize(217.0,115.0);
        button33.setMinSize(217.0,115.0);
        button43.setMaxSize(217.0,115.0);
        button43.setMinSize(217.0,115.0);
        button04.setMaxSize(217.0,115.0);
        button04.setMinSize(217.0,115.0);
        button14.setMaxSize(217.0,115.0);
        button14.setMinSize(217.0,115.0);
        button24.setMaxSize(217.0,115.0);
        button24.setMinSize(217.0,115.0);
        button34.setMaxSize(217.0,115.0);
        button34.setMinSize(217.0,115.0);
        button44.setMaxSize(217.0,115.0);


        button44.setMinSize(217.0,115.0);
        button00.setOnAction(f ->{
            System.out.println("ciao");
            Event event;
            //event = new askUser();
            //event.send(gui);
        });
        button10.setOnAction(f ->{
            System.out.println("ciao");
            Event event;
            String string = "peppe";
            event = new DeathPlayer(string);
            event.send(gui);
        });
        button20.setOnAction(f ->{
            System.out.println("ciao");
            Event event;
            String string = "salvo";
            event = new EndGame(string);
            event.send(gui);
        });
        button30.setOnAction(f ->{
            System.out.println("ciao");
        });
        button40.setOnAction(f ->{
            System.out.println("ciao");
        });
        button01.setOnAction(f ->{
            System.out.println("ciao");
        });
        button11.setOnAction(f ->{
            System.out.println("ciao");
        });
        button21.setOnAction(f ->{
            System.out.println("ciao");
        });
        button31.setOnAction(f ->{
            System.out.println("ciao");
        });
        button41.setOnAction(f ->{
            System.out.println("ciao");
        });
        button02.setOnAction(f ->{
            System.out.println("ciao");
        });
        button12.setOnAction(f ->{
            System.out.println("ciao");
        });
        button22.setOnAction(f ->{
            System.out.println("ciao");
        });
        button32.setOnAction(f ->{
            System.out.println("ciao");
        });
        button42.setOnAction(f ->{
            System.out.println("ciao");
        });
        button03.setOnAction(f ->{
            System.out.println("ciao");
        });
        button13.setOnAction(f ->{
            System.out.println("ciao");
        });
        button23.setOnAction(f ->{
            System.out.println("ciao");
        });
        button33.setOnAction(f ->{
            System.out.println("ciao");
        });
        button43.setOnAction(f ->{
            System.out.println("ciao");
        });
        button04.setOnAction(f ->{
            System.out.println("ciao");
        });
        button14.setOnAction(f ->{
            System.out.println("ciao");
        });
        button24.setOnAction(f ->{
            System.out.println("ciao");
        });
        button34.setOnAction(f ->{
            System.out.println("ciao");
        });
        button44.setOnAction(f ->{
            System.out.println("ciao");
        });


        ColumnConstraints c13= new ColumnConstraints();
        ColumnConstraints c12 = new ColumnConstraints();

        c13.setPercentWidth(50);
        c12.setPercentWidth(50);

        GridPane gridButton00 = new GridPane();
        gridButton00.getColumnConstraints().addAll(c13,c12);
        gridButtons[0][0] = gridButton00;
        GridPane gridButton10 = new GridPane();
        gridButton10.getColumnConstraints().addAll(c13,c12);
        gridButtons[1][0] = gridButton10;
        GridPane gridButton20 = new GridPane();
        gridButton20.getColumnConstraints().addAll(c13,c12);
        gridButtons[2][0] = gridButton20;
        GridPane gridButton30 = new GridPane();
        gridButton30.getColumnConstraints().addAll(c13,c12);
        gridButtons[3][0] = gridButton30;
        GridPane gridButton40 = new GridPane();
        gridButton40.getColumnConstraints().addAll(c13,c12);
        gridButtons[4][0] = gridButton40;
        GridPane gridButton01 = new GridPane();
        gridButton01.getColumnConstraints().addAll(c13,c12);
        gridButtons[0][1] = gridButton01;
        GridPane gridButton11 = new GridPane();
        gridButton11.getColumnConstraints().addAll(c13,c12);
        gridButtons[1][1] = gridButton11;
        GridPane gridButton21 = new GridPane();
        gridButton21.getColumnConstraints().addAll(c13,c12);
        gridButtons[2][1] = gridButton21;
        GridPane gridButton31 = new GridPane();
        gridButton31.getColumnConstraints().addAll(c13,c12);
        gridButtons[3][1] = gridButton31;
        GridPane gridButton41 = new GridPane();
        gridButton41.getColumnConstraints().addAll(c13,c12);
        gridButtons[4][1] = gridButton41;
        GridPane gridButton02 = new GridPane();
        gridButton02.getColumnConstraints().addAll(c13,c12);
        gridButtons[0][2] = gridButton02;
        GridPane gridButton12 = new GridPane();
        gridButton12.getColumnConstraints().addAll(c13,c12);
        gridButtons[1][2] = gridButton12;
        GridPane gridButton22 = new GridPane();
        gridButton22.getColumnConstraints().addAll(c13,c12);
        gridButtons[2][2] = gridButton22;
        GridPane gridButton32 = new GridPane();
        gridButton32.getColumnConstraints().addAll(c13,c12);
        gridButtons[3][2] = gridButton32;
        GridPane gridButton42 = new GridPane();
        gridButton42.getColumnConstraints().addAll(c13,c12);
        gridButtons[4][2] = gridButton42;
        GridPane gridButton03 = new GridPane();
        gridButton03.getColumnConstraints().addAll(c13,c12);
        gridButtons[0][3] = gridButton03;
        GridPane gridButton13 = new GridPane();
        gridButton13.getColumnConstraints().addAll(c13,c12);
        gridButtons[1][3] = gridButton13;
        GridPane gridButton23 = new GridPane();
        gridButton23.getColumnConstraints().addAll(c13,c12);
        gridButtons[2][3] = gridButton23;
        GridPane gridButton33 = new GridPane();
        gridButton33.getColumnConstraints().addAll(c13,c12);
        gridButtons[3][3] = gridButton33;
        GridPane gridButton43 = new GridPane();
        gridButton43.getColumnConstraints().addAll(c13,c12);
        gridButtons[4][3] = gridButton43;
        GridPane gridButton04 = new GridPane();
        gridButton04.getColumnConstraints().addAll(c13,c12);
        gridButtons[0][4] = gridButton04;
        GridPane gridButton14 = new GridPane();
        gridButton14.getColumnConstraints().addAll(c13,c12);
        gridButtons[1][4] = gridButton14;
        GridPane gridButton24 = new GridPane();
        gridButton24.getColumnConstraints().addAll(c13,c12);
        gridButtons[2][4] = gridButton24;
        GridPane gridButton34 = new GridPane();
        gridButton34.getColumnConstraints().addAll(c13,c12);
        gridButtons[3][4] = gridButton34;
        GridPane gridButton44 = new GridPane();
        gridButton44.getColumnConstraints().addAll(c13,c12);
        gridButtons[4][4] = gridButton44;


        StackPane stack00 = new StackPane();
        stacks[0][0] = stack00;
        StackPane stack10 = new StackPane();
        stacks[1][0] = stack10;
        StackPane stack20 = new StackPane();
        stacks[2][0] = stack20;
        StackPane stack30 = new StackPane();
        stacks[3][0] = stack30;
        StackPane stack40 = new StackPane();
        stacks[4][0] = stack40;
        StackPane stack01 = new StackPane();
        stacks[0][1] = stack01;
        StackPane stack11 = new StackPane();
        stacks[1][1] = stack11;
        StackPane stack21 = new StackPane();
        stacks[2][1] = stack21;
        StackPane stack31 = new StackPane();
        stacks[3][1] = stack31;
        StackPane stack41 = new StackPane();
        stacks[4][1] = stack41;
        StackPane stack02 = new StackPane();
        stacks[0][2] = stack02;
        StackPane stack12 = new StackPane();
        stacks[1][2] = stack12;
        StackPane stack22 = new StackPane();
        stacks[2][2] = stack22;
        StackPane stack32 = new StackPane();
        stacks[3][2] = stack32;
        StackPane stack42 = new StackPane();
        stacks[4][2] = stack42;
        StackPane stack03 = new StackPane();
        stacks[0][3] = stack03;
        StackPane stack13 = new StackPane();
        stacks[1][3] = stack13;
        StackPane stack23 = new StackPane();
        stacks[2][3] = stack23;
        StackPane stack33 = new StackPane();
        stacks[3][3] = stack33;
        StackPane stack43 = new StackPane();
        stacks[4][3] = stack43;
        StackPane stack04 = new StackPane();
        stacks[0][4] = stack04;
        StackPane stack14 = new StackPane();
        stacks[1][4] = stack14;
        StackPane stack24 = new StackPane();
        stacks[2][4] = stack24;
        StackPane stack34 = new StackPane();
        stacks[3][4] = stack34;
        StackPane stack44 = new StackPane();
        stacks[4][4] = stack44;






        for ( j = 0; j < 5; j++)
            for ( i = 0; i < 5; i++){
                if (squares[i][j].getColor() != null) {
                    System.out.println(squares[i][j].getColor());
                    // settare il colore giusto del player
                    //Image imageColorWorker = new Image(/*sq.getWorker().getC() + ".jpg" commento "Apollo.jpg");

        Image imageColorWorker = new Image(checkColor(squares[i][j].getColor().toString()));
        ImageView imageViewColorWorker = new ImageView(imageColorWorker);
        imageViewColorWorker.setFitHeight(100.0);
        imageViewColorWorker.setFitWidth(60.0);
        gridButtons[i][j].add(imageViewColorWorker,1,0);

        }
        if (squares[i][j].getLevels() == 0) {
        // aggiunge immagine livello ad ogni cella
        Image imageLevelSquare = new Image("0.png");
        ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
        imageViewLevelSquare.setFitHeight(100.0);
        imageViewLevelSquare.setFitWidth(40.0);
        gridButtons[i][j].add(imageViewLevelSquare,0,0);
        }
        else if (squares[i][j].getLevels() == 1) {
        // aggiunge immagine livello ad ogni cella
        Image imageLevelSquare = new Image("1.png");
        ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
        imageViewLevelSquare.setFitHeight(40.0);
        imageViewLevelSquare.setFitWidth(20.0);
        gridButtons[i][j].add(imageViewLevelSquare,0,0);
        }
        else if (squares[i][j].getLevels() == 2) {
        // aggiunge immagine livello ad ogni cella
        Image imageLevelSquare = new Image("2.jpg");
        ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
        imageViewLevelSquare.setFitHeight(40.0);
        imageViewLevelSquare.setFitWidth(20.0);
        gridButtons[i][j].add(imageViewLevelSquare,0,0);
        }
        else if (squares[i][j].getLevels() == 3) {
        // aggiunge immagine livello ad ogni cella
        Image imageLevelSquare = new Image("3.png");
        ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
        imageViewLevelSquare.setFitHeight(40.0);
        imageViewLevelSquare.setFitWidth(20.0);
        gridButtons[i][j].add(imageViewLevelSquare,0,0);
        }
        else if (squares[i][j].getLevels() == 4) {
        // aggiunge immagine livello ad ogni cella
        Image imageLevelSquare = new Image("4.png");
        ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
        imageViewLevelSquare.setFitHeight(40.0);
        imageViewLevelSquare.setFitWidth(20.0);
        gridButtons[i][j].add(imageViewLevelSquare,0,0);
        }

        field.add(gridButtons[i][j], i, j, 1, 1);
        }

        arena.add(board,2,1);

        arena.setGridLinesVisible(true);
        field.setGridLinesVisible(true);
        board.setGridLinesVisible(true);

        Button showGods = new Button("show player gods");
        arena.add(showGods, 2,0);
        showGods.setOnAction(f -> {
        display();
        });



        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("GameWindow");
 */

/*
     Label confirmMessage = new Label();
        confirmMessage.setText("These are the gods selected");

        GridPane gridPane = new GridPane();
        RowConstraints r1 = new RowConstraints();
        RowConstraints r2 = new RowConstraints();
        RowConstraints r3 = new RowConstraints();
        RowConstraints r4 = new RowConstraints();

        ColumnConstraints c1 = new ColumnConstraints();
        ColumnConstraints c2 = new ColumnConstraints();
        ColumnConstraints c3 = new ColumnConstraints();
        ColumnConstraints c4 = new ColumnConstraints();
        ColumnConstraints c5 = new ColumnConstraints();
        ColumnConstraints c6 = new ColumnConstraints();
        ColumnConstraints c7 = new ColumnConstraints();


        r1.setPercentHeight(20);
        r2.setPercentHeight(60);
        r3.setPercentHeight(10);
        r4.setPercentHeight(10);

        c1.setPercentWidth(7.5);
        c2.setPercentWidth(25);
        c3.setPercentWidth(5);
        c4.setPercentWidth(25);
        c5.setPercentWidth(5);
        c6.setPercentWidth(25);
        c7.setPercentWidth(7.5);

        gridPane.getRowConstraints().addAll(r1, r2, r4);
        gridPane.getColumnConstraints().addAll(c1, c2, c3, c4, c5, c6, c7);
        gridPane.add(confirmMessage, 3, 0);


        int i = 1;
        int j = 1;
        int k = 0;

        for (String string : gui.getGods()) {
            Image image = new Image(string + ".jpg");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(240.0);
            imageView.setFitWidth(180.0);
            Label nick = new Label(gui.getNicknames().get(k));
            gridPane.add(imageView, i, j, 1, 1);
            gridPane.add(nick, i, 2, 1, 1);
            i = i + 2;
            k++;

        }

        Hyperlink button1 = new Hyperlink();
        button1.setMaxSize(180.0, 240.0);
        button1.setId(gui.getGods().get(0));

        button1.setOnAction(f -> {
            System.out.println(button1.getId());
            showEffect(button1.getId());
        });
        gridPane.add(button1, 1, 1);

        Hyperlink button2 = new Hyperlink();
        button2.setMaxSize(180.0, 240.0);
        button2.setId(gui.getGods().get(1));

        button2.setOnAction(f -> {
            System.out.println("sonnnnnnnn");
            System.out.println(button2.getId());
            showEffect(button2.getId());
        });
        gridPane.add(button2, 3, 1);

        if (gui.getGods().size() == 3) {

            Hyperlink button3 = new Hyperlink();
            button3.setMaxSize(180.0, 240.0);
            button3.setId(gui.getGods().get(2));

            button3.setOnAction(f -> {
                System.out.println(button3.getId());
                showEffect(button3.getId());
            });
            gridPane.add(button3, 5, 1);
        }

        Label effect = new Label();
        effect.setTextFill(Color.RED);

        gridPane.add(effect, 1, 1);
     */