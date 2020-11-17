package com.bsuir.karalionak.metrology;

import com.bsuir.karalionak.metrology.model.Variable;
import com.bsuir.karalionak.metrology.service.FileService;
import com.bsuir.karalionak.metrology.service.Lexer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChapinMetricsController implements Initializable {
    private static final ObservableList<Variable> listC = FXCollections.observableArrayList();
    private static final ObservableList<Variable> listM = FXCollections.observableArrayList();
    private static final ObservableList<Variable> listP = FXCollections.observableArrayList();
    private static final ObservableList<Variable> listT = FXCollections.observableArrayList();

    private final Lexer lexer;
    private final ArrayList<Variable> listOfVariable;

    public MenuItem OpenFileMenuItem;
    public Button BackButton;
    public TableColumn<Variable, String> GroupC;
    public TableColumn<Variable, String> GroupM;
    public TableColumn<Variable, String> GroupP;
    public TableColumn<Variable, String> GroupT;
    public TableView<Variable> TableT;
    public TableView<Variable> TableC;
    public TableView<Variable> TableM;
    public TableView<Variable> TableP;


    {
        listOfVariable = new ArrayList<>();
        lexer = new Lexer();
    }


    public void initGroupLists() {
        for (Variable v : listOfVariable) {
            if (v.isC()) {
                listC.add(v);
            } else if (v.isM()) {
                listM.add(v);
            }else if(v.isP()){
                listP.add(v);
            }else{
                listT.add(v);
            }
        }
    }

    public void openFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getAppStage());
        if (file != null && file.getAbsolutePath().matches("^.+.txt$")) {
            FileService fileService = new FileService(file);
            initVariables(fileService);
            initGroupLists();
            fillTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error file");
            alert.setHeaderText(null);
            alert.setContentText((file == null ? "You haven't selected a file. " : "Selected" +
                    "file is not a Python program. ") + "Please, try again.");
            alert.showAndWait();
        }
    }

    private void fillTable() {
        TableT.setItems(listT);
        TableP.setItems(listP);
        TableM.setItems(listM);
        TableC.setItems(listC);
    }

    public void initVariables(FileService fileService) {
        listOfVariable.clear();
        fileService.getVariablesFromFile(listOfVariable);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GroupC.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupM.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupP.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupT.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        App.setRoot("menu");
    }
}
