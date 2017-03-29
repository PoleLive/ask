package cn.lawliex.ask.follow;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.lawliex.ask.R;


public class FollowActivity extends Activity implements View.OnClickListener{
    TextView bar_user,bar_answer, bar_question;
    int initColor = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow);
        bar_answer = (TextView)findViewById(R.id.fragment_follow_bar_answer);
        bar_question = (TextView)findViewById(R.id.fragment_follow_bar_question);
        bar_user = (TextView)findViewById(R.id.fragment_follow_bar_user);
        initColor = bar_answer.getCurrentTextColor();
        onBnPress(bar_user);
        bar_answer.setOnClickListener(this);
        bar_user.setOnClickListener(this);
        bar_question.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_follow_bar_user:
                onBnPress(bar_user);
                break;
            case R.id.fragment_follow_bar_answer:
                onBnPress(bar_answer);
                break;
            case R.id.fragment_follow_bar_question:
                onBnPress(bar_question);
                break;
        }
    }
    void onBnPress(TextView bn){
        bar_answer.setTextColor(Color.parseColor("#b6b6f5"));
        bar_question.setTextColor(Color.parseColor("#b6b6f5"));
        bar_user.setTextColor(Color.parseColor("#b6b6f5"));
        bn.setTextColor(Color.WHITE);
    }
}
