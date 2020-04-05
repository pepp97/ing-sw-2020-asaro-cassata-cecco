package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.commands.LoginCommand;
import it.polimi.ingsw.events.Event;
import it.polimi.ingsw.events.LoginSuccessful;
import it.polimi.ingsw.events.SettingsEvent;
import it.polimi.ingsw.model.Color;
import it.polimi.ingsw.view.Gui;
import it.polimi.ingsw.view.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class LoginWindow implements GuiScreen {
    private Gui gui;

    public LoginWindow(Gui gui) {
        this.gui=gui;
    }

    @Override
    public void setScene() {
        ArrayList<RadioButton> Bottoni=new ArrayList<>();

        GridPane form = new GridPane();
        form.setAlignment(Pos.CENTER);

        Scene scene=new Scene(form,gui.getWidthScreen(),gui.getHeightScreen());
        Image intro = new Image("rickFatto.jpg", gui.getWidthScreen(),gui.getHeightScreen(),true, true); //modificare percorso.

        //Image intro = new Image("SantoriniIntro.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(intro);
        BackgroundImage backgroundImage = new BackgroundImage(intro, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        form.setBackground(new Background(backgroundImage));
        TextField nameInput = new TextField("");
        Label nameLabel=new Label("scegliere il nickname: ");
        nameLabel.setTextFill(javafx.scene.paint.Color.RED);
        nameLabel.setFont(new Font(25.00));
        nameLabel.setLabelFor(nameInput);
        form.addRow(1,nameLabel,nameInput);

        Button connect = new Button("Connect");
        //   WaitRoom room=new WaitRoom(stage);
        ToggleGroup group = new ToggleGroup();
        RadioButton black= new RadioButton("       ");
        black.setToggleGroup(group);
        RadioButton brown= new RadioButton("       ");
        brown.setToggleGroup(group);
        RadioButton white= new RadioButton("       ");
        white.setToggleGroup(group);


        Bottoni.add(black);

        Bottoni.add(white);

        Bottoni.add(brown);
        black.setId("black");
        white.setId("white");
        brown.setId("brown");

        form.add(black,6,10);
        form.add(brown,2,10);
        form.add(white,3,10);
        black.setStyle("-fx-background-color: White");
        white.setStyle("-fx-background-color: Black");
        brown.setStyle("-fx-background-color: Brown");

        connect.setOnAction(f -> {
            String color = null;
            if(!nameInput.getText().equals("")) {
                for (RadioButton button : Bottoni)
                    if (button.isSelected()) {
                        color = button.getId();

                        try {
                            LoginCommand command= new LoginCommand(nameInput.getText(), Color.valueOf(color.toUpperCase()));
                            gui.getClient().send(command);
                            Event event;
                            event= new SettingsEvent();
                            event.send(gui);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                if (color == null) {
                    Label tryAgain = new Label("Seleziona un colore!");
                    form.add(tryAgain, 1, 0);
                }
            }
        });

        form.addRow(5,connect);
        gui.getPrimaryStage().setScene(scene);
        gui.getPrimaryStage().setTitle("Login Window");


        //   return scene;
    }
}
