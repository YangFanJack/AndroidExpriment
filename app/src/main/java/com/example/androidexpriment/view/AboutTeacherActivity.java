package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidexpriment.MainActivity;
import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.bean.Teacher;
import com.example.androidexpriment.presenter.AboutStudentPresenter;
import com.example.androidexpriment.presenter.AboutTeacherPresenter;

public class AboutTeacherActivity extends AppCompatActivity implements AboutTeacherView{

    private static final String TAG = "AboutTeacherActivity";
    private TextView tea_info_name;
    private TextView tea_info_username;
    private TextView tea_info_makePageNum;
    int id;
    SharedPreferences tea_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_teacher);
        //读取老师id
        tea_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        id = tea_info.getInt("id", 0);
        AboutTeacherPresenter aboutTeacherPresenter = new AboutTeacherPresenter(this,this);
        aboutTeacherPresenter.findTeacherById(id);
    }

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

    /**
     * 结果包含Boolean，Teacher
     *
     * @param resultInfo
     */
    @Override
    public void onFindTeacherByIdResult(ResultInfo resultInfo) {
        if(resultInfo.getFlag()){
            Teacher teacher = (Teacher) resultInfo.getData();
            System.out.println("+++++++++++++++++++++++"+teacher);
            tea_info_name = findViewById(R.id.tea_info_name);
            tea_info_username = findViewById(R.id.tea_info_username);
            tea_info_makePageNum = findViewById(R.id.tea_info_makePaperNum);
            tea_info_name.setText(teacher.getTea_name());
            tea_info_username.setText(teacher.getTea_username());
            tea_info_makePageNum.setText(String.valueOf(teacher.getTea_makePaperNum()));
        }
        else{
            Toast.makeText(this,"发生错误，老师信息获取失败。",Toast.LENGTH_LONG);
        }
    }

    public void clickExitLogin(View view) {
        //清空sharePregerence
        tea_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = tea_info.edit();
        edit.putInt("id",0);
        edit.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}