package com.bsuir.karalionak.metrology;

import com.bsuir.karalionak.metrology.model.Item;
import com.bsuir.karalionak.metrology.model.LexemInf;
import com.bsuir.karalionak.metrology.model.Lexems;
import com.bsuir.karalionak.metrology.service.FileService;
import com.bsuir.karalionak.metrology.service.Lexer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private static final ObservableList<Item> dataOperator = FXCollections.observableArrayList();
    private static final ObservableList<Item> dataOperand = FXCollections.observableArrayList();


    private final Lexems lexems;
    private final Lexer lexer;


    public MenuItem OpenFileMenuItem;
    public TableView<Item> TableInfo;
    public TableColumn<Item, Integer> NumberJ;
    public TableColumn<Item, String> Operators;
    public TableColumn<Item, Integer> F_Operators;
    public TableColumn<Item, Integer> NumberI;
    public TableColumn<Item, String> Operands;
    public TableColumn<Item, Integer> F_Operands;

    {
        lexems = new Lexems();
        lexer = new Lexer();
    }

    public void OpenFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(App.getAppStage());
        if (file != null && file.getAbsolutePath().matches("^.+.txt$")) {
            FileService fileService = new FileService(file);
            initLexems(fileService);
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

    public void initLexems(FileService fileService) {
        lexems.setLexems(fileService.getLexemsFromFile(lexems.getLexems()));
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        lexer.lexAlloc(operands, operator, lexems.getLexems());
        lexems.setOperands(lexer.getLexemInfo(operands));
        lexems.setOperators(lexer.getLexemInfo(operator));
        fillData(lexems.getOperators(), lexems.getOperands());
        System.out.println(lexems.getOperands());
        System.out.println(lexems.getOperators());
    }

    private void fillTable() {
        TableInfo.setItems(dataOperand);
    }

    private static void fillData(ArrayList<LexemInf> operators, ArrayList<LexemInf> operands){
        int id = 1;
        for(LexemInf lexem: operands){
            dataOperand.add(new Item(lexem.getName(), lexem.getCount(), id++));
        }
        id = 1;
        for(LexemInf lexem: operators){
            dataOperand.add(new Item(lexem.getName(), lexem.getCount(), id++));
        }
    }

    private void displayMetrics() {
        int totalOperatorsOccurrence = 0;
        int totalOperandsOccurrence = 0;
        int uniqueOperators = lexems.getOperators().size();
        int uniqueOperands = lexems.getOperands().size();

        for (int i = 0; i < lexems.getOperators().size(); i++) {
            totalOperatorsOccurrence += lexems.getOperators().get(i).getCount();
        }

        for (int i = 0; i < lexems.getOperands().size(); i++) {
            totalOperandsOccurrence += lexems.getOperands().get(i).getCount();
        }

        int programDictionary = uniqueOperands + uniqueOperators;
        int programLength = totalOperandsOccurrence + totalOperatorsOccurrence;
        int programScope = (int) Math.round(programLength * (Math.log10(programDictionary) / Math.log10(2)));

//        JOptionPane.showMessageDialog(null, "Уникальных операторов: " + uniqueOperators + "\n" +
//                "Уникальных операндов: " + uniqueOperands + "\n" +
//                "Общее число операторов: " + totalOperatorsOccurrence + "\n" +
//                "Общее число операндов: " + totalOperandsOccurrence + "\n" +
//                "Словарь программы: " + programDictionary + "\n" +
//                "Длина программы: " + programLength + "\n" +
//                "Объём программы: " + programScope, "Метрики Холстеда", JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Operators.setCellValueFactory(new PropertyValueFactory<>("name"));
        F_Operators.setCellValueFactory(new PropertyValueFactory<>("count"));
        Operands.setCellValueFactory(new PropertyValueFactory<>("name"));
        F_Operands.setCellValueFactory(new PropertyValueFactory<>("count"));
    }
}
