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

import com.joooonho.SelectableRoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lawliex.ask.R;
import cn.lawliex.ask.data.UserInfo;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfoDetailFragment extends Fragment implements UserInfoDetailContract.View, View.OnClickListener {
    UserInfoDetailContract.Presenter presenter;
    SelectableRoundedImageView headImg;
    TextView nameTxt, mottoTxt, questionCountTxt, answerCountTxt, followerCountTxt, followeeCountTxt, likeCount;
    Button  followBn, sendMsgBn;

    @Override
    public void showUserInfo(UserInfo userInfo) {

    }

    @Override
    public void showErrorMessage(String errMsg) {

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
        }
    }
}
