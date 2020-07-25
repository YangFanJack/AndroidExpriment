package com.example.androidexpriment.bean;

public class Student {
    private int stu_id;
    private String stu_username;
    private String stu_name;
    private String stu_password;
    private int stu_writePaperNum;

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_username() {
        return stu_username;
    }

    public void setStu_username(String stu_username) {
        this.stu_username = stu_username;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public int getStu_writePaperNum() {
        return stu_writePaperNum;
    }

    public void setStu_writePaperNum(int stu_writePaperNum) {
        this.stu_writePaperNum = stu_writePaperNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                ", stu_username='" + stu_username + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_password='" + stu_password + '\'' +
                ", stu_writePaperNum=" + stu_writePaperNum +
                '}';
    }
}
