package com.example.androidexpriment.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.db.DataBaseHelper;

import java.util.ArrayList;

public class TeacherMakePaperModel {

    private String TAG = "TeacherMakePaperModel";
    private DataBaseHelper helper;
    private SQLiteDatabase db;

    public TeacherMakePaperModel(Context context){
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    public ResultInfo submitPaper(Paper paper) {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        try {
            String sql = "update teacher set tea_makePaperNum=tea_makePaperNum+1 where tea_id=?";
            String sql2 = "insert into paper(paper_time,tea_id) values(?,?)";
            db.execSQL(sql,new String[]{String.valueOf(paper.getTea_id())});
            db.execSQL(sql2,new String[]{paper.getPaper_time(),String.valueOf(paper.getTea_id())});
            String sql4 = "select max(paper_id) from paper";
            Cursor cursor = db.rawQuery(sql4,null);
            cursor.moveToNext();
            int index_paper_id = cursor.getColumnIndex("max(paper_id)");
            int paper_id = cursor.getInt(index_paper_id);
            for (Item i : paper.getItemList()) {
                String sql3 = "insert into middle(paper_id,item_id) values(?,?)";
                db.execSQL(sql3,new String[]{String.valueOf(paper_id),String.valueOf(i.getItem_id())});
            }
            resultInfo.setFlag(true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            resultInfo.setFlag(false);
        }
        db.close();
        return resultInfo;
    }

    public ResultInfo findAllItem() {
        ResultInfo resultInfo = new ResultInfo();
        //从数据库获取数据
        String sql = "select * from item ";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Item> itemList = new ArrayList<>();
        while(cursor.moveToNext()){
            int index_item_id = cursor.getColumnIndex("item_id");
            int index_item_content = cursor.getColumnIndex("item_content");
            int index_item_answer = cursor.getColumnIndex("item_answer");
            int item_id = cursor.getInt(index_item_id);
            String item_content = cursor.getString(index_item_content);
            String item_answer = cursor.getString(index_item_answer);
            Item item = new Item();
            item.setItem_id(item_id);
            item.setItem_content(item_content);
            item.setItem_answer(item_answer);
            itemList.add(item);
        }
        if(itemList.size()==0){
            resultInfo.setFlag(false);
        }
        else{
            resultInfo.setFlag(true);
            resultInfo.setData(itemList);
        }

        cursor.close();
        db.close();
        return resultInfo;
    }
}
