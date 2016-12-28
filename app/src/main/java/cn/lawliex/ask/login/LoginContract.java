package cn.lawliex.ask.login;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.LoginResponse;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.UserDataSource;

/**
 * Created by Terence on 2016/12/27.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login(@NonNull String username,@NonNull String password);
        void register(@NonNull String username, @NonNull String password);

    }
    interface View extends BaseView<Presenter>{
        String getUsername();
        String getPassword();
        boolean isPasswordValid(String password);
        boolean isUsernameValid(String username);
        void toMainAct();
        void saveUser(User user);
        void saveTicket(String ticket);
        String getTicket();
        void setErrorMessage(String error);
        void showLoginProgress(boolean show);
    }
}
