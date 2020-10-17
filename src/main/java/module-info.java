module com.bsuir.karalionak.metrology {
    requires javafx.controls;
    requires javafx.fxml;
    exports com.bsuir.karalionak.metrology.model;
    exports com.bsuir.karalionak.metrology.service;

    opens com.bsuir.karalionak.metrology to javafx.fxml;
    exports com.bsuir.karalionak.metrology;
}