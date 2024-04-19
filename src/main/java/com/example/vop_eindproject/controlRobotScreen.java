package com.example.vop_eindproject;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class controlRobotScreen {
    public static Stage makeControlRobotStage(Robot robot, ObservableList<Robot> robots, TableView<Robot> tableView) {
        Stage primaryStage = new Stage();

        // Create the root VBox to hold the battery, D-pad, and grid with the red triangle
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Create an HBox to hold the battery and D-pad
        HBox batteryAndDpad = new HBox(10);

        // Create a rectangle to represent the battery outline
        Rectangle batteryOutline = new Rectangle(100, 50);
        batteryOutline.setFill(null);
        batteryOutline.setStroke(null); // Remove the stroke to hide the border

        // Create a VBox to hold the battery filling and percentage label
        VBox batteryContent = new VBox();
        batteryContent.setAlignment(Pos.CENTER);

        BigDecimal accu = robot.getAccuPercentage().multiply(BigDecimal.valueOf(100));

        // Create a rectangle to represent the battery filling
        Rectangle batteryFill = new Rectangle(40, accu.doubleValue());
        batteryFill.setFill(Color.LIGHTGREEN);

        // Create a label to display the percentage inside the battery

        Label percentageLabel = new Label(accu+ "%");
        percentageLabel.setStyle("-fx-font-size: 14pt;");

        // Add the battery filling and percentage label to the battery content VBox
        batteryContent.getChildren().addAll(batteryFill, percentageLabel);

        // Create a VBox to hold the D-pad buttons
        VBox dPad = new VBox(10);
        dPad.setAlignment(Pos.CENTER);

        // Create arrow buttons for the D-pad
        Button upButton = new Button("↑");
        Button downButton = new Button("↓");
        Button leftButton = new Button("←");
        Button rightButton = new Button("→");
        Button terugNaarLijst = new Button("Terug");

        Label coordinatenLabel = new Label(robot.geefHuidigeLocatie());



        // Add the arrow buttons to the D-pad VBox
        dPad.getChildren().addAll(terugNaarLijst, upButton, downButton, leftButton, rightButton, coordinatenLabel);

        // Create a Pane to hold the grid with the red triangle
        Pane gridPane = new Pane();
        gridPane.setPrefSize(200, 200);

        // Create a rectangle grid
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Rectangle square = new Rectangle(40, 40);
                square.setFill(Color.WHITE);
                square.setStroke(Color.BLACK);
                square.setX(col * 40);
                square.setY(row * 40);
                gridPane.getChildren().add(square);
            }
        }

        // Create a red triangle
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, -5.0,
                5.0, 5.0,
                -5.0, 5.0
        );
        triangle.setFill(Color.RED);
        triangle.setLayoutX(100);
        triangle.setLayoutY(100);

        AtomicInteger trianglegridposx = new AtomicInteger();
        AtomicInteger trianglegridposy = new AtomicInteger();

        upButton.setOnAction(e -> {
            robot.loopVooruit();
            coordinatenLabel.setText(robot.geefHuidigeLocatie());
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            if (robot.getAccuPercentage().compareTo(BigDecimal.ZERO) > 0) {
                batteryFill.setY(batteryFill.getY());
                if (trianglegridposy.get() < 2){
                    triangle.setLayoutY(triangle.getLayoutY()-40);
                    trianglegridposy.getAndIncrement();
                } else {
                    triangle.setLayoutY(triangle.getLayoutY()+160);
                    trianglegridposy.set(-2);
                }
            }
        });
        downButton.setOnAction(e -> {
            robot.loopTerug();
            coordinatenLabel.setText(robot.geefHuidigeLocatie());
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            if (robot.getAccuPercentage().compareTo(BigDecimal.ZERO) > 0) {
                if (trianglegridposy.get() > -2){
                    triangle.setLayoutY(triangle.getLayoutY()+40);
                    trianglegridposy.getAndDecrement();
                } else {
                    triangle.setLayoutY(triangle.getLayoutY()-160);
                    trianglegridposy.set(2);
                }
            }

        });

        rightButton.setOnAction(e -> {
            robot.loopRechts();
            coordinatenLabel.setText(robot.geefHuidigeLocatie());
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            if (robot.getAccuPercentage().compareTo(BigDecimal.ZERO) > 0) {
                if (trianglegridposx.get() < 2){
                    triangle.setLayoutX(triangle.getLayoutX()+40);
                    trianglegridposx.getAndIncrement();
                } else {
                    triangle.setLayoutX(triangle.getLayoutX()-160);
                    trianglegridposx.set(-2);
                }
            }

        });

        leftButton.setOnAction(e -> {
            robot.loopLinks();
            coordinatenLabel.setText(robot.geefHuidigeLocatie());
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            if (robot.getAccuPercentage().compareTo(BigDecimal.ZERO) > 0) {
                if (trianglegridposx.get() > -2){
                    triangle.setLayoutX(triangle.getLayoutX()-40);
                    trianglegridposx.getAndDecrement();
                } else {
                    triangle.setLayoutX(triangle.getLayoutX()+160);
                    trianglegridposx.set(2);
                }
            }

        });

        terugNaarLijst.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Close the EditRobotScreen stage
                primaryStage.close();

                // Open the RobotListScreen again
                Stage robotListStage = RobotListScreen.makeRobotStage(robots);
                robotListStage.show();
            }
        });


        // Add the red triangle to the gridPane
        gridPane.getChildren().add(triangle);

        // Add the battery, D-pad, and grid to the batteryAndDpad HBox
        batteryAndDpad.getChildren().addAll(batteryOutline, batteryContent, dPad);

        // Create an HBox to hold the input field and square label
        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);

        // Create the input field
        TextField numberField1 = new TextField();
        numberField1.setPromptText("Eerste getal");
        TextField operatorField = new TextField();
        operatorField.setPromptText("Toestandsteken");
        TextField numberField2 = new TextField();
        numberField2.setPromptText("Tweede getal");

        // Create the label to display the square of the entered number
        Label squareLabel = new Label();

        // Create the button to calculate the square
        Button calculateButton = new Button("Bereken");
        calculateButton.setOnAction(e -> {
            int number1 = Integer.parseInt(numberField1.getText());
            int number2 = Integer.parseInt(numberField2.getText());
            String toestandsteken = String.valueOf(operatorField.getText());
            int resultaat = robot.maakBerekening(number1, number2, toestandsteken);
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            squareLabel.setText("Resultaat: " + resultaat);
        });

        // Add the input field, button, and square label to the inputBox HBox
        inputBox.getChildren().addAll(numberField1, operatorField, numberField2, calculateButton, squareLabel);

        // Add the battery, D-pad, grid, and input field to the root VBox
        root.getChildren().addAll(batteryAndDpad, gridPane, inputBox);
        // Set the margin for the batteryContent VBox to align the percentage label inside the battery filling

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 850, 478);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Control Robot Screen");
        primaryStage.show();
        return primaryStage;
    }

    private void calculateSquare() {

    }

}

