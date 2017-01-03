package cn.lawliex.ask.followers;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowersContract {
    interface Presenter extends BasePresenter{
        void loadFollowers();
    }
    interface View extends BaseView<Presenter>{
        void showFollowers(List<User> users);
        void showErrorMessage(String errMsg);
        void update();
    }
}
