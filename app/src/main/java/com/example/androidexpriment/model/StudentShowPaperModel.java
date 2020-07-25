package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.db.DataBaseHelper;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentShowPaperModel {

    private String TAG = "StudentShowPaperModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public StudentShowPaperModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo findAllPaper() {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select paper_id,paper_time,paper.tea_id as paper_tea_id,tea_name from paper " +
                "inner join teacher on paper.tea_id=teacher.tea_id";
        Cursor cursor = db.rawQuery(sql, null);
        int count = 0;
        ArrayList<Paper> paperList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index_paper_id = cursor.getColumnIndex("paper_id");
            int index_paper_time = cursor.getColumnIndex("paper_time");
            int index_tea_name = cursor.getColumnIndex("tea_name");
            int index_tea_id = cursor.getColumnIndex("paper_tea_id");
            int paper_id = cursor.getInt(index_paper_id);
            String paper_time = cursor.getString(index_paper_time);
            String tea_name = cursor.getString(index_tea_name);
            int tea_id = cursor.getInt(index_tea_id);
            Paper paper = new Paper();
            paper.setTea_id(tea_id);
            paper.setPaper_id(paper_id);
            paper.setPaper_time(paper_time);
            paper.setTea_name(tea_name);
            paperList.add(paper);
            count++;
        }
        if(count != 0){
            resultInfo.setFlag(true);
            resultInfo.setData(paperList);
        }
        else{
            resultInfo.setFlag(false);
        }
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

    public ResultInfo findStudentPaper(int id) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select paper_id from examRecord where stu_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        ArrayList<Integer> studentPapers = new ArrayList<>();
        while(cursor.moveToNext()){
            int index_paper_id = cursor.getColumnIndex("paper_id");
            int paper_id = cursor.getInt(index_paper_id);
            studentPapers.add(paper_id);
        }
        cursor.close();
        resultInfo.setFlag(true);
        resultInfo.setData(studentPapers);
        return resultInfo;
    }
}
