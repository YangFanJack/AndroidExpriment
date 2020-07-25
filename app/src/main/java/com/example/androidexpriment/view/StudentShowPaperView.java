package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface StudentShowPaperView {
    /**
     * 结果包含Boolean,ArrayList<Paper>
     * @param resultInfo
     */
    public void onFindAllPaperResult(ResultInfo resultInfo);

    /**
     * 结果包含arrayList<int>,boolean
     * @param resultInfo
     */
    public void onFindStudentPaperResult(ResultInfo resultInfo);
}
