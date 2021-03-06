package cn.lawliex.ask.following.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.followers.FollowersContract;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowUserPresenter implements FollowUserContract.Presenter {
    FollowUserContract.View view;

    public FollowUserPresenter(FollowUserContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void loadFollowers() {
        Map<String,String> map = AskHelper.getRequestMap(view.getAct());
        int userId = view.getAct().getIntent().getIntExtra("userId",0);
        map.put("entityType","0");
        map.put("entityId",userId + "");
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
                if(jsonObject.getInteger("code") == 0){
                    List<User> userList = new ArrayList<User>();
                    JSONArray array = jsonObject.getJSONArray("list");
                    for(int i = 0; i < array.size();i++){
                        User user = array.getJSONObject(i).toJavaObject(User.class);
                        userList.add(user);
                    }
                    view.showFollowers(userList);
                }
                else
                    view.showErrorMessage("error");
            }
        }).post(UrlContract.FOLLOWEES,map);
    }

    @Override
    public void start() {
        loadFollowers();
    }
}
