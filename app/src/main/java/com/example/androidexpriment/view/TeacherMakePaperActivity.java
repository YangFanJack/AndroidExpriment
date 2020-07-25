package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.adapters.StudentWritePaperAdapter;
import com.example.androidexpriment.adapters.TeacherMakePaperAdapter;
import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentWritePaperPresenter;
import com.example.androidexpriment.presenter.TeacherMakePaperPresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TeacherMakePaperActivity extends AppCompatActivity implements TeacherMakePaperView{

    private String TAG = "TeacherMakePaperActivity";
    private RecyclerView tea_makePaper_view;
    int id;
    SharedPreferences tea_info;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.tea_btn_one:
                Toast.makeText(this,"欢迎来到新建试卷界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherMakePaperActivity.class));
                break;
            case R.id.tea_btn_two:
                Toast.makeText(this,"欢迎来到新建题目界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherMakeItemActivity.class));
                break;
            case R.id.tea_btn_three:
                Toast.makeText(this,"欢迎来到查看试卷界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherShowPaperActivity.class));
                break;
            case R.id.tea_btn_four:
                Toast.makeText(this,"欢迎来到我的信息界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,AboutTeacherActivity.class));
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_make_paper);
        TeacherMakePaperPresenter teacherMakePaperPresenter = new TeacherMakePaperPresenter(this,this);
        teacherMakePaperPresenter.findAllItem();
    }

    public void click_tea_makePaper(View view) {

        HashMap<Integer, String[]> itemChoose = null;
        try {
            FileInputStream fileIn = openFileInput("chooseItems");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            itemChoose = (HashMap<Integer, String[]>) in.readObject();
            File f=new File(getFilesDir(), "chooseItems");
            f.delete();
        }
        catch(Exception e){
            Toast.makeText(this,"请选择5道题目且保存后再新建试卷",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }
        ArrayList<Item> itemList = new ArrayList<>();
        for (Integer key : itemChoose.keySet()) {
            Log.v(TAG, "接收到的Key = " + key);
            Log.v(TAG, "接收到的value = " + itemChoose.get(key));
            String content = itemChoose.get(key)[0];
            String answer = itemChoose.get(key)[1];
            Item item = new Item();
            item.setItem_id(key);
            item.setItem_content(content);
            item.setItem_answer(answer);
            itemList.add(item);
        }

        Paper paper = new Paper();
        paper.setItemList(itemList);
        //获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String makeTime = sf.format(date);
        paper.setPaper_time(makeTime);
        //读取老师id
        tea_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        id = tea_info.getInt("id", 0);
        paper.setTea_id(id);
        /**
         * 留到model层去获得教师名字
         */
//        paper.setTea_name();
        TeacherMakePaperPresenter teacherMakePaperPresenter = new TeacherMakePaperPresenter(this,this);
        teacherMakePaperPresenter.submitPaper(paper);
    }

    /**
     * 结果包含Boolean,msg
     *
     * @param resultInfo
     */
    @Override
    public void onSubmitPaperResult(ResultInfo resultInfo) {
        if(resultInfo.getFlag()){
            Toast.makeText(this,"新建试卷成功",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"新建试卷失败",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 结果包含boolean，arraylist
     *
     * @param resultInfo
     */
    @Override
    public void onFindAllItemResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        //找到recyclerview控件
        tea_makePaper_view = (RecyclerView)findViewById(R.id.tea_makePaper_view);
        //准备数据
        ArrayList<Item> itemList= (ArrayList<Item>) resultInfo.getData();
        //RecyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tea_makePaper_view.setLayoutManager(linearLayoutManager);
        //创建适配器
        TeacherMakePaperAdapter teacherMakePaperAdapter = new TeacherMakePaperAdapter(itemList);
        //设置到RecyclerView里去
        tea_makePaper_view.setAdapter(teacherMakePaperAdapter);
        //获取最后一个item的position
        int lastItemPosition = tea_makePaper_view.getLayoutManager().getItemCount();
    }
}