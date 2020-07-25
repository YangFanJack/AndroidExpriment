package com.example.androidexpriment.bean;

public class Item {
    private int item_id;
    private String item_content;
    private String item_answer;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_content() {
        return item_content;
    }

    public void setItem_content(String item_content) {
        this.item_content = item_content;
    }

    public String getItem_answer() {
        return item_answer;
    }

    public void setItem_answer(String item_answer) {
        this.item_answer = item_answer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_content='" + item_content + '\'' +
                ", item_answer='" + item_answer + '\'' +
                '}';
    }
}
