package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.StudentRegisterModel;
import com.example.androidexpriment.view.StudentRegisterView;

public class StudentRegisterPresenter {
    private StudentRegisterModel studentRegisterModel;
    private StudentRegisterView studentRegisterView;

    public StudentRegisterPresenter(StudentRegisterView studentRegisterView, Context context){
        this.studentRegisterView = studentRegisterView;
        this.studentRegisterModel = new StudentRegisterModel(context);
    }

    /**
     * 学生注册
     * @param username 帐号
     * @param password 密码
     */
    public void stuRegister(String username, String password, String name){
        ResultInfo resultInfo = this.studentRegisterModel.stuRegister(username,password,name);
        studentRegisterView.onStuRegisterResult(resultInfo);
    }
}
