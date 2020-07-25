package com.example.androidexpriment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidexpriment.R;
import com.example.androidexpriment.bean.Item;
import com.example.androidexpriment.bean.ResultInfo;
import com.example.androidexpriment.bean.Student;
import com.example.androidexpriment.presenter.StudentLoginPresenter;
import com.example.androidexpriment.presenter.TeacherMakeItemPresenter;

public class TeacherMakeItemActivity extends AppCompatActivity implements TeacherMakeItemView{

    private static final String TAG = "TeacherMakeItemActivity";
    private EditText et_tea_makeItem_content;
    private EditText et_tea_makeItem_answer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.tea_btn_one:
                Toast.makeText(this,"欢迎来到新建试卷界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherMakePaperActivity.class));
                break;
            case R.id.tea_btn_two:
                Toast.makeText(this,"欢迎来到新建题目界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherMakeItemActivity.class));
                break;
            case R.id.tea_btn_three:
                Toast.makeText(this,"欢迎来到查看试卷界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,TeacherShowPaperActivity.class));
                break;
            case R.id.tea_btn_four:
                Toast.makeText(this,"欢迎来到我的信息界面",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,AboutTeacherActivity.class));
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_make_item);
    }

    /**
     * 结果包含Boolean,msg
     *
     * @param resultInfo
     */
    @Override
    public void onSubmitItemResult(ResultInfo resultInfo) {
        //处理前端页面
        Log.d(TAG,"数据库的数据已经传到前端界面");
        if(resultInfo.getFlag()){
            Toast.makeText(this,"提交题目成功",Toast.LENGTH_LONG).show();

        }
        else if(!resultInfo.getFlag()){
            Toast.makeText(this,"提交题目失败",Toast.LENGTH_LONG).show();
        }
    }

    public void click_teacherMakeItem(View view) {
        et_tea_makeItem_content = findViewById(R.id.et_tea_makeItem_content);
        et_tea_makeItem_answer = findViewById(R.id.et_tea_makeItem_answer);
        Item item = new Item();
        item.setItem_content(et_tea_makeItem_content.getText().toString().trim());
        item.setItem_answer(et_tea_makeItem_answer.getText().toString().trim());
        TeacherMakeItemPresenter teacherMakeItemPresenter = new TeacherMakeItemPresenter(this,this);
        teacherMakeItemPresenter.submitItem(item);
    }
}