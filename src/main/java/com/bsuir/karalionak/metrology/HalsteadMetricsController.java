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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HalsteadMetricsController implements Initializable {
    private static final ObservableList<Item> dataOperator = FXCollections.observableArrayList();
    private static final ObservableList<Item> dataOperand = FXCollections.observableArrayList();


    private final Lexemes lexemes;
    private final Lexer lexer;


    public MenuItem OpenFileMenuItem;
    public TableColumn<Item, Integer> NumberJ;
    public TableColumn<Item, String> Operators;
    public TableColumn<Item, Integer> F_Operators;
    public TableColumn<Item, Integer> NumberI;
    public TableColumn<Item, String> Operands;
    public TableColumn<Item, Integer> F_Operands;
    public TableView<Item> TableOfOperands;
    public TableView<Item> TableOfOperators;
    public Text UDS;
    public Text TRS;
    public Text TDS;
    public Text URS;
    public Text PD;
    public Text PL;
    public Text PS;
    public Button BackButton;

    {
        lexemes = new Lexemes();
        lexer = new Lexer();
    }

    private static void fillData(ArrayList<LexemeInf> operators, ArrayList<LexemeInf> operands) {
        dataOperand.clear();
        dataOperator.clear();
        int id = 1;
        for (LexemeInf lexeme : operands) {
            dataOperand.add(new Item(lexeme.getName(), lexeme.getCount(), id++));
        }
        id = 1;
        for (LexemeInf lexeme : operators) {
            dataOperator.add(new Item(lexeme.getName(), lexeme.getCount(), id++));
        }
    }

    public void openFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getAppStage());
        if (file != null && file.getAbsolutePath().matches("^.+.txt$")) {
            FileService fileService = new FileService(file);
            initLexemes(fileService);
            fillTable();
            displayMetrics();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error file");
            alert.setHeaderText(null);
            alert.setContentText((file == null ? "You haven't selected a file. " : "Selected" +
                    "file is not a Python program. ") + "Please, try again.");
            alert.showAndWait();
        }
    }

    public void initLexemes(FileService fileService) {
        lexemes.clear();
        fileService.getLexemesFromFile(lexemes.getLexemes());
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        lexer.lexAlloc(operands, operator, lexemes.getLexemes());
        lexemes.setOperands(lexer.getLexemeInfo(operands));
        lexemes.setOperators(lexer.getLexemeInfo(operator));
        fillData(lexemes.getOperators(), lexemes.getOperands());
    }

    private void fillTable() {
        TableOfOperands.setItems(dataOperand);
        TableOfOperators.setItems(dataOperator);
    }

    private void displayMetrics() {
        int totalOperatorsOccurrence = 0;
        int totalOperandsOccurrence = 0;
        int uniqueOperators = lexemes.getOperators().size();
        int uniqueOperands = lexemes.getOperands().size();

        for (int i = 0; i < lexemes.getOperators().size(); i++) {
            totalOperatorsOccurrence += lexemes.getOperators().get(i).getCount();
        }

        for (int i = 0; i < lexemes.getOperands().size(); i++) {
            totalOperandsOccurrence += lexemes.getOperands().get(i).getCount();
        }

        int programDictionary = uniqueOperands + uniqueOperators;
        int programLength = totalOperandsOccurrence + totalOperatorsOccurrence;
        int programScope = (int) Math.round(programLength * (Math.log10(programDictionary) / Math.log10(2)));


        String UDS_S = "Unique operands: ";
        UDS.setText(UDS_S + uniqueOperands);
        String URS_S = "Unique operators: ";
        URS.setText(URS_S + uniqueOperators);
        String TDS_S = "Total operands occurrence: ";
        TDS.setText(TDS_S + totalOperandsOccurrence);
        String TRS_S = "Total operators occurrence: ";
        TRS.setText(TRS_S + totalOperatorsOccurrence);
        String PD_S = "Program dictionary: ";
        PD.setText(PD_S + programDictionary);
        String PL_S = "Program length: ";
        PL.setText(PL_S + programLength);
        String PS_S = "Program scope: ";
        PS.setText(PS_S + programScope);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NumberJ.setCellValueFactory(new PropertyValueFactory<>("id"));
        Operators.setCellValueFactory(new PropertyValueFactory<>("name"));
        F_Operators.setCellValueFactory(new PropertyValueFactory<>("count"));
        NumberI.setCellValueFactory(new PropertyValueFactory<>("id"));
        Operands.setCellValueFactory(new PropertyValueFactory<>("name"));
        F_Operands.setCellValueFactory(new PropertyValueFactory<>("count"));
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        App.setRoot("menu");
    }
}
