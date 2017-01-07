package cn.lawliex.ask.answer.list_plus;


import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/31.
 */

public class QandAListPresenter implements QandAListContract.Presenter {
    QandAListContract.View view;

    public QandAListPresenter(QandAListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadAnswers() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        String path = UrlContract.Q_AND_A_LIST;
        doRequest(path,map);
    }

    public void doRequest(String path, Map<String,String> map){
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
                    JSONArray array = jsonObject.getJSONArray("answers");
                    List<Answer> answerList = new ArrayList<Answer>();
                    for(int i = 0; i < array.size(); i++){
                        Answer answer = array.getJSONObject(i).toJavaObject(Answer.class);
                        answerList.add(answer);
                    }
                    view.showAnswers(answerList);
                }
            }
        }).post(path,map);
    }


    @Override
    public void loadUserAnswers(int userId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("userId",userId + "");
        String path = UrlContract.Q_AND_A_USER_LIST;
        doRequest(path,map);

    }
    @Override
    public void start() {
        int userId = view.getActivity().getIntent().getIntExtra("userId",0);
        if(userId != 0){
            loadUserAnswers(userId);
        }else
            loadAnswers();
    }
}
