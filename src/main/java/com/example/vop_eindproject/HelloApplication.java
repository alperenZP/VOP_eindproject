package com.example.vop_eindproject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import java.math.BigDecimal;


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

        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ObservableList<Robot> robots = getRobotsList();
                RobotListScreen.makeRobotStage(robots).show();
                primaryStage.hide();
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.close();
            }
        });




        //**********Container*********
        VBox root = new VBox(5);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, credit,startButton, exitButton);
        primaryStage.setTitle("Startpagina");
        primaryStage.setScene(new Scene(root, 850, 478));
        primaryStage.show();
    }

    private ObservableList<Robot> getRobotsList(){
        Lichaam lichaam1 = new Lichaam("AxTax", Color.MAROON, 0,20);
        OpslagSchijf opslagSchijf = new OpslagSchijf("Dragon", 600, 53);
        Processor processor1 = new Processor("Dreebl", 16, BigDecimal.valueOf(689.555), 4, "Intel");
        Robot robot1 = new Robot("284303", "Randy", BigDecimal.valueOf(0.15), true, lichaam1, opslagSchijf, processor1);
        ObservableList<Robot> robots = FXCollections.observableArrayList(robot1);
        return robots;
    }

    public static void main(String[] args) {
        launch();
    }
}