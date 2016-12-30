package cn.lawliex.ask.answer.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class AnswerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        AnswerDetailFragment fragment = (AnswerDetailFragment)getFragmentManager().findFragmentById(R.id.answer_detail_fragment);
        AnswerDetailContract.Presenter presenter = new AnswerDetailPresenter(fragment);
        presenter.start();

    }
}
