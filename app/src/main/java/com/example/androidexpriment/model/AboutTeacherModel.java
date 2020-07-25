package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.bean.Teacher;
import com.example.androidexpriment.db.DataBaseHelper;

public class AboutTeacherModel {

    private static final String TAG = "AboutTeacherModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public AboutTeacherModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo findTeacherById(int id) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from teacher where tea_id=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            resultInfo.setFlag(true);
            int index_name = cursor.getColumnIndex("tea_name");
            int index_username = cursor.getColumnIndex("tea_username");
            int index_makePaperNum = cursor.getColumnIndex("tea_makePaperNum");
            String name = cursor.getString(index_name);
            String username = cursor.getString(index_username);
            int writePageNum = cursor.getInt(index_makePaperNum);
            Teacher teacher = new Teacher();
            teacher.setTea_name(name);
            teacher.setTea_username(username);
            teacher.setTea_makePaperNum(writePageNum);
            resultInfo.setData(teacher);
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
