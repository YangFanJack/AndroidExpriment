package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface StudentExamRecordView {
    /**
     * 结果包含Boolean，ArrayList<ExamRecord>
     * @param resultInfo
     */
    public void onFindExamRecordByIdResult(ResultInfo resultInfo);
}
