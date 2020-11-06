package com.bsuir.karalionak.metrology;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    public Button HalstedMetricsButton;
    public Button ChapinMetrics;

    public void goToHalstedMetrics(ActionEvent actionEvent) throws IOException {
        App.setRoot("halstead-metrics");
    }

    public void goToChapinMetrics(ActionEvent actionEvent) throws IOException {
        App.setRoot("chapin-metrics");
    }
}
