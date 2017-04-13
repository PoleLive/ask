package cn.lawliex.ask.admin.question;

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
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.question.detail.QuestionDetailActivity;

public class AdminQuestionActivity extends AppCompatActivity implements AdminQuestionContract.View{
    AdminQuestionContract.Presenter presenter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_question);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("问题列表");
        listView = (ListView)findViewById(R.id.activity_admin_listview);
        List<Question> datas = new ArrayList<>();
        AdminQuestionAdapter adapter = new AdminQuestionAdapter(datas, this);
        listView.setAdapter(adapter);
        AdminQuestionPresenter presenter = new AdminQuestionPresenter(this);
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
    public void setPresenter(AdminQuestionContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showQuestions(List<Question> questionList) {

        AdminQuestionAdapter adapter = new AdminQuestionAdapter(questionList, this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.invalidate();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdminQuestionAdapter tmp = (AdminQuestionAdapter) listView.getAdapter();
                int questionId = tmp.getDatas().get(position).getId();
                toDetailAct(questionId);
            }
        });
    }
    public void toDetailAct(int id) {
        Intent intent = new Intent(this, QuestionDetailActivity.class);
        intent.putExtra("questionId",id);
        startActivity(intent);
    }
}
