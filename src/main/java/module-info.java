module com.bsuir.karalionak.metrology {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.bsuir.karalionak.metrology to javafx.fxml;
    exports com.bsuir.karalionak.metrology;
}