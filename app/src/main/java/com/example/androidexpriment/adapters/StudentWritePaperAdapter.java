package com.example.androidexpriment.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.Paper;
import com.example.androidexpriment.view.StudentWritePaperActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class StudentWritePaperAdapter extends RecyclerView.Adapter<StudentWritePaperAdapter.InnerHolder> {

    private List<Item> data;
    private HashMap<Integer,String> answers = new HashMap<>();


    public StudentWritePaperAdapter(List<Item> data){
        this.data = data;
    }

    /**
     * 用于创建条目的view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public StudentWritePaperAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 传的view就是条目的界面
         * 1.拿到view
         * 2.创建innerholder（实现复用）
         */
//        View view = View.inflate(parent.getContext(), R.layout.student_show_paper,null);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.student_write_paper, parent, false);
        return new StudentWritePaperAdapter.InnerHolder(view);
    }

    /**
     * 用于绑定holder，用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull StudentWritePaperAdapter.InnerHolder holder, int position) {
        holder.setData(data.get(position),position);
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

        private TextView writePaper_content;
        private EditText writePaper_answer;
        private TextView writePaper_itemId;
        private Button writePaper_saveAnswer;
        Context context;

        public InnerHolder(@NonNull View itemView){
            super(itemView);
            //找到条目控件
            writePaper_content = itemView.findViewById(R.id.stu_writePaper_content);
            writePaper_answer = itemView.findViewById(R.id.stu_writePaper_answer);
            writePaper_itemId = itemView.findViewById(R.id.stu_writePaper_itemId);
            writePaper_saveAnswer = itemView.findViewById(R.id.stu_writePaper_saveAnswer);
            context = itemView.getContext();
        }

        /**
         * 设置数据
         * @param item
         */
        public void setData(Item item,int position) {

            writePaper_content.setText(item.getItem_content());
            writePaper_itemId.setText(String.valueOf(item.getItem_id()));
            writePaper_answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String answer = editable.toString().trim();
                    if(!answer.equals("")){
                        answers.put(item.getItem_id(),answer);
                    }
                    else{
                        System.out.println("answers大小--->"+answers.size());
                        answers.remove(item.getItem_id());
                    }
                }
            });
            writePaper_saveAnswer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int count = 0;
//                    for (Integer key : answers.keySet()) {
//                        Log.v("wjw","接收到的Key = " + key);
//                        Log.v("wjw","接收到的value = " + answers.get(key));
//                        if(answers.get(key).equals("")){
//                            count++;
//                        }
//                    }
                    System.out.println("++++++++++++++++++++++++++++++++++++");
                    System.out.println(answers);
                    System.out.println("++++++++++++++++++++++++++++++++++++");
                    if(answers.size()!=5){
                        Toast.makeText(context,"保存失败，请答完所有题目再保存",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
                        try {
                            FileOutputStream outputStream = context.openFileOutput("answers",Context.MODE_PRIVATE);
                            ObjectOutput out = new ObjectOutputStream(outputStream);
                            out.writeObject(answers);
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            //只有最后一个保存按钮可见
            if(data.size() != position+1){
                writePaper_saveAnswer.setVisibility(View.GONE);
            }
        }
    }
}
