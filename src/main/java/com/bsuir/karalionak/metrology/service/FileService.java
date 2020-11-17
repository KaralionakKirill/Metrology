package com.bsuir.karalionak.metrology.service;

import com.bsuir.karalionak.metrology.model.Variable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {
    private final Lexer lexer;
    private final File file;

    {
        lexer = new Lexer();
    }

    public FileService(File file) {
        this.file = file;
    }

    public void getLexemesFromFile(ArrayList<String> lexemes) {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                lexer.lexemesFromLine(line + "\n", lexemes);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getVariablesFromFile(ArrayList<Variable> list) {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                lexer.initListOfVariables(line + "\n", list);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
