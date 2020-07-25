package com.example.androidexpriment.presenter;

import android.content.Context;

import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.model.TeacherMakeItemModel;
import com.example.androidexpriment.view.TeacherMakeItemView;

public class TeacherMakeItemPresenter {
    private TeacherMakeItemModel teacherMakeItemModel;
    private TeacherMakeItemView teacherMakeItemView;

    public TeacherMakeItemPresenter(TeacherMakeItemView teacherMakeItemView, Context context){
        this.teacherMakeItemView = teacherMakeItemView;
        this.teacherMakeItemModel = new TeacherMakeItemModel(context);
    }

    /**
     * 老师提交写好的题目
     * @param item
     */
    public void submitItem(Item item){
        ResultInfo resultInfo = teacherMakeItemModel.submitItem(item);
        teacherMakeItemView.onSubmitItemResult(resultInfo);
    }
}
