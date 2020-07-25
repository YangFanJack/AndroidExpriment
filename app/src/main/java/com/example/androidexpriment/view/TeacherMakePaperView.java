package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface TeacherMakePaperView {
    /**
     * 结果包含Boolean,msg
     * @param resultInfo
     */
    public void onSubmitPaperResult(ResultInfo resultInfo);
    /**
     * 结果包含boolean，arraylist
     * @param resultInfo
     */
    public void onFindAllItemResult(ResultInfo resultInfo);
}
