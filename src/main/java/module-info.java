module com.example.prog {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.skiplegday;
    opens com.example.skiplegday to javafx.fxml;
}