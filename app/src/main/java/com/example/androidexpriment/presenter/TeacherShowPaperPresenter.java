package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.TeacherShowPaperModel;
import com.example.androidexpriment.view.TeacherShowPaperView;

public class TeacherShowPaperPresenter {
    private TeacherShowPaperModel teacherShowPaperModel;
    private TeacherShowPaperView teacherShowPaperView;

    public TeacherShowPaperPresenter(TeacherShowPaperView teacherShowPaperView, Context context){
        this.teacherShowPaperView = teacherShowPaperView;
        this.teacherShowPaperModel = new TeacherShowPaperModel(context);
    }

    /**
     * 查找所有试卷（老师不能查看内容，再加一个页面麻烦）
     */
    public void findAllPaper(){
        ResultInfo resultInfo = teacherShowPaperModel.findAllPaper();
        teacherShowPaperView.onFindAllPaperResult(resultInfo);
    }
}
