package com.example.dbtry1;

public class Student {
    // field
    private int studentID;
    private String studentName;
    // constructor
    public Student() {}
    public Student(int id, String sname){
        this.studentID = id;
        this.studentName = sname;
    }
    // properties
    public void setID(int id){ this.studentID = id; }
    public int getID(){ return this.studentID; }
    public void setStudentName(String sname){ this.studentName = sname; }
    public String getStudentName(){ return this.studentName; }
}
