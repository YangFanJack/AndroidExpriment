package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ExamRecord;
import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.db.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentWritePaperModel {

    private String TAG = "StudentWritePaperModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public StudentWritePaperModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo findChoosePaperById(int id) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select item.item_id as item_item_id from item " +
                "inner join middle on middle.item_id=item.item_id " +
                "inner join paper on paper.paper_id =? and paper.paper_id = middle.paper_id";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        ArrayList<Item> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index_item_id = cursor.getColumnIndex("item_item_id");
            int item_id = cursor.getInt(index_item_id);
            String sql2 = "select * from item where item_id = ?";
            Cursor cursor2 = db.rawQuery(sql2, new String[]{String.valueOf(item_id)});
            cursor2.moveToNext();
            Item item = new Item();
            int index_item_content = cursor2.getColumnIndex("item_content");
            int index_item_answer = cursor2.getColumnIndex("item_answer");
            String item_content = cursor2.getString(index_item_content);
            String item_answer = cursor2.getString(index_item_answer);
            item.setItem_id(item_id);
            item.setItem_content(item_content);
            item.setItem_answer(item_answer);
            itemList.add(item);
        }
        Paper paper = new Paper();
        paper.setPaper_id(id);
        paper.setItemList(itemList);
        resultInfo.setFlag(true);
        resultInfo.setData(paper);
        cursor.close();
        db.close();
//        ArrayList<Paper> paperList = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            Paper paper = new Paper();
//            paper.setPaper_id(2);
//            paper.setPaper_time("2020-01-01");
//            paper.setTea_name("何大英");
//            paperList.add(paper);
//        }
//        resultInfo.setFlag(true);
//        resultInfo.setData(paperList);
        return resultInfo;
    }

    public ResultInfo submitPaper(ExamRecord examRecord) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "update student set stu_writePaperNum=stu_writePaperNum+1 where stu_id=?";
        String sql2 = "insert into examRecord(paper_id,stu_id,paper_grade,paper_write_time) values(?,?,?,?)";
//        try {
            db.execSQL(sql, new String[]{String.valueOf(examRecord.getStu_id())});
            int paper_id = examRecord.getPaper_id();
            int stu_id = examRecord.getStu_id();
            int paper_grade = examRecord.getPaper_grade();
            String paper_write_time = examRecord.getPaper_write_time();
            db.execSQL(sql2,new String[]{String.valueOf(paper_id),String.valueOf(stu_id),String.valueOf(paper_grade),paper_write_time});
            resultInfo.setFlag(true);
//        }
//        catch (Exception e){
//            resultInfo.setFlag(false);
//        }
        db.close();
        return resultInfo;
    }
}
