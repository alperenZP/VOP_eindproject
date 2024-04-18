package com.example.vop_eindproject;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddRobotScreen {
    public static Stage makeAddRobotStage(ObservableList<Robot> robots) {
        Stage addRobotStage = new Stage();
        GridPane addRobotContainer = new GridPane();
        addRobotContainer.setVgap(5);
        addRobotContainer.setHgap(5);
        addRobotContainer.setPadding(new Insets(10));

        //***Code***
        Label codeLabel = new Label("Code: ");
        addRobotContainer.add(codeLabel, 0, 0);
        TextField codeVeld = new TextField();
        addRobotContainer.add(codeVeld, 1 , 0);
        //***Naam***
        Label naamLabel = new Label("Naam: ");
        addRobotContainer.add(naamLabel, 0, 1);
        TextField naamVeld = new TextField();
        addRobotContainer.add(naamVeld, 1, 1);
        //***Kleur***
        Label kleurLabel = new Label("Kleur: ");
        addRobotContainer.add(kleurLabel, 0, 2);
        ColorPicker kleurPicker = new ColorPicker();
        addRobotContainer.add(kleurPicker, 1, 2);

        //***Processor Naam***
        Label processorNaamLabel = new Label("Processor Naam: ");
        addRobotContainer.add(processorNaamLabel, 0, 3);
        TextField processorNaamVeld = new TextField();
        addRobotContainer.add(processorNaamVeld, 1, 3);

        //***Aantal Cores***
        Label aantalCoresLabel = new Label("Aantal cores: ");
        addRobotContainer.add(aantalCoresLabel, 0, 4);
        Spinner<Integer> aantalCoresSpinner = new Spinner<>(1, 100, 1); // min, max, initial, step
        addRobotContainer.add(aantalCoresSpinner, 1, 4);
        //***Cache Opslag***
        Label cacheOpslagLabel = new Label("Cache opslag: ");
        addRobotContainer.add(cacheOpslagLabel, 0, 5);
        Spinner<Integer> cacheOpslagSpinner = new Spinner<>(1, 1000, 0, 10); // min, max, initial, step
        addRobotContainer.add(cacheOpslagSpinner, 1, 5);

        //***Opslagschijf naam***
        Label opslagschijfNaamLabel = new Label("Opslagschijf Naam: ");
        addRobotContainer.add(opslagschijfNaamLabel, 0, 6);
        TextField opslagschijfNaamVeld = new TextField();
        addRobotContainer.add(opslagschijfNaamVeld, 1, 6);

        //***Max Opslag***
        Label maxOpslagLabel = new Label("Max Opslag: ");
        addRobotContainer.add(maxOpslagLabel, 0, 7);
        Spinner<Integer> maxOpslagSpinner = new Spinner<>(1, Integer.MAX_VALUE, 1, 10); // min, max, initial, step
        addRobotContainer.add(maxOpslagSpinner, 1, 7);

        //***toevoegenKnop***
        Button toevoegenKnop = new Button("Toevoegen");
        addRobotContainer.add(toevoegenKnop, 0, 8);
        toevoegenKnop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int maxOpslagValue = maxOpslagSpinner.getValue();
                int aantalCoresValue = aantalCoresSpinner.getValue();
                int cacheOpslagValue = cacheOpslagSpinner.getValue();
                OpslagSchijf opslagSchijf = new OpslagSchijf(opslagschijfNaamVeld.getText(), maxOpslagValue, 0);
                Processor processor = new Processor(processorNaamVeld.getText(), aantalCoresValue, BigDecimal.valueOf(400), cacheOpslagValue, "ACME");
                Lichaam lichaam = new Lichaam("SuperRobo", kleurPicker.getValue(), 0, 0);

                robots.add(new Robot(codeVeld.getText(),
                        naamVeld.getText(),
                        BigDecimal.ONE,
                        true,
                        lichaam,
                        opslagSchijf,
                        processor
                ));
                addRobotStage.close();
            }
        });
        addRobotStage.setTitle("Robot toevoegen");
        addRobotStage.setScene(
                new Scene(addRobotContainer, 800, 400));
        return(addRobotStage);
    }
}
