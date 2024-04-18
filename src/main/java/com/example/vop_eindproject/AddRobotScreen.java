package com.example.vop_eindproject;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddRobotScreen {
    public static Stage makeAddRobotStage(ObservableList<Robot> list) {
        Stage addRobotStage = new Stage();
        GridPane addRobotContainer = new GridPane();
        addRobotContainer.setVgap(5);
        addRobotContainer.setHgap(5);
        addRobotContainer.setPadding(new Insets(10));

        //***Naam***
        Label naamLabel = new Label("Naam: ");
        addRobotContainer.add(naamLabel, 0, 0);
        TextField naamVeld = new TextField();
        addRobotContainer.add(naamVeld, 1 , 0);
        //***Telefoonnummer***
        Label telefoonnummerLabel = new Label("Telefoonnummer: ");
        addRobotContainer.add(telefoonnummerLabel, 0, 1);
        TextField telefoonnummerVeld = new TextField();
        addRobotContainer.add(telefoonnummerVeld, 1, 1);
        //***E-mail***
        Label emailLabel = new Label("E-mail: ");
        addRobotContainer.add(emailLabel, 0, 2);
        TextField emailVeld = new TextField();
        addRobotContainer.add(emailVeld, 1, 2);
        //***Adres***
        Label adresLabel = new Label("Adres: ");
        addRobotContainer.add(adresLabel, 0, 3);
        TextField adresVeld = new TextField();
        addRobotContainer.add(adresVeld, 1, 3);
        //***toevoegenKnop***
        Button toevoegenKnop = new Button("Toevoegen");
        addRobotContainer.add(toevoegenKnop, 0, 5);
        toevoegenKnop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lijst.add(new Vereniging(naamVeld.getText(),
                        telefoonnummerVeld.getText(),
                        emailVeld.getText(),
                        adresVeld.getText()));
                addRobotStage.close();
            }
        });
        addRobotStage.setTitle("Vereniging toevoegen");
        addRobotStage.setScene(
                new Scene(addRobotContainer, 800, 400));
        return(addRobotStage);
    }
}
