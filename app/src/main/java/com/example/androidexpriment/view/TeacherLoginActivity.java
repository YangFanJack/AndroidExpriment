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
import com.example.androidexpriment.bean.Teacher;
import com.example.androidexpriment.presenter.StudentLoginPresenter;
import com.example.androidexpriment.presenter.TeacherLoginPresenter;

public class TeacherLoginActivity extends AppCompatActivity implements TeacherLoginView{

    private static final String TAG = "TeacherLoginActivity";
    private EditText et_tea_login_username;
    private EditText et_tea_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
    }

    /**
     * 结果包含Boolean,msg
     *
     * @param resultInfo
     */
    @Override
    public void onTeaLoginResult(ResultInfo resultInfo) {
        Log.d(TAG,"数据库的数据已经传到前端界面");
        if(resultInfo.getFlag()){
            //存id入sharepreference
            SharedPreferences tea_info = this.getSharedPreferences("login_info", MODE_PRIVATE);
            SharedPreferences.Editor edit = tea_info.edit();
            Teacher teacher = (Teacher) resultInfo.getData();
            edit.putInt("id",teacher.getTea_id());
            edit.commit();
            //跳转到菜单界面
            Intent intent = new Intent(this,AboutTeacherActivity.class);
            startActivity(intent);

        }
        else if(!resultInfo.getFlag()){
            //登陆界面打印登陆失败提示信息
            TextView tv_login_note = findViewById(R.id.tv_tea_login_note);
            tv_login_note.setText(resultInfo.getMsg());
        }
    }

    public void clickTeaLogin(View view) {
        et_tea_login_username = findViewById(R.id.et_tea_login_username);
        et_tea_login_password = findViewById(R.id.et_tea_login_password);
        String username = et_tea_login_username.getText().toString().trim();
        String password = et_tea_login_password.getText().toString().trim();
        TeacherLoginPresenter teacherLoginPresenter = new TeacherLoginPresenter(this,this);
        teacherLoginPresenter.teaLogin(username,password);
    }
}