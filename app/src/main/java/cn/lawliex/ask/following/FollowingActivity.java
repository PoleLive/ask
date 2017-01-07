package cn.lawliex.ask.following;

import android.app.Activity;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.answer.list_plus.QandAListContract;
import cn.lawliex.ask.answer.list_plus.QandAListFragment;
import cn.lawliex.ask.following.collection.FollowAnswerPresenter;
import cn.lawliex.ask.following.question.FollowQuestionPresenter;
import cn.lawliex.ask.following.user.FollowUserContract;
import cn.lawliex.ask.following.user.FollowUserFragment;
import cn.lawliex.ask.following.user.FollowUserPresenter;
import cn.lawliex.ask.question.list.QuestionListContract;
import cn.lawliex.ask.question.list.QuestionListFragment;

public class FollowingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView userTxt;
    TextView questionTxt;
    TextView collectionTxt;
    FragmentTransaction ft;
    FollowUserFragment userFragment;
    QuestionListFragment questionFragment;
    QandAListFragment answerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        userTxt = (TextView)findViewById(R.id.following_user_txt);
        questionTxt = (TextView)findViewById(R.id.following_question_txt);
        collectionTxt = (TextView)findViewById(R.id.following_answer_txt);
        userTxt.setOnClickListener(this);
        questionTxt.setOnClickListener(this);
        collectionTxt.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int selection = getIntent().getIntExtra("selection",0);
        ft = getFragmentManager().beginTransaction();
        switch (selection){
            case 0:case 1:
                userFragment = new FollowUserFragment();
                userFragment.setActivity(this);
                FollowUserContract.Presenter presenter2 = new FollowUserPresenter(userFragment);
                ft.replace(R.id.following_fragment, userFragment).commit();
                setTitle("关注的用户");
                break;
            case 2:
                questionFragment = new QuestionListFragment();
                questionFragment.setAct(this);
                QuestionListContract.Presenter presenter1 =new FollowQuestionPresenter(questionFragment);
                presenter1.start();
                ft.replace(R.id.following_fragment, questionFragment).commit();
                setTitle("关注的问题");
                break;
            case 3:
                answerFragment = new QandAListFragment();
                answerFragment.setAct(this);
                QandAListContract.Presenter presenter3 = new FollowAnswerPresenter(answerFragment);
                ft.replace(R.id.following_fragment, answerFragment).commit();
                setTitle("收藏的回答");
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        ft = getFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.following_user_txt:
                userFragment = new FollowUserFragment();
                userFragment.setActivity(this);
                FollowUserContract.Presenter presenter2 = new FollowUserPresenter(userFragment);
                ft.replace(R.id.following_fragment, userFragment).commit();
                setTitle("关注的用户");
                break;
            case R.id.following_question_txt:
                questionFragment = new QuestionListFragment();
                questionFragment.setAct(this);
                QuestionListContract.Presenter presenter1 =new FollowQuestionPresenter(questionFragment);
                presenter1.start();
                ft.replace(R.id.following_fragment, questionFragment).commit();
                setTitle("关注的问题");
                break;
            case R.id.following_answer_txt:
                answerFragment = new QandAListFragment();
                answerFragment.setAct(this);
                QandAListContract.Presenter presenter3 = new FollowAnswerPresenter(answerFragment);
                ft.replace(R.id.following_fragment, answerFragment).commit();
                setTitle("收藏的回答");
                break;
        }
    }
}
