package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.bean.Teacher;
import com.example.androidexpriment.db.DataBaseHelper;

public class TeacherLoginModel {

    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public TeacherLoginModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo teaLogin(String username, String password) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from teacher where tea_username=? and tea_password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if(cursor.moveToNext()){
            resultInfo.setFlag(true);
            resultInfo.setMsg("登录成功");
            System.out.println("登录成功");
            int index_id = cursor.getColumnIndex("tea_id");
            int id = cursor.getInt(index_id);
            Teacher teacher = new Teacher();
            teacher.setTea_id(id);
            resultInfo.setData(teacher);
        }
        else{
            resultInfo.setFlag(false);
            resultInfo.setMsg("登录失败，请检查帐号和密码");
            System.out.println("登陆失败");
        }
        db.close();
        return resultInfo;
    }
}
