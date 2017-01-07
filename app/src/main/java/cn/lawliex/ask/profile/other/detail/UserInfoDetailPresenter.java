package cn.lawliex.ask.profile.other.detail;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.UserInfo;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.profile.other.list.UserInfoListContract;
import cn.lawliex.ask.util.AskHelper;
import cn.lawliex.ask.util.GlideImageLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfoDetailPresenter implements UserInfoDetailContract.Presenter {
    UserInfoDetailContract.View view;
    public static final String TAG = "UserInfo";


    public UserInfoDetailPresenter(UserInfoDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);

        start();

    }

    @Override
    public void start() {
        int userId = view.getActivity().getIntent().getIntExtra("userId",0);
        loadUserInfo(userId);
        isFollowing(userId);
    }

    @Override
    public void loadUserInfo(int userId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("userId",userId + "");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMessage(e.getMessage());
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0 ) {
                    UserInfo userInfo = jsonObject.getJSONObject("userInfo").toJavaObject(UserInfo.class);
                    view.showUserInfo(userInfo);
                }
            }
        }).post(UrlContract.USREINFO,map);
    }

    @Override
    public void follow(int userId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityId",userId + "");
        map.put("entityType","0");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    view.changeFollowBnState(false);
                }else if(jsonObject.getInteger("code") == 1){
                    view.changeFollowBnState(true);
                }
            }
        }).post(UrlContract.ISFOLLOWERED,map);
    }

    @Override
    public void isFollowing(int userId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityId",userId + "");
        map.put("entityType","0");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    view.changeFollowBnState(true);
                }
            }
        }).post(UrlContract.ISFOLLOWER,map);
    }



    @Override
    public void sendMessage(Message message) {

    }

    @Override
    public void loadMessage(int toId) {

    }

    @Override
    public void updateInfo() {
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if (jsonObject.getInteger("code") == 0){
                    User user = jsonObject.getJSONObject("user").toJavaObject(User.class);
                    UserLocalDataSource.getInstance(view.getActivity()).saveUser(user);
                }
            }
        }).post("user/update",AskHelper.getRequestMap(view.getActivity()));
    }
}
