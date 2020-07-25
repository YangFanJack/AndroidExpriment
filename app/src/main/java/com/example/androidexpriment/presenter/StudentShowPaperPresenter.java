package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.StudentShowPaperModel;
import com.example.androidexpriment.view.StudentShowPaperView;

public class StudentShowPaperPresenter {
    private StudentShowPaperModel studentShowPaperModel;
    private StudentShowPaperView studentShowPaperView;

    public StudentShowPaperPresenter(StudentShowPaperView studentShowPaperView, Context context){
        this.studentShowPaperView = studentShowPaperView;
        this.studentShowPaperModel = new StudentShowPaperModel(context);
    }

    /**
     * 查找所有试卷
     */
    public void findAllPaper(){
        ResultInfo resultInfo = studentShowPaperModel.findAllPaper();
        studentShowPaperView.onFindAllPaperResult(resultInfo);
    }

    public void findStudentPaper(int id){
        ResultInfo resultInfo = studentShowPaperModel.findStudentPaper(id);
        studentShowPaperView.onFindStudentPaperResult(resultInfo);
    }
}
