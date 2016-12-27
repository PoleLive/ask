package cn.lawliex.ask.data.source;

import android.support.annotation.NonNull;

import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2016/12/27.
 */

public interface UserDataSource {
    interface LoginCallback{
        void onLoginSuccess(User user);
        void onLoginFail();
    }
    void getUser(@NonNull int id,@NonNull LoginCallback callback);
}
