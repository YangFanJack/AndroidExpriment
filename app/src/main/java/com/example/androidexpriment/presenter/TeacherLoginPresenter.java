package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.TeacherLoginModel;
import com.example.androidexpriment.view.TeacherLoginView;

public class TeacherLoginPresenter {
    private TeacherLoginModel teacherLoginModel;
    private TeacherLoginView teacherLoginView;

    public TeacherLoginPresenter(TeacherLoginView teacherLoginView, Context context){
        this.teacherLoginView = teacherLoginView;
        this.teacherLoginModel = new TeacherLoginModel(context);
    }

    /**
     * 老师登录
     * @param username 老师帐号
     * @param password 老师密码
     */
    public void teaLogin(String username,String password){
        ResultInfo resultInfo = this.teacherLoginModel.teaLogin(username,password);
        teacherLoginView.onTeaLoginResult(resultInfo);
    }
}
