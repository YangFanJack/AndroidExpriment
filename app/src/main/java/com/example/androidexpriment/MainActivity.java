package com.example.androidexpriment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidexpriment.view.AboutStudentActivity;
import com.example.androidexpriment.view.StudentLoginActivity;
import com.example.androidexpriment.view.StudentLoginView;
import com.example.androidexpriment.view.TeacherLoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button stu_enter;
    private Button tea_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stu_enter = findViewById(R.id.stu_enter);
        tea_enter = findViewById(R.id.tea_enter);
    }

    public void clickStuEnter(View view) {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
    }

    public void clickTeaEnter(View view) {
        Intent intent = new Intent(this, TeacherLoginActivity.class);
        startActivity(intent);
    }
}