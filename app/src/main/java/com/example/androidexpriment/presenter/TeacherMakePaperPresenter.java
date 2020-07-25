package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.TeacherMakePaperModel;
import com.example.androidexpriment.view.TeacherMakePaperView;

public class TeacherMakePaperPresenter {
    private TeacherMakePaperModel teacherMakePaperModel;
    private TeacherMakePaperView teacherMakePaperView;

    public TeacherMakePaperPresenter(TeacherMakePaperView teacherMakePaperView, Context context){
        this.teacherMakePaperView = teacherMakePaperView;
        this.teacherMakePaperModel = new TeacherMakePaperModel(context);
    }

    /**
     * 老师提交编好的试卷
     * @param paper
     */
    public void submitPaper(Paper paper){
        ResultInfo resultInfo = teacherMakePaperModel.submitPaper(paper);
        teacherMakePaperView.onSubmitPaperResult(resultInfo);
    }

    /**
     * 查找所有题目
     */
    public void findAllItem(){
        ResultInfo resultInfo = teacherMakePaperModel.findAllItem();
        teacherMakePaperView.onFindAllItemResult(resultInfo);
    }
}
