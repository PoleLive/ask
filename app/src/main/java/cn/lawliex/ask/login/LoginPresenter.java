package cn.lawliex.ask.login;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import cn.lawliex.ask.ApplicationContract;
import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.LoginResponse;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/27.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @Override
    public void start() {

    }
    @Override
    public void login(@NonNull String username, @NonNull String password, @NonNull final LoginContract.LoginCallback callback) {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        HttpRequests.getInstance()
                .baseUrl(ApplicationContract.SERVER_ADDRESS)
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onLoginFail(e.getMessage());
                    }
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.getCode() == 0)
                            callback.onLoginSuccess(response);
                        else
                            callback.onLoginFail(response.getMsg());
                    }
                })
                .post("login",map);
    }

    @Override
    public void register(@NonNull String username, @NonNull String password, @NonNull final LoginContract.RegisterCallback callback) {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);

        HttpRequests.getInstance()
                .baseUrl(ApplicationContract.SERVER_ADDRESS)
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        callback.onRegisterFail(e.getMessage());
                    }
                    @Override
                    public void onNext(BaseResponse response) {
                        if(response.getCode() == 0)
                            callback.onRegisterSuccess(response);
                        else
                            callback.onRegisterFail(response.getMsg());
                    }
                })
                .post("register",map);
    }
}
