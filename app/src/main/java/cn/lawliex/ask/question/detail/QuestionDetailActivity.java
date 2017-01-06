package cn.lawliex.ask.question.detail;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;
import cn.lawliex.ask.answer.list.AnswerListContract;
import cn.lawliex.ask.answer.list.AnswerListFragment;
import cn.lawliex.ask.answer.list.AnswerListPresenter;

public class QuestionDetailActivity extends AppCompatActivity {
    AnswerListContract.Presenter answerListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("问题详情");
        QuestioinDetailFragment questioinDetailfragment = (QuestioinDetailFragment) getFragmentManager().findFragmentById(R.id.question_detail_fragment);
        QuestionDetailContract.Presenter presenter = new QuestionDetailPresenter(questioinDetailfragment);
        AnswerListFragment answerListFragment = (AnswerListFragment)getFragmentManager().findFragmentById(R.id.answer_list_fragment);
        answerListPresenter = new AnswerListPresenter(answerListFragment);
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
    public void onStart(){
        super.onStart();
        answerListPresenter.start();
    }
}
