package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface TeacherShowPaperView {
    /**
     * 结果包含Boolean,ArrayList<Paper>
     * @param resultInfo
     */
    public void onFindAllPaperResult(ResultInfo resultInfo);
}
