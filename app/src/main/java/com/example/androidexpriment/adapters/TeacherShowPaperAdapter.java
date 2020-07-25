package com.example.androidexpriment.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.view.StudentWritePaperActivity;

import java.util.List;

public class TeacherShowPaperAdapter extends RecyclerView.Adapter<TeacherShowPaperAdapter.InnerHolder> {

    private List<Paper> data;

    public TeacherShowPaperAdapter(List<Paper> data){
        this.data = data;
    }

    /**
     * 用于创建条目的view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TeacherShowPaperAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 传的view就是条目的界面
         * 1.拿到view
         * 2.创建innerholder（实现复用）
         */
//        View view = View.inflate(parent.getContext(), R.layout.student_show_paper,null);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.teacher_show_paper, parent, false);
        return new TeacherShowPaperAdapter.InnerHolder(view);
    }

    /**
     * 用于绑定holder，用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TeacherShowPaperAdapter.InnerHolder holder, int position) {
        holder.setDate(data.get(position));
    }

    /**
     * 返回条目个数
     * @return
     */
    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView paperId;
        private TextView teaName;
        private TextView paperTime;
        Context context;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目控件
            paperId = itemView.findViewById(R.id.tea_showPaper_paperId);
            teaName = itemView.findViewById(R.id.tea_showPaper_teaName);
            paperTime = itemView.findViewById(R.id.tea_showPaper_paperTime);
            context = itemView.getContext();
        }

        /**
         * 设置数据
         * @param paper
         */
        public void setDate(Paper paper) {
            paperId.setText(String.valueOf(paper.getPaper_id()));
            teaName.setText(paper.getTea_name());
            paperTime.setText(paper.getPaper_time());
        }
    }
}
