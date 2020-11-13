package com.bsuir.karalionak.metrology.model;

public class Variable {
    private boolean isP;
    private boolean isM;
    private boolean isC;
    private boolean isT;
    private String value;

    public boolean isP() {
        return isP;
    }

    public void setP(boolean p) {
        isP = p;
    }

    public boolean isM() {
        return isM;
    }

    public void setM(boolean m) {
        isM = m;
    }

    public boolean isC() {
        return isC;
    }

    public void setC(boolean c) {
        isC = c;
    }

    public boolean isT() {
        return isT;
    }

    public void setT(boolean t) {
        isT = t;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
