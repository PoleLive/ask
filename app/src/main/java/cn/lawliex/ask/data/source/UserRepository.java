package cn.lawliex.ask.data.source;

import android.support.annotation.NonNull;

import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2016/12/28.
 */

public class UserRepository implements UserDataSource {
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
    public String getTicket() {
        return null;
    }
}
