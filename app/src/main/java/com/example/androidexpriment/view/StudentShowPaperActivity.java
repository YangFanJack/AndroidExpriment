package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.adapters.StudentShowPaperAdapter;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentShowPaperPresenter;
import com.example.androidexpriment.presenter.TeacherMakeItemPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentShowPaperActivity extends AppCompatActivity implements StudentShowPaperView{

    private String TAG = "StudentShowPaperActivity";
    private RecyclerView stu_showPaper_view;
    private int stuId;
    private SharedPreferences stu_info;
    ArrayList<Integer> studentPapers = null;

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
        setContentView(R.layout.activity_student_show_paper);
        StudentShowPaperPresenter studentShowPaperPresenter = new StudentShowPaperPresenter(this,this);
        //读取学生id
        stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        stuId = stu_info.getInt("id", 0);
        studentShowPaperPresenter.findStudentPaper(stuId);
        studentShowPaperPresenter.findAllPaper();


    }

    /**
     * 结果包含Boolean,ArrayList<Paper>
     *
     * @param resultInfo
     */
    @Override
    public void onFindAllPaperResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        //找到recyclerview控件
        stu_showPaper_view = (RecyclerView)findViewById(R.id.stu_showPaper_view);
        //准备数据
        ArrayList<Paper> paperList = (ArrayList<Paper>) resultInfo.getData();
        //RecyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        stu_showPaper_view.setLayoutManager(linearLayoutManager);
        //创建适配器
        StudentShowPaperAdapter studentShowPaperAdapter = new StudentShowPaperAdapter(paperList,studentPapers);
        //设置到RecyclerView里去
        stu_showPaper_view.setAdapter(studentShowPaperAdapter);

        HashMap<Integer, String> answers = (HashMap<Integer, String>) getIntent().getSerializableExtra("answers");
        if(answers!=null){
            for (Integer key : answers.keySet()) {
                Log.v("wjw","接收到的Key = " + key);
                Log.v("wjw","接收到的value = " + answers.get(key));
                if(answers.get(key).equals("")){
                }
            }
        }
    }

    /**
     * 结果包含arrayList<int>,boolean
     *
     * @param resultInfo
     */
    @Override
    public void onFindStudentPaperResult(ResultInfo resultInfo) {
        studentPapers = (ArrayList<Integer>) resultInfo.getData();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(studentPapers);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
    }

}