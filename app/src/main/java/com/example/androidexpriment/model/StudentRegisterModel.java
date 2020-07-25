package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.db.DataBaseHelper;

public class StudentRegisterModel {

    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public StudentRegisterModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo stuRegister(String username, String password, String name) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "insert into student (stu_username,stu_name,stu_password,stu_writePaperNum)" +
                "values(?,?,?,0)";
        try {
            db.execSQL(sql, new String[]{username,name,password});
            resultInfo.setFlag(true);
            resultInfo.setMsg("注册成功");
        }
        catch (Exception e){
            resultInfo.setFlag(false);
            resultInfo.setMsg("注册失败，用户名重复");
        }
        db.close();
        return resultInfo;
    }
}
