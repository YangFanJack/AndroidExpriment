package com.example.androidexpriment.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.Item;

import org.w3c.dom.Text;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class TeacherMakePaperAdapter extends RecyclerView.Adapter<TeacherMakePaperAdapter.InnerHolder> {
    private List<Item> data;
    private HashMap<Integer,String[]> chooseItems = new HashMap<>();


    public TeacherMakePaperAdapter(List<Item> data){
        this.data = data;
    }

    /**
     * 用于创建条目的view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public TeacherMakePaperAdapter.InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 传的view就是条目的界面
         * 1.拿到view
         * 2.创建innerholder（实现复用）
         */
//        View view = View.inflate(parent.getContext(), R.layout.student_show_paper,null);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.teacher_make_paper, parent, false);
        return new TeacherMakePaperAdapter.InnerHolder(view);
    }

    /**
     * 用于绑定holder，用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull TeacherMakePaperAdapter.InnerHolder holder, int position) {
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

        private CheckBox makePaper_itemId;
        private TextView makePaper_content;
        private Button makePaper_saveChoose;
        private TextView makePaper_answer;
        Context context;

        public InnerHolder(@NonNull View itemView){
            super(itemView);
            //找到条目控件
            makePaper_itemId = itemView.findViewById(R.id.make_paper_itemId);
            makePaper_content = itemView.findViewById(R.id.make_paper_content);
            makePaper_saveChoose = itemView.findViewById(R.id.makePaper_saveChoose);
            makePaper_answer = itemView.findViewById(R.id.make_paper_answer);
            context = itemView.getContext();
        }

        /**
         * 设置数据
         * @param item
         */
        public void setData(Item item,int position) {

            makePaper_itemId.setText(String.valueOf(item.getItem_id()));
            makePaper_content.setText(item.getItem_content());
            makePaper_answer.setText(item.getItem_answer());
            makePaper_itemId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked){
                        String[] items = {item.getItem_content(),item.getItem_answer()};
                        chooseItems.put(item.getItem_id(),items);
                    }
                    else{
                        chooseItems.remove(item.getItem_id());
                    }
                }
            });
            makePaper_saveChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    for (Integer key : chooseItems.keySet()) {
//                        Log.v("wjw","接收到的Key = " + key);
//                        Log.v("wjw","接收到的value = " + chooseItems.get(key));
//                    }
                    if(chooseItems.size()!=5){
                        Toast.makeText(context,"保存失败，请选择5道题再保存",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,"保存成功",Toast.LENGTH_SHORT).show();
                        try {
                            FileOutputStream outputStream = context.openFileOutput("chooseItems", Context.MODE_PRIVATE);
                            ObjectOutput out = new ObjectOutputStream(outputStream);
                            out.writeObject(chooseItems);
                            outputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            //只有最后一个保存按钮可见
            if(data.size() != position+1){
                makePaper_saveChoose.setVisibility(View.GONE);
            }
        }
    }
}
