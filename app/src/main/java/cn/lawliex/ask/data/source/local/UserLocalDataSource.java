package cn.lawliex.ask.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.UserDataSource;

/**
 * Created by Terence on 2016/12/27.
 */

public class UserLocalDataSource implements UserDataSource {
    private static UserLocalDataSource instance;
    SharedPreferencesHelper spHelper;
    @Override
    public void getUser(@NonNull int id, @NonNull LoginCallback callback) {
        User user = new User();
        user.setId(spHelper.getInt("id"));
        user.setEmail(spHelper.getString("email"));
        user.setPassword(spHelper.getString("password"));
        user.setSalt(spHelper.getString("salt"));
        user.setName(spHelper.getString("name"));
        user.setHeadUrl(spHelper.getString("headUrl"));
        if(user.getName() != null)
            callback.onLoginSuccess(user);
        else
            callback.onLoginFail();
    }
    private UserLocalDataSource(@NonNull Context context){
        spHelper = new SharedPreferencesHelper(context);
    }
    public static UserLocalDataSource getInstance(@NonNull Context context){
        if(instance == null){
            instance = new UserLocalDataSource(context);
        }
        return instance;
    }
    @Override
    public void saveUser(@NonNull User user){
        spHelper.saveInt("id",user.getId());
        spHelper.saveString("email",user.getEmail());
        spHelper.saveString("password",user.getPassword());
        spHelper.saveString("name",user.getName());
        spHelper.saveString("headUrl",user.getHeadUrl());
        spHelper.saveString("salt",user.getSalt());
        spHelper.saveString("headUrlLocal","");
    }

    @Override
    public void saveTicket(String ticket) {
        spHelper.saveString("ticket",ticket);
    }

    @Override
    public String getString(String key, String defaultStr) {
        if(spHelper.getString(key) != null){
            return spHelper.getString(key);
        }
        return defaultStr;
    }

    @Override
    public String getTicket() {
        return spHelper.getString("ticket");
    }

    public void logout(){

    }
}
