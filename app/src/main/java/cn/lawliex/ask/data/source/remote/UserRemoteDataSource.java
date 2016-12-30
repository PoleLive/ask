package cn.lawliex.ask.data.source.remote;

import android.support.annotation.NonNull;

import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.UserDataSource;

/**
 * Created by Terence on 2016/12/27.
 */

public class UserRemoteDataSource implements UserDataSource {
    @Override
    public void getUser(@NonNull int id, @NonNull LoginCallback callback) {

    }

    @Override
    public void saveUser(@NonNull User user) {

    }

    @Override
    public void saveTicket(String ticket) {

    }

    @Override
    public String getString(String key, String defaultStr) {
        return null;
    }

    @Override
    public String getTicket() {
        return null;
    }
}
