package cn.lawliex.ask.question.detail;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import cn.lawliex.ask.R;
import cn.lawliex.ask.answer.add.AnswerAddActivity;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/29.
 */

public class QuestioinDetailFragment extends Fragment implements QuestionDetailContract.View, View.OnClickListener{
    QuestionDetailContract.Presenter presenter;

    TextView titleTxt;
    TextView contentTxt;
    TextView answerCountTxt;
    TextView visitCountTxt;
    ListView answerListView;
    Button followBn;
    Button answerBn;
    Question question;

    @Override
    public void showErrorView(String errMsg) {

    }

    @Override
    public void showQuestionDetail(Question question) {
        this.question = question;
        titleTxt.setText(question.getTitle());
        contentTxt.setText(question.getContent());

    }

    @Override
    public void showLoadingView(int progress) {

    }

    @Override
    public void setPresenter(QuestionDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_detail_fragment,container,false);

        answerBn = (Button)view.findViewById(R.id.question_detail_bn_answer);
        followBn = (Button)view.findViewById(R.id.question_detail_bn_follow);
        contentTxt = (TextView) view.findViewById(R.id.question_detail_txt_content);
        titleTxt = (TextView)view.findViewById(R.id.question_detail_txt_title);
        initListener();

        return view;
    }

    public void initListener(){
        followBn.setOnClickListener(this);
        answerBn.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_detail_bn_answer:
                Intent intent = new Intent(getActivity(), AnswerAddActivity.class);
                intent.putExtra("questionId",question.getId());
                startActivity(intent);
                break;
            case R.id.question_detail_bn_follow:
                break;
        }
    }
}
