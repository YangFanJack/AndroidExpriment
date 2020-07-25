package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ExamRecord;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.StudentWritePaperModel;
import com.example.androidexpriment.view.StudentWritePaperView;

public class StudentWritePaperPresenter {
    private StudentWritePaperModel studentWritePaperModel;
    private StudentWritePaperView studentWritePaperView;

    public StudentWritePaperPresenter(StudentWritePaperView studentWritePaperView, Context context){
        this.studentWritePaperView = studentWritePaperView;
        this.studentWritePaperModel = new StudentWritePaperModel(context);
    }

    /**
     * 根据选择的试卷id查找试卷内容
     * @param id
     */
    public void findChoosedPaperById(int id){
        ResultInfo resultInfo = studentWritePaperModel.findChoosePaperById(id);
        studentWritePaperView.onFindChoosedPaperByIdResult(resultInfo);
    }

    /**
     * 把答题完的试卷提交
     * @param examRecord
     */
    public void submitPaper(ExamRecord examRecord){
        ResultInfo resultInfo = studentWritePaperModel.submitPaper(examRecord);
        studentWritePaperView.onSubmitPaperResult(resultInfo);
    }
}
