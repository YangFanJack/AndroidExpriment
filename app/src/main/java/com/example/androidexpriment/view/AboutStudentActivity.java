package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidexpriment.MainActivity;
import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.presenter.AboutStudentPresenter;
import com.example.androidexpriment.presenter.StudentLoginPresenter;

public class AboutStudentActivity extends AppCompatActivity implements AboutStudentView{

    private static final String TAG = "AboutStudentActivity";
    private TextView stu_info_name;
    private TextView stu_info_username;
    private TextView stu_info_writePaperNum;
    int id;
    SharedPreferences stu_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_student);
        //读取学生id
        stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        id = stu_info.getInt("id", 0);
        AboutStudentPresenter aboutStudentPresenter = new AboutStudentPresenter(this,this);
        aboutStudentPresenter.findStudentById(id);
    }

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

    /**
     * 结果包含Boolean和Student
     *
     * @param resultInfo
     */
    @Override
    public void onFindStudentByIdResult(ResultInfo resultInfo) {
        if(resultInfo.getFlag()){
            Student student = (Student) resultInfo.getData();
            stu_info_name = findViewById(R.id.stu_info_name);
            stu_info_username = findViewById(R.id.stu_info_username);
            stu_info_writePaperNum = findViewById(R.id.stu_info_writePaperNum);
            stu_info_name.setText(student.getStu_name());
            stu_info_username.setText(student.getStu_username());
            stu_info_writePaperNum.setText(String.valueOf(student.getStu_writePaperNum()));
        }
        else{
            Toast.makeText(this,"发生错误，学生信息获取失败。",Toast.LENGTH_LONG);
        }
    }

    public void clickExitLogin(View view) {
        //清空sharePregerence
        stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = stu_info.edit();
        edit.putInt("id",0);
        edit.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}