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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    private static final ObservableList<Variable> listVariables = FXCollections.observableArrayList();

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
    public TableView<Variable> TableM_IO;
    public TableView<Variable> TableC_IO;
    public TableView<Variable> TableP_IO;
    public TableView<Variable> TableT_IO;
    public TableView<Variable> VariablesTable;
    public TableColumn<Variable, String> GroupC_IO;
    public TableColumn<Variable, String> GroupM_IO;
    public TableColumn<Variable, String> GroupP_IO;
    public TableColumn<Variable, String> GroupT_IO;
    public TableColumn<Variable, Integer> CountColumn;
    public TableColumn<Variable, String> VariablesColumn;
    public Text CR;


    {
        listOfVariable = new ArrayList<>();
        lexer = new Lexer();
    }

    public void initGroupLists() {
        for (Variable v : listOfVariable) {
            listVariables.add(v);
            if (v.isC()) {
                listC.add(v);
            } else if (v.isM()) {
                listM.add(v);
            } else if (v.isP()) {
                listP.add(v);
            } else {
                listT.add(v);
            }
        }
        outputResult();
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
        VariablesTable.setItems(listVariables);
    }

    public void initVariables(FileService fileService) {
        listOfVariable.clear();
        fileService.getVariablesFromFile(listOfVariable);
    }

    public void outputResult() {
        int reult = 3 * listC.size() + 2 * listM.size() + listP.size() + listT.size() / 2;
        StringBuilder resultStr = new StringBuilder("Q = ");
        resultStr.append("3*").append(listC.size()).append(" + ");
        resultStr.append("2*").append(listM.size()).append("  + ");
        resultStr.append("1*").append(listP.size()).append(" + ");
        resultStr.append("0,5*").append(listT.size()).append(" = ");
        resultStr.append(reult);
        CR.setText(resultStr.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GroupC.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupM.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupP.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupT.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupC_IO.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupM_IO.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupP_IO.setCellValueFactory(new PropertyValueFactory<>("value"));
        GroupT_IO.setCellValueFactory(new PropertyValueFactory<>("value"));
        VariablesColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        CountColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        App.setRoot("menu");
    }
}
