package cn.lawliex.ask.following.user;

import android.app.Activity;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowUserContract {
    public interface Presenter extends BasePresenter{
        void loadFollowers();
    }
    public interface View extends BaseView<Presenter>{
        void showFollowers(List<User> users);
        void showErrorMessage(String errMsg);
        void update();
        void setActivity(Activity activity);
        Activity getAct();
    }
}
