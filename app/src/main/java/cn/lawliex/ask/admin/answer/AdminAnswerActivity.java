package cn.lawliex.ask.admin.answer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.lawliex.ask.R;

import cn.lawliex.ask.answer.detail.AnswerDetailActivity;
import cn.lawliex.ask.data.Answer;


public class AdminAnswerActivity extends AppCompatActivity implements AdminAnswerContract.View{
    AdminAnswerContract.Presenter presenter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_answer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("回答列表");
        listView = (ListView)findViewById(R.id.activity_admin_listview);
        List<Answer> datas = new ArrayList<>();
        AdminAnswerAdapter adapter = new AdminAnswerAdapter(datas, this);
        listView.setAdapter(adapter);
        AdminAnswerPresenter presenter = new AdminAnswerPresenter(this);
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
    public void setPresenter(AdminAnswerContract.Presenter presenter) {
        this.presenter = presenter;
    }



    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showAnswers(List<Answer> questionList) {

        AdminAnswerAdapter adapter = new AdminAnswerAdapter(questionList, this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.invalidate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdminAnswerAdapter tmp = (AdminAnswerAdapter) listView.getAdapter();
                int answerId = tmp.getDatas().get(position).getId();
                toDetailAct(answerId);
            }
        });
    }
    public void toDetailAct(int id) {
        Intent intent = new Intent(this, AnswerDetailActivity.class);
        intent.putExtra("answerId",id);
        startActivity(intent);
    }


}
