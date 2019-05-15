package com.plachkovskyy.xmlparser.model;

public class Subject {

    private String title;
    private int value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Subject(String title, int mark) {
        this.title = title;
        this.value = mark;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "title='" + title + '\'' +
                ", value=" + value +
                '}';
    }

}
