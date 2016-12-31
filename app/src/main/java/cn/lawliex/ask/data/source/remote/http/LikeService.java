package cn.lawliex.ask.data.source.remote.http;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/31.
 */

public class LikeService {
    Context context;

    public LikeService(Context context, int entityType, int entityId, final BaseView view) {
        this.context = context;
        Map<String,String> map = AskHelper.getRequestMap(context);
        map.put("entityType",entityType + "");
        map.put("entityId",entityId + "");
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

                }
            }
        }).post(UrlContract.LIKE_LIKE,map);

    }


}
