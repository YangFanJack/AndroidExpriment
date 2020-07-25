package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.StudentLoginModel;
import com.example.androidexpriment.view.StudentLoginView;

public class StudentLoginPresenter {
    private StudentLoginModel studentLoginModel;
    private StudentLoginView studentLoginView;

    public StudentLoginPresenter(StudentLoginView studentLoginView, Context context){
        this.studentLoginView = studentLoginView;
        this.studentLoginModel = new StudentLoginModel(context);
    }

    /**
     * 学生登录
     * @param username 学生帐号
     * @param password 学生密码
     */
    public void stuLogin(String username,String password){
        ResultInfo resultInfo = this.studentLoginModel.stuLogin(username,password);
        studentLoginView.onStuLoginResult(resultInfo);

    }
}
