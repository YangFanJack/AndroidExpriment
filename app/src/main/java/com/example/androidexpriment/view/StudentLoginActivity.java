package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.presenter.StudentLoginPresenter;

public class StudentLoginActivity extends AppCompatActivity implements StudentLoginView{

    private static final String TAG = "StudentLoginActivity";
    private EditText et_stu_login_username;
    private EditText et_stu_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
    }

    public void clickStuLogin(View view) {
        et_stu_login_username = findViewById(R.id.et_stu_login_username);
        et_stu_login_password = findViewById(R.id.et_stu_login_password);
        String username = et_stu_login_username.getText().toString().trim();
        String password = et_stu_login_password.getText().toString().trim();
        StudentLoginPresenter studentLoginPresenter = new StudentLoginPresenter(this,this);
        studentLoginPresenter.stuLogin(username,password);
    }

    @Override
    public void onStuLoginResult(ResultInfo resultInfo) {
        //处理前端页面
        Log.d(TAG,"数据库的数据已经传到前端界面");
        if(resultInfo.getFlag()){
            //存id入sharepreference
            SharedPreferences stu_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
            SharedPreferences.Editor edit = stu_info.edit();
            Student student = (Student) resultInfo.getData();
            edit.putInt("id",student.getStu_id());
            edit.commit();
            //跳转到菜单界面
            Intent intent = new Intent(this,AboutStudentActivity.class);
            startActivity(intent);

        }
        else if(!resultInfo.getFlag()){
            //登陆界面打印登陆失败提示信息
            TextView tv_login_note = findViewById(R.id.tv_stu_login_note);
            tv_login_note.setText(resultInfo.getMsg());
        }
    }

    public void click_loginToRegister(View view) {
        Intent intent = new Intent(this,StudentRegisterActivity.class);
        startActivity(intent);
    }
}