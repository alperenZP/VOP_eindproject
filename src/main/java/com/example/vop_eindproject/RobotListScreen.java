package com.example.vop_eindproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import static com.example.vop_eindproject.AddRobotScreen.makeAddRobotStage;
import static com.example.vop_eindproject.EditRobotScreen.makeEditRobotStage;
import static com.example.vop_eindproject.controlRobotScreen.makeControlRobotStage;

public class RobotListScreen {
    public static Stage makeRobotStage(ObservableList<Robot> robots) {
        Stage robotListStage = new Stage();

        TableView<Robot> tableView = new TableView<>();
        TableColumn<Robot, String> naamKolom = new TableColumn<>("Naam");
        TableColumn<Robot, String> accuKolom = new TableColumn<>("Accu");
        TableColumn<Robot, String> opslagKolom = new TableColumn<>("Opslag");
        TableColumn<Robot, String> plaatsKolom = new TableColumn<>("Coördinaten");

        naamKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
        accuKolom.setCellValueFactory(new PropertyValueFactory<>("accuPercentage"));

        opslagKolom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Robot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Robot, String> param) {
                return new SimpleStringProperty(param.getValue().geefOpslagGegevens());
            }
        });

        plaatsKolom.setCellValueFactory(new PropertyValueFactory<>("plaats"));

        plaatsKolom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Robot, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Robot, String> param) {
                return new SimpleStringProperty(param.getValue().geefHuidigeLocatie());
            }
        });

        tableView.getColumns().addAll(naamKolom, accuKolom, opslagKolom, plaatsKolom);
        tableView.setItems(robots);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        Button createButton = new Button("Creëren");
            createButton.setMinSize(200,50);
        Button controlButton = new Button("Besturen");
            controlButton.setMinSize(200, 50);
        Button editButton = new Button("Wijzigen");
            editButton.setMinSize(200, 50);
        Button returnButton = new Button("Sluit af");
            returnButton.setMinSize(200, 50);


        createButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage addRobotStage =
                        makeAddRobotStage(robots);
                addRobotStage.show();
            }

        });

        controlButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    robotListStage.close();

                    Stage controlRobotStage =
                            makeControlRobotStage(tableView.getSelectionModel().getSelectedItem(), robots, tableView);
                    controlRobotStage.show();
                }
            }

        });

        editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tableView.getSelectionModel().getSelectedItem() != null){
                    robotListStage.close();
                    Stage editRobotStage = makeEditRobotStage(tableView.getSelectionModel().getSelectedItem(), robots, tableView);
                    editRobotStage.show();
                }

            }
        });

        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                robotListStage.close();
            }
        });



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

    public static void refreshList(ObservableList<Robot> updatedRobots, TableView<Robot> tableView) {
        tableView.getItems().clear();
        tableView.setItems(updatedRobots);
    }



}
