package cn.lawliex.ask.question.simplelist;

import android.app.Activity;
import android.content.Context;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.question.detail.QuestionDetailActivity;
import cn.lawliex.ask.question.list.QuestionListAdapter;

/**
 * Created by Terence on 2017/4/2.
 */

public class QuestionSimpleListFragment extends Fragment implements QuestionSimpleListContract.View {
    ListView listView;
    QuestionSimpleListContract.Presenter presenter;
    List<Question> datas;
    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_list_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.question_list);
        initListener();
        return view;
    }

    public void initListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int questionId = datas.get(position).getId();
                toDetailAct(questionId);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showErrorView(String errMsg) {
        Toast.makeText(getActivity(),errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadSuccessView(String msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showQuestionList(Question question) {

    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void showLoadingView(int progress) {

    }

    @Override
    public void toDetailAct(int id) {
        Intent intent = new Intent(getAct(), QuestionDetailActivity.class);
        intent.putExtra("questionId",id);
        startActivity(intent);
    }

    @Override
    public void setListDatas(List<Question> questions) {
        datas = questions;
        QuestionListAdapter adapter = new QuestionListAdapter(getAct(),datas);
        listView.setAdapter(adapter);

    }

    @Override
    public void setAct(Activity act) {
        this.activity = act;
    }

    @Override
    public Activity getAct() {
        if(getActivity() != null)
            return  getActivity();
        return activity;
    }

    @Override
    public void setPresenter(QuestionSimpleListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Context getContext() {
        if(getActivity() != null)
            return  getActivity();
        return getAct();
    }


}
