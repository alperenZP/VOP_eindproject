package com.example.vop_eindproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene;
        VBox root;
        ListView<String> lijstView;
        ObservableList<String> observableLijst = FXCollections.observableArrayList();
        Label keuze;

        root = new VBox(5);
        scene = new Scene(root, 320, 240);
        lijstView = new ListView<String>();
        observableLijst.addAll("Berlijn", "Brussel", "Madrid", "Moskou", "Helsinki");
        lijstView.setItems(observableLijst);
        lijstView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        keuze = new Label();
        lijstView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                keuze.setText(lijstView.getSelectionModel().getSelectedItem());
            }
        });


        root.getChildren().addAll(lijstView, keuze);
        primaryStage.setTitle("Demo lijsten");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}