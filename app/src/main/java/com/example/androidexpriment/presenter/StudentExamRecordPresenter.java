package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.StudentExamRecordModel;
import com.example.androidexpriment.view.StudentExamRecordView;

public class StudentExamRecordPresenter {
    private StudentExamRecordModel studentExamRecordModel;
    private StudentExamRecordView studentExamRecordView;

    public StudentExamRecordPresenter(StudentExamRecordView studentExamRecordView, Context context){
        this.studentExamRecordView = studentExamRecordView;
        this.studentExamRecordModel = new StudentExamRecordModel(context);
    }

    /**
     * 根据学生id查找改名学生的考试记录
     * @param id
     */
    public void findExamRecordById(int id){
        ResultInfo resultInfo = studentExamRecordModel.findExamRecordById(id);
        studentExamRecordView.onFindExamRecordByIdResult(resultInfo);

    }
}
