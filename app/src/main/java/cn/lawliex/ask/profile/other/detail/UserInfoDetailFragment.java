package cn.lawliex.ask.profile.other.detail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.joooonho.SelectableRoundedImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.UserInfo;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfoDetailFragment extends Fragment implements UserInfoDetailContract.View, View.OnClickListener {
    UserInfoDetailContract.Presenter presenter;
    SelectableRoundedImageView headImg;
    TextView nameTxt, mottoTxt, questionCountTxt, answerCountTxt, followerCountTxt, followeeCountTxt, likeCount;
    Button  followBn, sendMsgBn;
    UserInfo userInfo;
    @Override
    public void showUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        nameTxt.setText(userInfo.getName());
        mottoTxt.setText(userInfo.getMotto());
        questionCountTxt.setText(userInfo.getQuestionCount() + "");
        answerCountTxt.setText(userInfo.getAnswerCount() + "");
        followeeCountTxt.setText(userInfo.getFolloweeCount() + "");
        followerCountTxt.setText(userInfo.getFollowingCount() + "");
        likeCount.setText(userInfo.getLikeCount() + "");

    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeFollowBnState(boolean follow) {
        if(follow){
            followBn.setText("已关注");
        }else{
            followBn.setText("关注");
        }
    }

    @Override
    public void setPresenter(UserInfoDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_detail_fragment,container, false);
        sendMsgBn = (Button)view.findViewById(R.id.user_info_detail_bn_send_message) ;
        nameTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_name);
        questionCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_question_count);
        answerCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_answer_count);
        followerCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_follower_count);
        followeeCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_followee_count);
        likeCount = (TextView)view.findViewById(R.id.user_info_detail_txt_like_count);
        mottoTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_motto);
        followBn = (Button) view.findViewById(R.id.user_info_detail_txt_bn_follow_user);



        followBn.setOnClickListener(this);
        sendMsgBn.setOnClickListener(this);
        return view;
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
        switch (v.getId()){
            case R.id.user_info_detail_bn_send_message:

                break;
            case R.id.user_info_detail_txt_bn_follow_user:

                presenter.follow(userInfo.getId());
                break;
        }
    }
}
