package com.example.androidexpriment.bean;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class ExamRecord {
    private int paper_id;
    private int stu_id;
    private int paper_grade;
    private String paper_write_time;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getPaper_grade() {
        return paper_grade;
    }

    public void setPaper_grade(int paper_grade) {
        this.paper_grade = paper_grade;
    }

    public String getPaper_write_time() {
        return paper_write_time;
    }

    public void setPaper_write_time(String paper_write_time) {
        this.paper_write_time = paper_write_time;
    }

    @Override
    public String toString() {
        return "ExamRecord{" +
                "paper_id=" + paper_id +
                ", stu_id=" + stu_id +
                ", paper_grade=" + paper_grade +
                ", paper_write_time='" + paper_write_time + '\'' +
                '}';
    }
}
