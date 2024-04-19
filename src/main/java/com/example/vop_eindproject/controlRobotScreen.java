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

        HBox root = new HBox(150);
        root.setPadding(new Insets(10));

        HBox batteryAndDpad = new HBox(10);

        Rectangle batteryOutline = new Rectangle(100, 50);
        batteryOutline.setFill(null);
        batteryOutline.setStroke(null);

        VBox batteryContent = new VBox();
        batteryContent.setAlignment(Pos.CENTER);

        BigDecimal accu = robot.getAccuPercentage().multiply(BigDecimal.valueOf(100));

        Rectangle batteryFill = new Rectangle(40, accu.doubleValue());
        batteryFill.setFill(Color.LIGHTGREEN);


        Label percentageLabel = new Label(accu+ "%");
        percentageLabel.setStyle("-fx-font-size: 14pt;");

        batteryContent.getChildren().addAll(batteryFill, percentageLabel);

        VBox dPad = new VBox(10);
        dPad.setAlignment(Pos.CENTER);

        Button upButton = new Button("↑");
        Button downButton = new Button("↓");
        Button leftButton = new Button("←");
        Button rightButton = new Button("→");
        Button terugNaarLijst = new Button("Terug");

        Label coordinatenLabel = new Label(robot.geefHuidigeLocatie());



        dPad.getChildren().addAll(terugNaarLijst, upButton, downButton, leftButton, rightButton, coordinatenLabel);

        Pane gridPane = new Pane();
        gridPane.setPrefSize(200, 200);

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
                primaryStage.close();

                Stage robotListStage = RobotListScreen.makeRobotStage(robots);
                robotListStage.show();
            }
        });


        gridPane.getChildren().add(triangle);

        batteryAndDpad.getChildren().addAll(batteryOutline, batteryContent, dPad);

        VBox inputBox = new VBox(10);
        inputBox.setAlignment(Pos.CENTER);

        Spinner<Integer> numberField1 = new Spinner<>();
        numberField1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
        numberField1.setEditable(true);
        numberField1.setPromptText("Eerste getal");

        Spinner<Integer> numberField2 = new Spinner<>();
        numberField2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
        numberField2.setEditable(true);
        numberField2.setPromptText("Tweede getal");

        ComboBox<String> operatorField = new ComboBox<>();
        operatorField.getItems().addAll("*", "+", "-", "/");
        operatorField.setPromptText("Toestandsteken");


        Label squareLabel = new Label();

        Button calculateButton = new Button("Bereken");
        calculateButton.setOnAction(e -> {
            int number1 = Integer.parseInt(String.valueOf(numberField1.getValue()));
            int number2 = Integer.parseInt(String.valueOf(numberField2.getValue()));
            String toestandsteken = String.valueOf(operatorField.getValue());
            Integer resultaat = robot.maakBerekening(number1, number2, toestandsteken);
            percentageLabel.setText(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100))+ "%");
            batteryFill.setHeight(robot.getAccuPercentage().multiply(BigDecimal.valueOf(100)).doubleValue());

            if (resultaat != null){
                squareLabel.setText("Resultaat: " + resultaat);
            } else {
                squareLabel.setText("Ongeldige invoer");
            }


        });

        inputBox.getChildren().addAll(numberField1, operatorField, numberField2, calculateButton, squareLabel);

        root.getChildren().addAll(batteryAndDpad, gridPane, inputBox);

        Scene scene = new Scene(root, 850, 478);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Control Robot Screen");
        primaryStage.show();
        return primaryStage;
    }

    private void calculateSquare() {

    }

}

