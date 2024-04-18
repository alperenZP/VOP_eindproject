package com.example.vop_eindproject;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class EditRobotScreen {
    public static Stage makeEditRobotStage(Robot robot, ObservableList<Robot> robots, TableView<Robot> tableView) {
        Stage editRobotStage = new Stage();
        GridPane addRobotContainer = new GridPane();
        addRobotContainer.setVgap(5);
        addRobotContainer.setHgap(5);
        addRobotContainer.setPadding(new Insets(10));

        //***Naam***
        Label naamLabel = new Label("Naam: ");
        addRobotContainer.add(naamLabel, 0, 1);
        TextField naamVeld = new TextField();
        addRobotContainer.add(naamVeld, 1, 1);

        naamVeld.setText(robot.getNaam());
        //***Kleur***
        Color defaultColor = robot.geefKleur(); // Set your default color here
        Label kleurLabel = new Label("Kleur: ");
        addRobotContainer.add(kleurLabel, 0, 2);
        ColorPicker kleurPicker = new ColorPicker(defaultColor);
        addRobotContainer.add(kleurPicker, 1, 2);

        //***Processor Naam***
        Label processorNaamLabel = new Label("Processor Naam: ");
        addRobotContainer.add(processorNaamLabel, 0, 3);
        TextField processorNaamVeld = new TextField();
        addRobotContainer.add(processorNaamVeld, 1, 3);
        processorNaamVeld.setText(robot.geefProcessorNaam());

        //***Aantal Cores***
        Label aantalCoresLabel = new Label("Aantal cores: ");
        addRobotContainer.add(aantalCoresLabel, 0, 4);
        Spinner<Integer> aantalCoresSpinner = new Spinner<>(1, 100, robot.geefAantalCores(), 10); // min, max, initial, step
        addRobotContainer.add(aantalCoresSpinner, 1, 4);
        //***Cache Opslag***
        Label cacheOpslagLabel = new Label("Cache opslag: ");
        addRobotContainer.add(cacheOpslagLabel, 0, 5);
        Spinner<Integer> cacheOpslagSpinner = new Spinner<>(1, 1000, robot.geefCacheOpslag(), 10); // min, max, initial, step
        addRobotContainer.add(cacheOpslagSpinner, 1, 5);

        //***Opslagschijf naam***
        Label opslagschijfNaamLabel = new Label("Opslagschijf Naam: ");
        addRobotContainer.add(opslagschijfNaamLabel, 0, 6);
        TextField opslagschijfNaamVeld = new TextField();
        addRobotContainer.add(opslagschijfNaamVeld, 1, 6);
        opslagschijfNaamVeld.setText(robot.geefOpslagSchijfNaam());

        //***Max Opslag***
        Label maxOpslagLabel = new Label("Max Opslag: ");
        addRobotContainer.add(maxOpslagLabel, 0, 7);
        Spinner<Integer> maxOpslagSpinner = new Spinner<>(robot.geefMaxOpslag(), Integer.MAX_VALUE, robot.geefMaxOpslag(), 10); // min, max, initial, step
        addRobotContainer.add(maxOpslagSpinner, 1, 7);

        // *** Delete Button ***
        Button deleteButton = new Button("Verwijderen");
        addRobotContainer.add(deleteButton, 0, 9);

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Remove the selected robot from the list
                robots.remove(robot);

                // Close the EditRobotScreen stage
                editRobotStage.close();

                // Open the RobotListScreen again
                Stage robotListStage = RobotListScreen.makeRobotStage(robots);
                robotListStage.show();
            }
        });

        //***wijzigenKnop***
        Button wijzigenKnop = new Button("Wijzigen");
        addRobotContainer.add(wijzigenKnop, 0, 8);

        wijzigenKnop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Update robot's data
                robot.setNaam(naamVeld.getText());
                robot.setKleur(kleurPicker.getValue());
                robot.setProcessorNaam(processorNaamVeld.getText());
                robot.setAantalCores(aantalCoresSpinner.getValue());
                robot.setCacheOpslag(cacheOpslagSpinner.getValue());
                robot.setOpslagSchijfNaam(opslagschijfNaamVeld.getText());
                robot.setMaxOpslag(maxOpslagSpinner.getValue());

                // Close the EditRobotScreen stage
                editRobotStage.close();

                // Open the RobotListScreen again
                Stage robotListStage = RobotListScreen.makeRobotStage(robots);
                robotListStage.show();
            }
        });



        editRobotStage.setTitle("Robot wijzigen");
        editRobotStage.setScene(
                new Scene(addRobotContainer, 800, 400));
        return(editRobotStage);
    }
}

