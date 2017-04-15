package cn.lawliex.ask.admin.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.lawliex.ask.R;
import cn.lawliex.ask.admin.answer.AdminAnswerActivity;
import cn.lawliex.ask.admin.comment.CommentActivity;
import cn.lawliex.ask.admin.question.AdminQuestionActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bn_user, bn_question, bn_comment, bn_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn_answer = (TextView) findViewById(R.id.activity_main_admin_answer);
        bn_question = (TextView) findViewById(R.id.activity_main_admin_question);
        bn_comment = (TextView) findViewById(R.id.activity_main_admin_comment);
        bn_user = (TextView) findViewById(R.id.activity_main_admin_user);
        bn_answer.setOnClickListener(this);
        bn_question.setOnClickListener(this);
        bn_comment.setOnClickListener(this);
        bn_user.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_admin_answer:
                Intent answerIntent = new Intent(MainActivity.this, AdminAnswerActivity.class);
                startActivity(answerIntent);
                break;
            case R.id.activity_main_admin_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_admin_question:
                Intent questionIntent = new Intent(MainActivity.this, AdminQuestionActivity.class);
                startActivity(questionIntent);
                break;
            case R.id.activity_main_admin_user:
                break;
        }
    }

}
