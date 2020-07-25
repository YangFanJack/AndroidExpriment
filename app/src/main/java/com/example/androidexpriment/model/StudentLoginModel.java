package com.example.androidexpriment.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.db.DataBaseHelper;

public class StudentLoginModel {
    private static final String TAG = "StudentLoginModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public StudentLoginModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo stuLogin(String username,String password){
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from student where stu_username=? and stu_password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if(cursor.moveToNext()){
            resultInfo.setFlag(true);
            resultInfo.setMsg("登录成功");
            int index_id = cursor.getColumnIndex("stu_id");
            int id = cursor.getInt(index_id);
            Student student = new Student();
            student.setStu_id(id);
            resultInfo.setData(student);
        }
        else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("登录失败，请检查帐号和密码");
            System.out.println("登陆失败");
        }
        cursor.close();
        db.close();
        return resultInfo;
    }
}
