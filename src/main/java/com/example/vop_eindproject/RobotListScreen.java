package com.example.vop_eindproject;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RobotListScreen {
    public static Stage makeRobotStage() {
        Stage robotListStage = new Stage();

        TableView<Robot> tableView = new TableView<Robot>();
        TableColumn<Robot, String> naamKolom =
                new TableColumn<Robot, String>("Naam");
        TableColumn<Robot, String> accuKolom =
                new TableColumn<Robot, String>("Accu");
        TableColumn<Robot, String> opslagKolom =
                new TableColumn<Robot, String>("Opslag");
        TableColumn<Robot, String> plaatsKolom =
                new TableColumn<Robot, String>("Coördinaten");
        tableView.setMinWidth(800);
        tableView.setMinHeight(250);
        tableView.getColumns().addAll(naamKolom, accuKolom, opslagKolom, plaatsKolom);
        naamKolom.setCellValueFactory(new PropertyValueFactory("naam"));
        accuKolom.setCellValueFactory(new PropertyValueFactory("accu"));
        opslagKolom.setCellValueFactory(new PropertyValueFactory("opslag"));
        plaatsKolom.setCellValueFactory(new PropertyValueFactory("plaats"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Button createButton = new Button("Creëren");
            createButton.setMinSize(200,50);
        Button controlButton = new Button("Besturen");
            controlButton.setMinSize(200, 50);
        Button editButton = new Button("Wijzigen");
            editButton.setMinSize(200, 50);
        Button returnButton = new Button("Terug");
            returnButton.setMinSize(200, 50);

        VBox robotsContainer = new VBox(2);
        robotsContainer.setPadding(new Insets(15));
        VBox tableContainer = new VBox();
        HBox buttonsContainer = new HBox(7);
        robotsContainer.getChildren().addAll(tableContainer, buttonsContainer);
        tableContainer.getChildren().addAll(tableView);
        HBox.setHgrow(tableContainer, javafx.scene.layout.Priority.ALWAYS);
        buttonsContainer.getChildren().addAll(createButton, controlButton,
                editButton, returnButton);
        robotListStage.setTitle("List");
        robotListStage.setScene(new Scene(robotsContainer, 850, 478));
        return (robotListStage);
    }

}
