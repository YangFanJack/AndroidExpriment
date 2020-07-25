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
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.adapters.StudentExamRecordAdapter;
import com.example.androidexpriment.adapters.StudentShowPaperAdapter;
import com.example.androidexpriment.bean.ExamRecord;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentExamRecordPresenter;
import com.example.androidexpriment.presenter.StudentShowPaperPresenter;

import java.util.ArrayList;

public class StudentExamRecordActivity extends AppCompatActivity implements StudentExamRecordView{

    private String TAG = "StudentExamRecordActivity";
    private RecyclerView stu_examRecord_view;
    int id;
    SharedPreferences stu_info;

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
        setContentView(R.layout.activity_student_exam_record);
        //读取学生id
        stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        id = stu_info.getInt("id", 0);
        StudentExamRecordPresenter studentExamRecordPresenter = new StudentExamRecordPresenter(this,this);
        studentExamRecordPresenter.findExamRecordById(id);
    }

    /**
     * 结果包含Boolean，ArrayList<ExamRecord>
     *
     * @param resultInfo
     */
    @Override
    public void onFindExamRecordByIdResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        //找到recyclerview控件
        stu_examRecord_view = (RecyclerView)findViewById(R.id.stu_examRecord_view);
        //准备数据
        ArrayList<ExamRecord> examRecordList = (ArrayList<ExamRecord>) resultInfo.getData();
        //RecyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stu_examRecord_view.setLayoutManager(linearLayoutManager);
        //创建适配器
        StudentExamRecordAdapter studentExamRecordAdapter = new StudentExamRecordAdapter(examRecordList);
        //设置到RecyclerView里去
        stu_examRecord_view.setAdapter(studentExamRecordAdapter);
    }
}