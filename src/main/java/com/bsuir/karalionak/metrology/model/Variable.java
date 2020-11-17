package com.bsuir.karalionak.metrology.model;

import java.util.Objects;

public class Variable {
    private String value;
    private int count = 0;
    private boolean isP = false;
    private boolean isM = false;
    private boolean isT = false;
    private boolean isC = false;

    public Variable(String value) {
        this.value = value;
    }

    public void setFunctionGroup(String nameGroup) {
        switch (nameGroup) {
            case "P":
                isP = true;
                break;
            case "M":
                isM = true;
                break;
            case "C":
                isC = true;
                break;
            case "T":
                isT = true;
                break;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        this.count++;
    }

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

    public boolean isT() {
        return isT;
    }

    public void setT(boolean t) {
        isT = t;
    }

    public boolean isC() {
        return isC;
    }

    public void setC(boolean c) {
        isC = c;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "value='" + value + '\'' +
                ", count=" + count +
                ", isP=" + isP +
                ", isM=" + isM +
                ", isT=" + isT +
                ", isC=" + isC +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return value.equals(variable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
