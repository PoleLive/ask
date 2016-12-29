package cn.lawliex.ask.question.add;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.local.SharedPreferencesHelper;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.question.list.QuestionListActivity;

/**
 * Created by Terence on 2016/12/29.
 */

public class QuestionAddFragment extends Fragment implements QuestionAddContract.View, View.OnClickListener, TextWatcher {
    Button submit;
    EditText titleEdit;
    EditText contentEdit;
    QuestionAddContract.Presenter presenter;
    @Override
    public Question getQuestion() {
        Question question = new Question();
        String title = titleEdit.getText().toString();
        String content = contentEdit.getText().toString();
        SharedPreferencesHelper sphelper = new SharedPreferencesHelper(getActivity());
        if(title != null && content != null){
            question.setTitle(title);
            question.setContent(content);
            question.setUserId(sphelper.getInt("id"));

            return question;
        }
        return null;
    }

    @Override
    public void toDetailAct() {
        Intent intent = new Intent(getActivity(), QuestionListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCreatingView(int progress) {

    }

    @Override
    public void showErrorView(String errMsg) {

    }

    @Override
    public void setPresenter(QuestionAddContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_add_fragment, container, false);
        submit = (Button) view.findViewById(R.id.question_add_submit_bn);
        titleEdit = (EditText)view.findViewById(R.id.question_add_title_edit);
        contentEdit = (EditText)view.findViewById(R.id.question_add_content_edit);
        submit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_add_submit_bn:
                presenter.start();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
