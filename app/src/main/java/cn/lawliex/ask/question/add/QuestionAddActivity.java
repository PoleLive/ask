package cn.lawliex.ask.question.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class QuestionAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_add);
        QuestionAddFragment fragment = (QuestionAddFragment) getFragmentManager().findFragmentById(R.id.question_add_fragment);
        QuestionAddContract.Presenter presenter = new QuestionAddPresenter(fragment);

    }
}
