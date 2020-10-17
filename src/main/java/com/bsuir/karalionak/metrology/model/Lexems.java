package com.bsuir.karalionak.metrology.model;

import java.util.ArrayList;

public class Lexems {
    private ArrayList<String> lexems;
    private ArrayList<LexemInf> operands;
    private ArrayList<LexemInf> operators;

    public Lexems(){
        lexems = new ArrayList<>();
        operands = new ArrayList<>();
        operators = new ArrayList<>();
    }

    public ArrayList<String> getLexems() {
        return lexems;
    }

    public ArrayList<LexemInf> getOperands() {
        return operands;
    }

    public ArrayList<LexemInf> getOperators() {
        return operators;
    }

    public void setLexems(ArrayList<String> lexems) {
        this.lexems = lexems;
    }

    public void setOperands(ArrayList<LexemInf> operands) {
        this.operands = operands;
    }

    public void setOperators(ArrayList<LexemInf> operators) {
        this.operators = operators;
    }

    public int getMaxSize (){
        return Math.max(operands.size(), operators.size());
    }
}
