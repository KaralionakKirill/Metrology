package com.bsuir.karalionak.metrology.model;

import java.util.ArrayList;

public class Lexemes {
    private ArrayList<String> lexemes;
    private ArrayList<LexemeInf> operands;
    private ArrayList<LexemeInf> operators;

    public Lexemes() {
        lexemes = new ArrayList<>();
        operands = new ArrayList<>();
        operators = new ArrayList<>();
    }

    public void clear() {
        lexemes.clear();
        operands.clear();
        operators.clear();
    }

    public ArrayList<String> getLexemes() {
        return lexemes;
    }

    public void setLexemes(ArrayList<String> lexemes) {
        this.lexemes = lexemes;
    }

    public ArrayList<LexemeInf> getOperands() {
        return operands;
    }

    public void setOperands(ArrayList<LexemeInf> operands) {
        this.operands = operands;
    }

    public ArrayList<LexemeInf> getOperators() {
        return operators;
    }

    public void setOperators(ArrayList<LexemeInf> operators) {
        this.operators = operators;
    }

}
