package cn.lawliex.ask.profile.other.detail;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.UserInfo;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfoDetailContract {
    public interface Presenter extends BasePresenter {
        void loadUserInfo(int userId);
        void follow(int userId);
        void unFollow(int userId);
        void sendMessage(Message message);
        void loadMessage(int toId);
    }
    public interface View extends BaseView<Presenter> {
        void showUserInfo(UserInfo userInfo);
        void showErrorMessage(String errMsg);

    }
}
