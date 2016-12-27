package cn.lawliex.ask.login;

import android.support.annotation.NonNull;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2016/12/27.
 */

public interface LoginContract {
    interface LoginCallback{
        void onLoginSuccess(User user);
        void onLoginFail();
    }
    interface Presenter extends BasePresenter{
        void login(@NonNull String username,@NonNull String password,@NonNull LoginCallback callback);

    }
    interface View extends BaseView<Presenter>{

    }
}
