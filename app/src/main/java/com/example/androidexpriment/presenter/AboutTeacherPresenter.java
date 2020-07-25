package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.AboutTeacherModel;
import com.example.androidexpriment.view.AboutTeacherView;

public class AboutTeacherPresenter {
    private AboutTeacherModel aboutTeacherModel;
    private AboutTeacherView aboutTeacherView;

    public AboutTeacherPresenter(AboutTeacherView aboutTeacherView, Context context){
        this.aboutTeacherView = aboutTeacherView;
        this.aboutTeacherModel = new AboutTeacherModel(context);
    }

    /**
     * 根据帐号查找老师
     * @param id
     */
    public void findTeacherById(int id){
        ResultInfo resultInfo = this.aboutTeacherModel.findTeacherById(id);
        aboutTeacherView.onFindTeacherByIdResult(resultInfo);
    }
}
