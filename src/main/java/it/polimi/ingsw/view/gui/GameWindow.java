package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.events.UpdateEvent;
import it.polimi.ingsw.view.Gui;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

public class GameWindow implements GuiScreen {
    private Gui gui;
    private UpdateEvent event;

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

        arena.add(board,2,1);

        arena.setGridLinesVisible(true);
        field.setGridLinesVisible(true);
        board.setGridLinesVisible(true);

        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("Let's Play!");


    }
}
