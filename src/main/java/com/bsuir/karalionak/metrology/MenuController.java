package com.bsuir.karalionak.metrology;

import com.bsuir.karalionak.metrology.model.LexemInf;
import com.bsuir.karalionak.metrology.model.Lexems;
import com.bsuir.karalionak.metrology.service.FileService;
import com.bsuir.karalionak.metrology.service.Lexer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MenuController {
    public MenuItem OpenFileMenuItem;
    public TableView<LexemInf> TableInfo;
    private final Lexems lexems;
    private final Lexer lexer;
    private static Stage stage;
    public TableColumn<LexemInf, Integer> NumberJ;
    public TableColumn<LexemInf, LexemInf> Operator;
    public TableColumn<LexemInf, Integer> F_Operator;
    public TableColumn<LexemInf, Integer> NumberI;
    public TableColumn<LexemInf, LexemInf> Operands;
    public TableColumn<LexemInf, Integer> F_Operands;

    {
        lexems = new Lexems();
        lexer = new Lexer();
    }

    public static void setStage(Stage stage) {
        MenuController.stage = stage;
    }

    public void OpenFileAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null) {
            FileService fileService = new FileService(file);
            initLexems(fileService);
            //fillTable();
        }
    }

    public void initLexems(FileService fileService) {
        lexems.setLexems(fileService.getLexemsFromFile(lexems.getLexems()));
        ArrayList<String> operator = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();
        lexer.lexAlloc(operands, operator, lexems.getLexems());
        lexems.setOperands(lexer.getLexemInfo(operands));
        lexems.setOperators(lexer.getLexemInfo(operator));
        System.out.println(lexems.getOperands());
        System.out.println(lexems.getOperators());
    }

    private void fillTable(){
        ObservableList<LexemInf> operatorsList = (ObservableList<LexemInf>) lexems.getOperators();
        TableInfo = new TableView<LexemInf>(operatorsList);
        Operator.setCellValueFactory(new PropertyValueFactory<LexemInf, LexemInf>("operators"));

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
}
