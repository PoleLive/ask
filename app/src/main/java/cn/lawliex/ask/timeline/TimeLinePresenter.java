package cn.lawliex.ask.timeline;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Feed;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/6.
 */

public class TimeLinePresenter implements TimeLineContract.Presenter {
    TimeLineContract.View view;

    public TimeLinePresenter(TimeLineContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void loadFeeds() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        final List<Feed> feeds = new ArrayList<>();
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {
                view.showFeedsView(feeds);
            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMessage(e.getMessage());
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    JSONArray array = jsonObject.getJSONArray("feeds");
                    for(int i = 0; i < array.size(); i++){
                        Feed feed = array.getJSONObject(i).toJavaObject(Feed.class);
                        feeds.add(feed);
                    }
                }
            }
        }).post(UrlContract.FEEDS_PULL, map);
    }

    @Override
    public void collect() {

    }

    @Override
    public void follow() {

    }

    @Override
    public void start() {
        loadFeeds();
    }
}
