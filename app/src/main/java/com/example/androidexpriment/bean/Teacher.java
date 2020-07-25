package com.example.androidexpriment.bean;

public class Teacher {
    private int tea_id;
    private String tea_username;
    private String tea_name;
    private String tea_password;
    private int tea_makePaperNum;

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public String getTea_username() {
        return tea_username;
    }

    public void setTea_username(String tea_username) {
        this.tea_username = tea_username;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public String getTea_password() {
        return tea_password;
    }

    public void setTea_password(String tea_password) {
        this.tea_password = tea_password;
    }

    public int getTea_makePaperNum() {
        return tea_makePaperNum;
    }

    public void setTea_makePaperNum(int tea_makePaperNum) {
        this.tea_makePaperNum = tea_makePaperNum;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tea_id=" + tea_id +
                ", tea_username='" + tea_username + '\'' +
                ", tea_name='" + tea_name + '\'' +
                ", tea_password='" + tea_password + '\'' +
                ", tea_makePaperNum=" + tea_makePaperNum +
                '}';
    }
}
