package cn.lawliex.ask.login;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import cn.lawliex.ask.ApplicationContract;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Result;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/27.
 */

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View loginView;
    @Override
    public void start() {
        Map<String,String> map = new HashMap<>();
        String ticket = loginView.getTicket();
        if(loginView.getTicket()!=null){
            map.put("ticket",ticket);
            HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
                @Override
                public void onCompleted() {
                }
                @Override
                public void onError(Throwable e) {
                }
                @Override
                public void onNext(JSONObject jsonObject) {
                    if(jsonObject.getInteger("code")== 0){
                        loginView.toMainAct();
                    }
                }
            }).post(UrlContract.TEST_LOGINED, map);
        }

    }
    public LoginPresenter(@NonNull LoginContract.View loginView){
        this.loginView = loginView;
    }
    @Override
    public void login(@NonNull String username, @NonNull String password) {

        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        HttpRequests.getInstance()
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        loginView.setErrorMessage(e.getMessage());
                    }
                    @Override
                    public void onNext(JSONObject response) {
                        Result<User> result = new Result<User>(response,User.class);
                        if(result.getCode() == 0) {
                            loginView.setErrorMessage("success");
                            String ticket = result.getTicket();
                            loginView.saveTicket(ticket);
                            User user = result.getData();
                            loginView.saveUser(user);
                            loginView.toMainAct();
                        }
                        else
                            loginView.setErrorMessage(result.getMsg());
                    }
                })
                .post(UrlContract.LOGIN_PATH,map);
    }
    @Override
    public void register(@NonNull String username, @NonNull String password) {
        Map<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        HttpRequests.getInstance()
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        loginView.setErrorMessage(e.getMessage());
                    }
                    @Override
                    public void onNext(JSONObject response) {
                        Result<User> result = new Result<User>(response,User.class);
                        loginView.setErrorMessage(response.getString("msg"));
                        String ticket = result.getTicket();
                        loginView.saveTicket(ticket);
                        User user = result.getData();
                        loginView.saveUser(user);
                        loginView.toMainAct();
                    }
                })
                .post(UrlContract.REGISTER_PATH,map);
    }
}
