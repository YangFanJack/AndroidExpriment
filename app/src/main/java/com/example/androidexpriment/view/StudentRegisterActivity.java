package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.presenter.StudentLoginPresenter;
import com.example.androidexpriment.presenter.StudentRegisterPresenter;

public class StudentRegisterActivity extends AppCompatActivity implements StudentRegisterView{

    private static final String TAG = "StudentRegisterActivity";
    private EditText et_stu_register_username;
    private EditText et_stu_register_password;
    private EditText et_stu_register_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
    }

    /**
     * 结果包含boolean，msg
     *
     * @param resultInfo
     */
    @Override
    public void onStuRegisterResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        if(resultInfo.getFlag()){
            //注册界面打印注册成功提示信息
            TextView tv_register_note = findViewById(R.id.tv_stu_register_note);
            tv_register_note.setText(resultInfo.getMsg());

        }
        else if(!resultInfo.getFlag()){
            //注册界面打印注册失败提示信息
            TextView tv_login_note = findViewById(R.id.tv_stu_register_note);
            tv_login_note.setText(resultInfo.getMsg());
        }
    }

    public void click_registerToLogin(View view) {
        Intent intent = new Intent(this,StudentLoginActivity.class);
        startActivity(intent);
    }

    public void clickStuRegister(View view) {
        et_stu_register_username = findViewById(R.id.et_stu_register_username);
        et_stu_register_password = findViewById(R.id.et_stu_register_password);
        et_stu_register_name = findViewById(R.id.et_stu_register_name);
        String username = et_stu_register_username.getText().toString().trim();
        String password = et_stu_register_password.getText().toString().trim();
        String name = et_stu_register_name.getText().toString().trim();
        StudentRegisterPresenter studentRegisterPresenter = new StudentRegisterPresenter(this,this);
        studentRegisterPresenter.stuRegister(username,password,name);
    }
}