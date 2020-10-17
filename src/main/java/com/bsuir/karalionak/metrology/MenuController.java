package com.bsuir.karalionak.metrology;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    public Button HalstedMetricsButton;

    public void GoToHalstedMetrics(ActionEvent actionEvent) throws IOException {
        App.setRoot("halstead-metrics");
    }
}
