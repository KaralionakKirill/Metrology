package com.bsuir.karalionak.metrology;

import com.bsuir.karalionak.metrology.model.Item;
import com.bsuir.karalionak.metrology.model.LexemeInf;
import com.bsuir.karalionak.metrology.model.Lexemes;
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
    private static final ObservableList<Item> dataOperand = FXCollections.observableArrayList();

    private final Lexemes lexemes;
    private final Lexer lexer;

    public MenuItem OpenFileMenuItem;
    public TableColumn<Item, Integer> Number;
    public TableColumn<Item, String> Operands;
    public TableColumn<Item, Integer> F_Operands;
    public Button BackButton;
    public TableView<Item> TableOfOperands;


    {
        lexemes = new Lexemes();
        lexer = new Lexer();
    }

    private static void fillData(ArrayList<LexemeInf> operands) {
        dataOperand.clear();
        int id = 1;
        for (LexemeInf lexeme : operands) {
            dataOperand.add(new Item(lexeme.getName(), lexeme.getCount(), id++));
        }
    }

    public void openFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getAppStage());
        if (file != null && file.getAbsolutePath().matches("^.+.txt$")) {
            FileService fileService = new FileService(file);
            initLexemes(fileService);
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
        TableOfOperands.setItems(dataOperand);
    }

    public void initLexemes(FileService fileService) {
        lexemes.clear();
        lexemes.setLexemes(fileService.getLexemesFromFile(lexemes.getLexemes()));
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        lexer.lexAlloc(operands, operator, lexemes.getLexemes());
        lexemes.setOperands(lexer.getLexemeInfo(operands));
        lexemes.setOperators(lexer.getLexemeInfo(operator));
        fillData(lexemes.getOperands());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Number.setCellValueFactory(new PropertyValueFactory<>("id"));
        Operands.setCellValueFactory(new PropertyValueFactory<>("name"));
        F_Operands.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        App.setRoot("menu");
    }
}
