package com.example.skiplegday.view;

import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class InfoTooltip {
    public static void agganciaTooltip(Parent parent, String id, String testo) {
        Tooltip tt = new Tooltip();
        tt.setId(id);
        tt.setShowDelay(Duration.millis(10));
        tt.setText(testo);
        Tooltip.install(parent, tt);
    }
}
