package com.example.vop_eindproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //*********Items**********
        Label title = new Label("ROBOTS");
            title.setTextFill(Color.ROYALBLUE);
            title.setFont(new Font(150));

        Label credit = new Label("Door Alperen Tok");
            credit.setTextFill(Color.MAROON);

        Button startButton = new Button("Start");
            startButton.setMinSize(200, 50);
        Button exitButton = new Button("Sluit af");
            exitButton.setMinSize(200, 50);

        //**********Container*********
        VBox root = new VBox(5);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, credit,startButton, exitButton);
        primaryStage.setTitle("Startpagina");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}