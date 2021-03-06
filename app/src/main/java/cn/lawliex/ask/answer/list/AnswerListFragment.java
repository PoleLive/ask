package cn.lawliex.ask.answer.list;

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
import cn.lawliex.ask.answer.detail.AnswerDetailActivity;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerListFragment extends Fragment implements AnswerListContract.View {
    List<Answer> answers;
    AnswerListContract.Presenter presenter;
    ListView answerListView;
    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showAnswerList(List<Answer> answerList) {
        this.answers = answerList;
        AnswerListAdapter adapter = new AnswerListAdapter(answers,this);
        answerListView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(AnswerListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_list_fragment, container, false);

        answerListView = (ListView)view.findViewById(R.id.answer_list_view);
        initListener();
        return view;
    }
    public void initListener(){
        answerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AnswerDetailActivity.class);
                intent.putExtra("answerId",answers.get(position).getId());
                startActivity(intent);
            }
        });
    }
    @Override
    public void update(){
        presenter.start();
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
