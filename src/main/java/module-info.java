module com.example.lector {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lector to javafx.fxml;
    exports com.example.lector;
    exports com.example.lector.models;
    opens com.example.lector.models to javafx.fxml;
}