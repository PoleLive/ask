package cn.lawliex.ask.answer.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;

public class AnswerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("回答详情");
        AnswerDetailFragment fragment = (AnswerDetailFragment)getFragmentManager().findFragmentById(R.id.answer_detail_fragment);
        AnswerDetailContract.Presenter presenter = new AnswerDetailPresenter(fragment);
        presenter.start();

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
}
