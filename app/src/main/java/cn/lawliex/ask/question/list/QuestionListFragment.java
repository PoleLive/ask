package cn.lawliex.ask.question.list;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/28.
 */

public class QuestionListFragment extends Fragment implements QuestionListContract.View{

    ListView listView;

    public QuestionListFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_list_fragment,container,false);
        listView = (ListView)view.findViewById(R.id.question_list);
        List<Question> questions = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Question question = new Question();
            question.setTitle("haha");
            question.setUserId(5);
            question.setContent("balbalblablab");
            questions.add(question);
        }
        QuestionListAdapter adapter = new QuestionListAdapter(getActivity(),questions);

        listView.setAdapter(adapter);
        return view;
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
    public void showLoadingView(int progress) {

    }

    @Override
    public void toDetailAct(int id) {

    }

    @Override
    public void setListDatas(List<Question> questions) {
        QuestionListAdapter adapter = new QuestionListAdapter(getActivity(),questions);
        listView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(QuestionListContract.Presenter presenter) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
