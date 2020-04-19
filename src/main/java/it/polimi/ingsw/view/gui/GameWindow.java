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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class GameWindow implements GuiScreen {
    private Gui gui;
    private UpdateEvent event;
    private Hyperlink[][] buttons;
    private StackPane [][] stacks;

    public GameWindow(Gui gui, UpdateEvent event) {
        this.gui=gui;
        this.event=event;
    }

    @Override
    public void setScene() {

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


        Hyperlink button00 = new Hyperlink();
        buttons[0][0] = button00;
        Hyperlink button10 = new Hyperlink();
        buttons[1][0] = button10;
        Hyperlink button20 = new Hyperlink();
        buttons[2][0] = button20;
        Hyperlink button30 = new Hyperlink();
        buttons[3][0] = button30;
        Hyperlink button40 = new Hyperlink();
        buttons[4][0] = button40;
        Hyperlink button01 = new Hyperlink();
        buttons[0][1] = button01;
        Hyperlink button11 = new Hyperlink();
        buttons[1][1] = button11;
        Hyperlink button21 = new Hyperlink();
        buttons[2][1] = button21;
        Hyperlink button31 = new Hyperlink();
        buttons[3][1] = button31;
        Hyperlink button41 = new Hyperlink();
        buttons[4][1] = button41;
        Hyperlink button02 = new Hyperlink();
        buttons[0][2] = button02;
        Hyperlink button12 = new Hyperlink();
        buttons[1][2] = button12;
        Hyperlink button22 = new Hyperlink();
        buttons[2][2] = button22;
        Hyperlink button32 = new Hyperlink();
        buttons[3][2] = button32;
        Hyperlink button42 = new Hyperlink();
        buttons[4][2] = button42;
        Hyperlink button03 = new Hyperlink();
        buttons[0][3] = button03;
        Hyperlink button13 = new Hyperlink();
        buttons[1][3] = button13;
        Hyperlink button23 = new Hyperlink();
        buttons[2][3] = button23;
        Hyperlink button33 = new Hyperlink();
        buttons[3][3] = button33;
        Hyperlink button43 = new Hyperlink();
        buttons[4][3] = button43;
        Hyperlink button04 = new Hyperlink();
        buttons[0][4] = button04;
        Hyperlink button14 = new Hyperlink();
        buttons[1][4] = button14;
        Hyperlink button24 = new Hyperlink();
        buttons[2][4] = button24;
        Hyperlink button34 = new Hyperlink();
        buttons[3][4] = button34;
        Hyperlink button44 = new Hyperlink();
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
                    Image imageColorWorker = new Image(/*sq.getWorker().getC() + ".jpg"*/"Apollo.jpg");
                    ImageView imageViewColorWorker = new ImageView(imageColorWorker);
                    imageViewColorWorker.setFitHeight(40.0);
                    imageViewColorWorker.setFitWidth(20.0);
                    stacks[i][j].getChildren().add(imageViewColorWorker);

                }
                stacks[i][j].getChildren().add(buttons[i][j]);
                field.add(stacks[i][j], i, j, 1, 1);
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
        gui.getPrimaryStage().setTitle("Let's Play!");


    }

    private void display() {
        Stage stage;
        stage = new Stage(StageStyle.UTILITY);
        Scene scene;
        Background confirmBackground = new Background(new BackgroundFill(Color.web("#bbb"), CornerRadii.EMPTY, Insets.EMPTY));
        Label confirmMessage = new Label();
        confirmMessage.setText("these are the gods selected");

        GridPane gridPane=new GridPane();
        RowConstraints r1=new RowConstraints();
        RowConstraints r2=new RowConstraints();
        RowConstraints r3=new RowConstraints();
        RowConstraints r4=new RowConstraints();

        ColumnConstraints c1=new ColumnConstraints();
        ColumnConstraints c2=new ColumnConstraints();
        ColumnConstraints c3=new ColumnConstraints();
        ColumnConstraints c4=new ColumnConstraints();
        ColumnConstraints c5=new ColumnConstraints();
        ColumnConstraints c6=new ColumnConstraints();
        ColumnConstraints c7=new ColumnConstraints();


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
        gridPane.add(confirmMessage, 3,0);


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
            gridPane.add(nick, i,2,1,1);
            i = i + 2;
            k++;

        }

        Hyperlink button1=new Hyperlink();
        button1.setMaxSize(180.0,240.0);
        button1.setId(gui.getGods().get(0));

        button1.setOnAction(f->{
            System.out.println(button1.getId());
            showEffect(button1.getId());
        });
        gridPane.add(button1,1,1);

        Hyperlink button2=new Hyperlink();
        button2.setMaxSize(180.0,240.0);
        button2.setId(gui.getGods().get(1));

        button2.setOnAction(f->{
            System.out.println("sonnnnnnnn");
            System.out.println(button2.getId());
            showEffect(button2.getId());
        });
        gridPane.add(button2,3,1);

        if (gui.getGods().get(2) != null ) {

            Hyperlink button3 = new Hyperlink();
            button3.setMaxSize(180.0, 240.0);
            button3.setId(gui.getGods().get(2));

            button3.setOnAction(f -> {
                System.out.println(button3.getId());
                showEffect(button3.getId());
            });
            gridPane.add(button3, 5, 1);
        }

        Label effect=new Label ();
        effect.setTextFill(Color.RED);

        gridPane.add(effect,1,1);




        scene=new Scene(gridPane,800,400,Color.BLACK);
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
        confirmMessage.setText("Do you want to use this god?" +
                "\n \n                 this is " + str + "'s effect: ");

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
        effect.setText("ciaoooooo"); // andare a prendere l'effetto
        effect.setTextFill(Color.RED);

        gridPane.add(effect,1,1);



        scene=new Scene(gridPane,800,400,Color.BLACK);
        scene.setFill(Color.BROWN);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(gui.getPrimaryStage());
        stage.setScene(scene);
        stage.showAndWait();
    }
}