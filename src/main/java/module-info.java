module com.example.prog {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.skiplegday;
    opens com.example.skiplegday to javafx.fxml;

    exports com.example.skiplegday.view;
    opens com.example.skiplegday.view to javafx.fxml;

    exports com.example.skiplegday.controller;
    opens com.example.skiplegday.controller to javafx.fxml;
}