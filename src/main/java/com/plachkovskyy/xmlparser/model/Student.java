package com.plachkovskyy.xmlparser.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String firstname;
    private String lastname;
    private String groupnumber;
    private double average;
    private List<Subject> subjectListList;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGroupnumber() {
        return groupnumber;
    }

    public void setGroupnumber(String groupnumber) {
        this.groupnumber = groupnumber;
    }

    public List<Subject> getSubjectListList() {
        return subjectListList;
    }

    public void setSubjectListList(List<Subject> subjectListList) {
        this.subjectListList = subjectListList;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Student(String firstname, String lastname, String groupnumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.groupnumber = groupnumber;
        this.subjectListList = new ArrayList<Subject>();
    }

    public double averageCalculate() {
        double avr = 0d;
        for (Subject subject: getSubjectListList()) {
            avr += subject.getValue();
        }
        avr = Math.round (100.0 * avr / getSubjectListList().size()) / 100.0;
        return avr;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", groupnumber=" + groupnumber +
                ", subjectListList=" + subjectListList +
                ", average=" + average +
                '}';
    }

}


