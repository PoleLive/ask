package cn.lawliex.ask.follow.user;

import android.app.Activity;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.UserInfo;
import cn.lawliex.ask.message.detail.MessageDetailContract;

/**
 * Created by Terence on 2017/1/16.
 */

public class FollowUserContract {
    interface Presenter extends BasePresenter{
        void loadUsers();
        void followered();
    }
    interface View extends BaseView<Presenter>{
        void showUsersView(List<UserInfo> users);
        Activity getAct();
        void setAct(Activity act);
        void showErrorMessage(String errMsg);

    }
}
