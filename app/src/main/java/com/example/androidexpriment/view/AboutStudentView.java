package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface AboutStudentView {
    /**
     * 结果包含Boolean和Student
     * @param resultInfo
     */
    public void onFindStudentByIdResult(ResultInfo resultInfo);
}
