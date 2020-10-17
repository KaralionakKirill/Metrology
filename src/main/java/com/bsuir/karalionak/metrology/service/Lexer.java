package com.bsuir.karalionak.metrology.service;

import com.bsuir.karalionak.metrology.model.LexemInf;

import java.util.ArrayList;

public class Lexer {
    private final Dictionary dictionary = new Dictionary();

    public ArrayList<LexemInf> getLexemInfo(ArrayList<String> arrOfLexems) {
        ArrayList<LexemInf> list = new ArrayList<>();
        int index;
        for (String arrOfLexem : arrOfLexems) {
            index = lexemInList(list, arrOfLexem);
            LexemInf lexemInf = new LexemInf();
            if (index == -1) {
                lexemInf.setName(arrOfLexem);
                lexemInf.setCount(1);
                list.add(lexemInf);
            } else {
                list.get(index).incCount();
            }
        }
        return list;
    }

    public void lexAlloc(ArrayList<String> operands, ArrayList<String> operators, ArrayList<String> arrOfLexems) {
        for (String arrOfLexem : arrOfLexems) {
            if ((dictionary.lexemInPyStatements(arrOfLexem) || (dictionary.lexemInPyKeywords(arrOfLexem)))) {
                operators.add(arrOfLexem);
            } else if (arrOfLexem.contains("()")) {
                operators.add(arrOfLexem);
            } else {
                operands.add(arrOfLexem);
            }
        }
    }

    public ArrayList<String> lexemsFromLine(String str, ArrayList<String> result) {
        int lexemPos = 0;
        int i;
        String lexem;
        while (lexemPos < str.length()) {
            while (str.charAt(lexemPos) == ' ') {
                lexemPos++;
            }

            if (str.charAt(lexemPos) == '#') {
                break;
            }

            i = lexemPos;
            while (dictionary.lexemInPyIdentifier(Character.toString(str.charAt(i)))) {
                i++;
            }

            if (str.matches("[1-9?]]") && (str.charAt(i) == '.')) {
                i++;
                while (dictionary.lexemInPyIdentifier(Character.toString(str.charAt(i)))) {
                    i++;
                }
            }

            if ((str.charAt(i) == '"') || (str.charAt(i) == '\'')) {
                i++;
                while (!((str.charAt(i) == '"') || (str.charAt(i) == '\''))) {
                    i++;
                }
                i++;
            }

            if (i == lexemPos) {
                while (dictionary.lexemInPyStatements(Character.toString(str.charAt(i)))) {
                    i++;
                }
            }

            lexem = str.substring(lexemPos, i);
            if ((str.charAt(i) == '(') && (!dictionary.lexemInPyStatements(lexem))) {
                lexem += "()";
                i++;
            }
            if (str.charAt(i) == ')') {
                i++;
            }
            if (!lexem.equals("")) {
                result.add(lexem);
            }
            if (lexemPos < i) {
                lexemPos = i;
            } else {
                lexemPos = i + 1;
            }
        }
        return result;
    }

    public int lexemInList(ArrayList<LexemInf> list, String lexem) {
        int result = -1;
        int i = 0;
        while (i < list.size()) {
            if (lexem.equals(list.get(i).getName())) {
                result = i;
                return result;
            }
            i++;
        }
        return result;
    }
}
