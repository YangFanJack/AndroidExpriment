package com.example.androidexpriment.view;

import com.example.androidexpriment.bean.ResultInfo;

public interface StudentWritePaperView {
    /**
     * 返回结果包含Boolean,Paper
     * @param resultInfo
     */
    public void onFindChoosedPaperByIdResult(ResultInfo resultInfo);

    /**
     * 返回结果包含Boolean,msg
     * @param resultInfo
     */
    public void onSubmitPaperResult(ResultInfo resultInfo);
}
