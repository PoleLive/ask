package cn.lawliex.ask.answer.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class AnswerAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_add);

        AnswerAddFragment fragment = (AnswerAddFragment)getFragmentManager().findFragmentById(R.id.answer_add_fragment);
        AnswerAddContract.Presenter presenter = new AnswerAddPresenter(fragment);
    }
}
