package com.bsuir.karalionak.metrology.model;

public class LexemeInf {
    private String name;
    private int count;

    @Override
    public String toString() {
        return "{" +
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
