package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.db.DataBaseHelper;

public class TeacherMakeItemModel {

    private static final String TAG = "TeacherMakeItemModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public TeacherMakeItemModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo submitItem(Item item) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "insert into item (item_content,item_answer) values(?,?)";
        try {
            db.execSQL(sql, new String[]{item.getItem_content(),item.getItem_answer()});
            resultInfo.setFlag(true);
        }
        catch(Exception e){
            resultInfo.setFlag(false);
        }
        db.close();
        return resultInfo;
    }
}
