package cn.lawliex.ask.answer.add;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.source.local.SharedPreferencesHelper;
import cn.lawliex.ask.question.detail.QuestionDetailActivity;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerAddFragment extends Fragment implements AnswerAddContract.View ,View.OnClickListener{
    AnswerAddContract.Presenter presenter;
    EditText contentTxt;
    Button submitBn;
    @Override
    public Answer getAnswer() {
        Answer answer = new Answer();
        String content = contentTxt.getText().toString();
        SharedPreferencesHelper spHelper= new SharedPreferencesHelper(getActivity());
        if(content == null)
            return null;
        answer.setContent(content);
        answer.setUserId(spHelper.getInt("id"));
        answer.setEntityId(getActivity().getIntent().getIntExtra("questionId", 0));

        return answer;
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toQuestionDetail(int questionId) {
        questionId = getActivity().getIntent().getIntExtra("questionId",0);
        Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
        intent.putExtra("questionId",questionId);
        startActivity(intent);
    }

    @Override
    public void setPresenter(AnswerAddContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_add_fragment, container, false);
        contentTxt = (EditText)view.findViewById(R.id.answer_add_edit_content);
        submitBn = (Button)view.findViewById(R.id.answer_add_bn_submit);
        initListener();
        return view;
    }
    public void initListener(){
        submitBn.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        presenter.start();
    }
}
