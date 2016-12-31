package cn.lawliex.ask.answer.detail;

import android.content.Intent;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.comment.CommentActivity;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerDetailPresenter implements AnswerDetailContract.Presenter {
    AnswerDetailContract.View view;

    public AnswerDetailPresenter(AnswerDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void loadAnswer() {
        Map<String, String> map = AskHelper.getRequestMap(view.getActivity());
        int id = view.getActivity().getIntent().getIntExtra("answerId",0);
        map.put("id",id + "");
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
                    Answer answer = jsonObject.getJSONObject("data").toJavaObject(Answer.class);
                    view.showAnswer(answer);
                }
            }
        }).post(UrlContract.ANSWER_DETAIL, map);
    }

    @Override
    public void like(int commentId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityType","2");
        map.put("entityId",""+commentId);
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
                    loadAnswer();
                }
            }
        }).post(UrlContract.LIKE_LIKE,map);
    }

    @Override
    public void collect(int commentId) {

    }

    @Override
    public void comment(int commentId) {


    }

    @Override
    public void start() {
        loadAnswer();
    }
}
