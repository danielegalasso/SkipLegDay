package com.example.skiplegday.view;

import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HoveredThresholdNode extends StackPane {
    public HoveredThresholdNode(String dateLabel) {
        setPrefSize(10, 10);
        final Label label = createDataThresholdLabel(dateLabel);
        setOnMouseEntered(event -> {
            getChildren().setAll(label);
            setCursor(Cursor.NONE);
            toFront();
        });
        setOnMouseExited(event -> {
            getChildren().clear();
            setCursor(Cursor.CROSSHAIR);
        });
    }
    private Label createDataThresholdLabel(String dateLabel) {
        final Label label = new Label(dateLabel);
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return label;
    }
}
