module com.example.skiplegday {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.skiplegday to javafx.fxml;
    exports com.example.skiplegday;
}