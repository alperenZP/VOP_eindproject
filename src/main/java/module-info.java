module com.example.vop_eindproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vop_eindproject to javafx.fxml;
    exports com.example.vop_eindproject;
}