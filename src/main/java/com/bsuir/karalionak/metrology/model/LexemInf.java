package com.bsuir.karalionak.metrology.model;

public class LexemInf {
    private String name;
    private int count;

    @Override
    public String toString() {
        return "com.korolenock.metra.model.TLexemInf{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void incCount() {
        count++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
