package com.bsuir.karalionak.metrology.service;

import com.bsuir.karalionak.metrology.model.LexemeInf;

import java.util.ArrayList;

public class Lexer {
    private final Dictionary dictionary = new Dictionary();

    public ArrayList<LexemeInf> getLexemeInfo(ArrayList<String> arrOfLexemes) {
        ArrayList<LexemeInf> list = new ArrayList<>();
        int index;
        for (String arrOfLexeme : arrOfLexemes) {
            index = lexemeInList(list, arrOfLexeme);
            LexemeInf lexemeInf = new LexemeInf();
            if (index == -1) {
                lexemeInf.setName(arrOfLexeme);
                lexemeInf.setCount(1);
                list.add(lexemeInf);
            } else {
                list.get(index).incCount();
            }
        }
        return list;
    }

    public void lexAlloc(ArrayList<String> operands, ArrayList<String> operators, ArrayList<String> arrOfLexemes) {
        for (String arrOfLexeme : arrOfLexemes) {
            if ((dictionary.lexemeInPyStatements(arrOfLexeme) || (dictionary.lexemeInPyKeywords(arrOfLexeme)))) {
                operators.add(arrOfLexeme);
            } else if (arrOfLexeme.contains("()")) {
                operators.add(arrOfLexeme);
            } else {
                operands.add(arrOfLexeme);
            }
        }
    }

    public ArrayList<String> lexemesFromLine(String str, ArrayList<String> result) {
        int lexemePos = 0;
        int i;
        String lexem;
        while (lexemePos < str.length()) {
            while (str.charAt(lexemePos) == ' ') {
                lexemePos++;
            }

            if (str.charAt(lexemePos) == '#') {
                break;
            }

            i = lexemePos;
            while (dictionary.lexemeInPyIdentifier(Character.toString(str.charAt(i)))) {
                i++;
            }

            if (str.matches("[1-9?]]") && (str.charAt(i) == '.')) {
                i++;
                while (dictionary.lexemeInPyIdentifier(Character.toString(str.charAt(i)))) {
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

            if (i == lexemePos) {
                while (dictionary.lexemeInPyStatements(Character.toString(str.charAt(i)))) {
                    i++;
                }
            }

            lexem = str.substring(lexemePos, i);
            if ((str.charAt(i) == '(') && (!dictionary.lexemeInPyStatements(lexem))) {
                lexem += "()";
                i++;
            }
            if (str.charAt(i) == ')') {
                i++;
            }
            if (!lexem.equals("")) {
                result.add(lexem);
            }
            if (lexemePos < i) {
                lexemePos = i;
            } else {
                lexemePos = i + 1;
            }
        }
        return result;
    }

    public int lexemeInList(ArrayList<LexemeInf> list, String lexeme) {
        int result = -1;
        int i = 0;
        while (i < list.size()) {
            if (lexeme.equals(list.get(i).getName())) {
                result = i;
                return result;
            }
            i++;
        }
        return result;
    }
}
