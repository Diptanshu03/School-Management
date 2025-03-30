package com.Entity;

public class Student {
    private int sid;
    private String sname;
    private String age;
    private String phno;
    private String gender;

    // Default constructor
    public Student() {}

    // Parameterized constructor
    public Student(int sid, String sname, String age, String phno, String gender) {
        this.sid = sid;
        this.sname = sname;
        this.age = age;
        this.phno = phno;
        this.gender = gender;
    }

    // Getters
    public int getId() {
        return sid;
    }

    public String getName() {
        return sname;
    }

    public String getAge() {
        return age;
    }

    public String getMobile() {
        return phno;
    }

    public String getGender() {
        return gender;
    }
}
