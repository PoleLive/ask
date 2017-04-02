package cn.lawliex.ask.question.simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;


public class QuestionSimpleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_simple_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("TA的提问");
         QuestionSimpleListFragment fragment = (QuestionSimpleListFragment) getFragmentManager().findFragmentById(R.id.question_list_fragment);
        QuestionSimpleListContract.Presenter presenter = new QuestionSimpleListPresenter(fragment);

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
