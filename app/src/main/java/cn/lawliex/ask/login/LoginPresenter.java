package cn.lawliex.ask.login;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import cn.lawliex.ask.ApplicationContract;
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
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoginFail();
                    }

                    @Override
                    public void onNext(User user) {
                        callback.onLoginSuccess(user);
                    }
                })
                .post("lawliex",map);
    }
}
