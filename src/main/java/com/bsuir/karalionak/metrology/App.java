package com.bsuir.karalionak.metrology;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage appStage;

    public static Stage getAppStage() {
        return appStage;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML(), 600, 450);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    private Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu" + ".fxml"));
        return fxmlLoader.load();
    }

}