package cn.lawliex.ask.profile.other.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;
import cn.lawliex.ask.profile.other.list.UserInfoListContract;

public class UserInfoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_detail);
        UserInfoDetailFragment fragment = (UserInfoDetailFragment) getFragmentManager().findFragmentById(R.id.user_info_detail_fragment);
        UserInfoDetailContract.Presenter presenter = new UserInfoDetailPresenter(fragment);


    }
}
