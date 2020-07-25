package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.AboutStudentModel;
import com.example.androidexpriment.view.AboutStudentView;

public class AboutStudentPresenter {
    private AboutStudentModel aboutStudentModel;
    private AboutStudentView aboutStudentView;

    public AboutStudentPresenter(AboutStudentView aboutStudentView, Context context){
        this.aboutStudentView = aboutStudentView;
        this.aboutStudentModel = new AboutStudentModel(context);
    }

    /**
     * 根据帐号查找学生
     * @param id
     */
    public void findStudentById(int id){
        ResultInfo resultInfo = this.aboutStudentModel.findStudentById(id);
        aboutStudentView.onFindStudentByIdResult(resultInfo);
    }
}
