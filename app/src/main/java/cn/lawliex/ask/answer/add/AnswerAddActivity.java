package cn.lawliex.ask.answer.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;

public class AnswerAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("添加回答");
        AnswerAddFragment fragment = (AnswerAddFragment)getFragmentManager().findFragmentById(R.id.answer_add_fragment);
        AnswerAddContract.Presenter presenter = new AnswerAddPresenter(fragment);
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
