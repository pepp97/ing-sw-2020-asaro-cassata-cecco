package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.events.*;
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
/**
 * this window is the most important because it is the game map
 */
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
        GridPane bPane= new GridPane();

        ColumnConstraints cb1 = new ColumnConstraints();
        ColumnConstraints cb2 = new ColumnConstraints();
        cb1.setHalignment(HPos.CENTER);
        cb2.setHalignment(HPos.CENTER);
        bPane.getColumnConstraints().addAll(cb1,cb2);
        bPane.setStyle("-fx-alignment: center;");

        Button showGods = new Button("Show player Gods");
        String style = "-fx-alignment: center; -fx-text-fill: #ffffff;" + "-fx-background-color: #000000;" + "-fx-font-size: 12pt;" + "-fx-font-weight: bold;" + "-fx-border-radius: 20;" + "-fx-background-radius: 20;";
        showGods.setStyle(style);
        bPane.add(showGods,0,0);


        bottom.getChildren().add(bPane);
        bottom.setAlignment(bPane,Pos.CENTER);


        pane.setBottom(bottom);
        pane.getBottom().setLayoutY(scalingY(80));
        pane.getBottom().setLayoutX(scalingY(300));

        showGods.setOnAction(f -> {
            display();
        });


        button00.setOnAction(f -> {
        });
        button10.setOnAction(f -> {

        });
        button20.setOnAction(f -> {

        });
        button30.setOnAction(f -> {

        });
        button40.setOnAction(f -> {

        });
        button01.setOnAction(f -> {

        });
        button11.setOnAction(f -> {

        });
        button21.setOnAction(f -> {

        });
        button31.setOnAction(f -> {

        });
        button41.setOnAction(f -> {

        });
        button02.setOnAction(f -> {

        });
        button12.setOnAction(f -> {

        });
        button22.setOnAction(f -> {

        });
        button32.setOnAction(f -> {

        });
        button42.setOnAction(f -> {

        });
        button03.setOnAction(f -> {

        });
        button13.setOnAction(f -> {

        });
        button23.setOnAction(f -> {

        });
        button33.setOnAction(f -> {

        });
        button43.setOnAction(f -> {

        });
        button04.setOnAction(f -> {

        });
        button14.setOnAction(f -> {

        });
        button24.setOnAction(f -> {

        });
        button34.setOnAction(f -> {

        });
        button44.setOnAction(f -> {

        });


        int xButton=210;
        int yButton=210;
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
                s.setMinSize(scalingX(200),scalingY(200));
                s.setMaxSize(scalingX(200),scalingY(200));
                s.setStyle("-fx-alignment: center;");
                if (squares[i][j].getColor() != null) {
                    System.out.println(squares[i][j].getColor());
                    Image imageColorWorker = new Image(checkColor(squares[i][j].getColor().toString()));
                    ImageView imageViewColorWorker = new ImageView(imageColorWorker);
                    imageViewColorWorker.setFitHeight(scalingY(100));
                    imageViewColorWorker.setFitWidth(scalingX(60));
                    s.add(imageViewColorWorker,1,0);

                }
                if (squares[i][j].getLevels() == 1) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("1.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(scalingY(50));
                    imageViewLevelSquare.setFitWidth(scalingX(50));
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 2) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("2.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(scalingY(80));
                    imageViewLevelSquare.setFitWidth(scalingX(50));
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 3) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("3.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(scalingY(100));
                    imageViewLevelSquare.setFitWidth(scalingX(50));
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                } else if (squares[i][j].getLevels() == 4) {
                    // aggiunge immagine livello ad ogni cella
                    Image imageLevelSquare = new Image("4.png");
                    ImageView imageViewLevelSquare = new ImageView(imageLevelSquare);
                    imageViewLevelSquare.setFitHeight(scalingY(120));
                    imageViewLevelSquare.setFitWidth(scalingX(50));
                    s.add(imageViewLevelSquare,0,0);
                    //grid.add(imageViewLevelSquare, i, j);
                }
                grid.add(s, i, j);
            }


        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("GameWindow");
    }

    /**
     * this method is used to calculate the right proportions of the window's elements
     * @param x width
     * @return right width
     */
    private int scalingX(int x){
        int a=0;
        double width=gui.getWidthScreen();
        double first=2560;
        a= (int) ((int)(x*width)/first);
        return a;
    }

    /**
     * this method is used to calculate the right proportions of the window's elements
     * @param y high
     * @return right high
     */
    private int scalingY(int y){
        int a=0;
        double first=1400;
        double high=gui.getHeightScreen();
        a= (int) ((int)(y*high)/first);
        return a;
    }

    /**
     * this method returns the image of the pawn corresponding to the color sought
     * @param c name of the color
     * @return  path of the image
     */
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

    /**
     * this pop-up displays the gods in the game
     */
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

        gridPane.add(button1, 0, 0);

        Hyperlink button2 = new Hyperlink();
        button2.setMaxSize(180.0, 240.0);
        button2.setId(gui.getGods().get(1));

        gridPane.add(button2, 1, 0);

        if (gui.getGods().size() == 3) {
            Hyperlink button3 = new Hyperlink();
            button3.setMaxSize(180.0, 240.0);
            button3.setId(gui.getGods().get(2));

            gridPane.add(button3, 2, 0);
        }

        scene = new Scene(pane, 800, 400, Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();
    }

}