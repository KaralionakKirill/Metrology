package com.bsuir.karalionak.metrology.service;

public class Dictionary {

    public final String[] PY_IDENTIFIER = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d",
            "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "_", "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9"
    };

    public final String[] PY_KEYWORDS = new String[]{
            "False", "None", "True", "and", "as", "assert", "break", "class", "continue",
            "def", "del", "except", "finally", "for", "from", "global", "if", "import",
            "in", "is", "lambda", "nonlocal", "not", "or", "pass", "raise", "return",
            "try", "while", "with", "yield"
    };

    private final String[] PY_STATEMENTS = new String[]{
            "+", "-", "*", "**", "/", "//", "%", "@", "<<", ">>", "&", "|", "^", "~", "<",
            ">", "<=", ">=", "==", "!=", "+=", "-=", "=", "*=", "/=", "%=", "**=", "//=", "."
    };

    public boolean lexemInPyStatements(String lexem) {
        if(lexem.equals(" ")){
            return false;
        }
        boolean isLocatedInStatements = false;
        int i = 0;
        while (!isLocatedInStatements && i < PY_STATEMENTS.length) {
            if (lexem.equals(PY_STATEMENTS[i])) {
                isLocatedInStatements = true;
            }
            i++;
        }
        return isLocatedInStatements;
    }

    public boolean lexemInPyKeywords(String lexem) {
        if(lexem.equals(" ")){
            return false;
        }
        boolean isLocatedInKeywords = false;
        int i = 0;
        while (!isLocatedInKeywords && i < PY_KEYWORDS.length) {
            if (lexem.equals(PY_KEYWORDS[i])) {
                isLocatedInKeywords = true;
            }
            i++;
        }
        return isLocatedInKeywords;
    }

    public boolean lexemInPyIdentifier(String str) {
        if(str.equals(" ")){
            return false;
        }
        boolean isLocatedInIdentifier = false;
        int i = 0;
        while (!isLocatedInIdentifier && i < PY_IDENTIFIER.length) {
            if (str.equals(PY_IDENTIFIER[i])) {
                isLocatedInIdentifier = true;
            }
            i++;
        }
        return isLocatedInIdentifier;
    }

}