package com.bsuir.karalionak.metrology.service;

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

    public ArrayList<String> getLexemesFromFile(ArrayList<String> lexemes) {
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                lexemes = lexer.lexemesFromLine(line + "\n", lexemes);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lexemes;
    }

}
