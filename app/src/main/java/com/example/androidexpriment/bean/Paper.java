package com.example.androidexpriment.bean;

import java.util.ArrayList;
import java.util.Date;

public class Paper {
    private int paper_id;
    private String paper_time;
    private int tea_id;
    private String tea_name;
    private ArrayList<Item> itemList;

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_time() {
        return paper_time;
    }

    public void setPaper_time(String paper_time) {
        this.paper_time = paper_time;
    }

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paper_id=" + paper_id +
                ", paper_time='" + paper_time + '\'' +
                ", tea_id=" + tea_id +
                ", tea_name='" + tea_name + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
