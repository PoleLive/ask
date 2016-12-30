package cn.lawliex.ask.answer.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerDetailFragment extends Fragment implements AnswerDetailContract.View {
    AnswerDetailContract.Presenter presenter;
    TextView titleTxt;
    TextView contentTxt;
    TextView authorTxt;

    @Override
    public void showAnswer(Answer answer) {
        contentTxt.setText(answer.getContent());
        authorTxt.setText(answer.getAuthor());
        titleTxt.setText(answer.getQuestionTitle());
    }

    @Override
    public void setPresenter(AnswerDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.answer_detail_fragment, container, false);
        titleTxt = (TextView)view.findViewById(R.id.answer_detail_questionTitle);
        authorTxt = (TextView)view.findViewById(R.id.answer_detail_author);
        contentTxt = (TextView)view.findViewById(R.id.answer_detail_content);
        return view;
    }
}
