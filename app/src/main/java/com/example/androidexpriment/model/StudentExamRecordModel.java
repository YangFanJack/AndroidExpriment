package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ExamRecord;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.db.DataBaseHelper;

import java.util.ArrayList;

public class StudentExamRecordModel {

    private String TAG = "StudentExamRecordModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public StudentExamRecordModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo findExamRecordById(int id) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from examRecord where stu_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        int count = 0;
        ArrayList<ExamRecord> examRecordList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index_paper_id = cursor.getColumnIndex("paper_id");
            int index_paper_grade = cursor.getColumnIndex("paper_grade");
            int index_paper_write_time = cursor.getColumnIndex("paper_write_time");
            int paper_id = cursor.getInt(index_paper_id);
            int paper_grade = cursor.getInt(index_paper_grade);
            String paper_write_time = cursor.getString(index_paper_write_time);
            ExamRecord examRecord = new ExamRecord();
            examRecord.setStu_id(id);
            examRecord.setPaper_id(paper_id);
            examRecord.setPaper_grade(paper_grade);
            examRecord.setPaper_write_time(paper_write_time);
            examRecordList.add(examRecord);
            count++;
        }
        if(count != 0){
            resultInfo.setFlag(true);
            resultInfo.setData(examRecordList);
        }
        else{
            resultInfo.setFlag(false);
        }
        cursor.close();
        db.close();
//        ArrayList<ExamRecord> examRecordList = new ArrayList<>();
//        for(int i=0;i<10;i++){
//            ExamRecord examRecord = new ExamRecord();
//            examRecord.setPaper_id(2);
//            examRecord.setPaper_grade(10);
//            examRecord.setPaper_write_time("2020-01-01");
//            examRecordList.add(examRecord);
//        }
//        resultInfo.setFlag(true);
//        resultInfo.setData(examRecordList);
        return resultInfo;
    }
}
