package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.adapters.StudentShowPaperAdapter;
import com.example.androidexpriment.adapters.TeacherShowPaperAdapter;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentShowPaperPresenter;
import com.example.androidexpriment.presenter.TeacherShowPaperPresenter;

import java.util.ArrayList;

public class TeacherShowPaperActivity extends AppCompatActivity implements TeacherShowPaperView{

    private String TAG = "TeacherShowPaperActivity";
    private RecyclerView tea_showPaper_view;

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
        setContentView(R.layout.activity_teacher_show_paper);
        TeacherShowPaperPresenter teacherShowPaperPresenter = new TeacherShowPaperPresenter(this,this);
        teacherShowPaperPresenter.findAllPaper();
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
        tea_showPaper_view = (RecyclerView)findViewById(R.id.tea_showPaper_view);
        //准备数据
        ArrayList<Paper> paperList = (ArrayList<Paper>) resultInfo.getData();
        //RecyclerView设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tea_showPaper_view.setLayoutManager(linearLayoutManager);
        //创建适配器
        TeacherShowPaperAdapter teacherShowPaperAdapter = new TeacherShowPaperAdapter(paperList);
        //设置到RecyclerView里去
        tea_showPaper_view.setAdapter(teacherShowPaperAdapter);
    }
}