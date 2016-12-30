package cn.lawliex.ask.answer.detail;

import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerDetailFragment extends Fragment implements AnswerDetailContract.View, View.OnClickListener {
    AnswerDetailContract.Presenter presenter;
    TextView titleTxt;
    TextView contentTxt;
    TextView authorTxt;
    ImageView headImg;


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
        headImg = (ImageView) view.findViewById(R.id.answer_detail_img_head);
        headImg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), UserInfoDetailActivity.class);
        startActivity(intent);
    }
}
