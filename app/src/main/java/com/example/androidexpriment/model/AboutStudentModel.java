package com.example.androidexpriment.model;

import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.db.DataBaseHelper;

public class AboutStudentModel {
    private static final String TAG = "AboutStudentModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public AboutStudentModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }


    public ResultInfo findStudentById(int id) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from student where stu_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            resultInfo.setFlag(true);
            int index_name = cursor.getColumnIndex("stu_name");
            int index_username = cursor.getColumnIndex("stu_username");
            int index_writePaperNum = cursor.getColumnIndex("stu_writePaperNum");
            String name = cursor.getString(index_name);
            String username = cursor.getString(index_username);
            int writePageNum = cursor.getInt(index_writePaperNum);
            Student student = new Student();
            student.setStu_name(name);
            student.setStu_username(username);
            student.setStu_writePaperNum(writePageNum);
            resultInfo.setData(student);
        }
        else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("查找用户信息失败");
        }
        cursor.close();
        db.close();
        return resultInfo;
    }
}
