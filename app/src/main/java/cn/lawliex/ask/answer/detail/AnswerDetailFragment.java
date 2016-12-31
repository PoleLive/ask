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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.lawliex.ask.R;
import cn.lawliex.ask.comment.CommentActivity;
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
    TextView mottoTxt;
    LinearLayout likeLayout, collectionLayout, commentLayout;
    TextView likeCountTxt;
    TextView commentCountTxt;
    TextView collectionTxt;
    Answer answer;
    @Override
    public void showAnswer(Answer answer) {
        this.answer = answer;
        contentTxt.setText(answer.getContent());
        authorTxt.setText(answer.getAuthor());
        titleTxt.setText(answer.getQuestionTitle());
        likeCountTxt.setText(""+answer.getLikeCount());
        commentCountTxt.setText(""+answer.getCommentCount());

    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void toCommentActivity(int commentId) {
        Intent intent = new Intent(getActivity(), CommentActivity.class);
        intent.putExtra("answerId",commentId);
        startActivity(intent);
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
        mottoTxt = (TextView)view.findViewById(R.id.answer_detail_moto);
        likeCountTxt = (TextView)view.findViewById(R.id.answer_detail_like_count);
        commentCountTxt = (TextView)view.findViewById(R.id.answer_detail_txt_comment_count);
        collectionTxt  = (TextView)view.findViewById(R.id.answer_detail_txt_collection);
        likeLayout = (LinearLayout)view.findViewById(R.id.answer_detail_like_count_layout);
        commentLayout = (LinearLayout) view.findViewById(R.id.answer_detail_layout_comment_count);
        collectionLayout = (LinearLayout)view.findViewById(R.id.answer_detail_collection);
        initLisener();

        return view;
    }
    public void initLisener(){
        headImg.setOnClickListener(this);
        titleTxt.setOnClickListener(this);
        authorTxt.setOnClickListener(this);
        contentTxt.setOnClickListener(this);
        mottoTxt.setOnClickListener(this);
        likeCountTxt.setOnClickListener(this);
        commentCountTxt.setOnClickListener(this);
        likeLayout.setOnClickListener(this);
        commentLayout.setOnClickListener(this);
        collectionLayout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.answer_detail_img_head:
            case R.id.answer_detail_author:
            case R.id.answer_detail_moto:
                Intent intent = new Intent(getActivity(), UserInfoDetailActivity.class);
                intent.putExtra("userId",answer.getUserId());
                startActivity(intent);
                break;
            case R.id.answer_detail_layout_comment_count:
                toCommentActivity(answer.getId());
                break;
            case R.id.answer_detail_like_count_layout:
                presenter.like(answer.getId());
                break;
            case R.id.answer_detail_collection:

                break;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        presenter.start();
    }
}
