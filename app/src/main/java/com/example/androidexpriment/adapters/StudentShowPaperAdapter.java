package com.example.androidexpriment.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.view.StudentShowPaperActivity;
import com.example.androidexpriment.view.StudentWritePaperActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class StudentShowPaperAdapter extends RecyclerView.Adapter<StudentShowPaperAdapter.InnerHolder> {

    private List<Paper> data;
    private HashMap<Integer,String> answers = new HashMap<>();
    private List<Integer> studentPapers;

    public StudentShowPaperAdapter(List<Paper> data,List<Integer> studentPapers){
        this.data = data;
        this.studentPapers = studentPapers;
    }

    /**
     * 用于创建条目的view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public InnerHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        /**
         * 传的view就是条目的界面
         * 1.拿到view
         * 2.创建innerholder（实现复用）
         */
//        View view = View.inflate(parent.getContext(), R.layout.student_show_paper,null);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_show_paper, parent, false);
        return new InnerHolder(view);
    }

    /**
     * 用于绑定holder，用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.setData(data.get(position));
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
        private Button button;
        Context context;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目控件
            paperId = itemView.findViewById(R.id.stu_showPaper_paperId);
            teaName = itemView.findViewById(R.id.stu_showPaper_teaName);
            paperTime = itemView.findViewById(R.id.stu_showPaper_paperTime);
            button = itemView.findViewById(R.id.stu_showPaper_button);
            context = itemView.getContext();
        }

        /**
         * 设置数据
         * @param paper
         */
        public void setData(Paper paper) {
            paperId.setText(String.valueOf(paper.getPaper_id()));
            teaName.setText(paper.getTea_name());
            paperTime.setText(paper.getPaper_time());
            if(!studentPapers.contains(paper.getPaper_id())){
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, StudentWritePaperActivity.class);
                        intent.putExtra("paperId",paper.getPaper_id());
                        context.startActivity(intent);
                    }
                });
            }
            else{
                button.setText("试卷已答");
            }

//            teaName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String answer = "aaaaaaaaaaaaaa";
//                    Random random = new Random();
//                    answers.put(random.nextInt(10000),answer);
//                }
//            });
//            paperId.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, StudentShowPaperActivity.class);
//                    intent.putExtra("answers",(Serializable)answers);
//                    context.startActivity(intent);
//                }
//            });
        }
    }
}
