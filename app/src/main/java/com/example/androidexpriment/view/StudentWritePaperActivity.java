package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.adapters.StudentShowPaperAdapter;
import com.example.androidexpriment.adapters.StudentWritePaperAdapter;
import com.example.androidexpriment.bean.ExamRecord;
import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentShowPaperPresenter;
import com.example.androidexpriment.presenter.StudentWritePaperPresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class StudentWritePaperActivity extends AppCompatActivity implements StudentWritePaperView{


    private String TAG = "StudentWritePaperActivity";
    private RecyclerView stu_writePaper_view;
    private int paperId;
    private int stuId;
    private SharedPreferences stu_info;
    HashMap<Integer,String> rightAnswers = new HashMap<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.stu_btn_one:
                Toast.makeText(this,"欢迎来到开始答题界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,StudentShowPaperActivity.class));
                break;
            case R.id.stu_btn_two:
                Toast.makeText(this,"欢迎来到答题记录界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,StudentExamRecordActivity.class));
                break;
            case R.id.stu_btn_three:
                Toast.makeText(this,"欢迎来到我的信息界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,AboutStudentActivity.class));
                break;
            default:
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_write_paper);
        Intent intent = getIntent();
        paperId = intent.getIntExtra("paperId",0);
        StudentWritePaperPresenter studentWritePaperPresenter = new StudentWritePaperPresenter(this,this);
        studentWritePaperPresenter.findChoosedPaperById(paperId);
    }

    /**
     * 返回结果包含Boolean,Paper
     *
     * @param resultInfo
     */
    @Override
    public void onFindChoosedPaperByIdResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        //找到recyclerview控件
        stu_writePaper_view = (RecyclerView)findViewById(R.id.stu_writePaper_view);
        //准备数据
        Paper paper = (Paper) resultInfo.getData();
        ArrayList<Item> list= paper.getItemList();
        for (Item r : list) {
            rightAnswers.put(r.getItem_id(),r.getItem_answer());
        }
        //RecyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stu_writePaper_view.setLayoutManager(linearLayoutManager);
        //创建适配器
        StudentWritePaperAdapter studentWritePaperAdapter = new StudentWritePaperAdapter(paper.getItemList());
        //设置到RecyclerView里去
        stu_writePaper_view.setAdapter(studentWritePaperAdapter);
    }

    /**
     * 返回结果包含Boolean,msg
     *
     * @param resultInfo
     */
    @Override
    public void onSubmitPaperResult(ResultInfo resultInfo) {
        if(resultInfo.getFlag()){
            Toast.makeText(this,"提交试卷成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,StudentShowPaperActivity.class));
        }
        else{
            Toast.makeText(this,"提交失败",Toast.LENGTH_LONG).show();
        }

    }

    public void stu_submitPaper(View view) {
        HashMap<Integer, String> answers = null;
        try {
            FileInputStream fileIn = openFileInput("answers");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            answers = (HashMap<Integer, String>) in.readObject();
            File f=new File(getFilesDir(), "answers");
            f.delete();
        }
        catch(Exception e){
            Toast.makeText(this,"请回答所有问题且保存后再提交答案",Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }

//        for (Integer key : answers.keySet()) {
//            Log.v(TAG, "接收到的Key = " + key);
//            Log.v(TAG, "接收到的value = " + answers.get(key));
//        }

        ExamRecord examRecord = new ExamRecord();
        examRecord.setPaper_id(paperId);
        //读取学生id
        stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        stuId = stu_info.getInt("id", 0);
        examRecord.setStu_id(stuId);
        //获得SimpleDateFormat类，我们转换为yyyy-MM-dd的时间格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String writeTime = sf.format(date);
        examRecord.setPaper_write_time(writeTime);
        int grade = 0;
        for (Integer key : answers.keySet()){
            if(rightAnswers.get(key).equals(answers.get(key))){
                grade+=2;
            }
        }
        examRecord.setPaper_grade(grade);
        StudentWritePaperPresenter studentWritePaperPresenter = new StudentWritePaperPresenter(this,this);
        studentWritePaperPresenter.submitPaper(examRecord);

    }
}