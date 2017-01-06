package cn.lawliex.ask.answer.list_plus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;

public class QandAListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qand_alist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("回答");
        QandAListFragment fragment = (QandAListFragment) getFragmentManager().findFragmentById(R.id.q_and_a_fragment);
        QandAListContract.Presenter presenter= new QandAListPresenter(fragment);

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
