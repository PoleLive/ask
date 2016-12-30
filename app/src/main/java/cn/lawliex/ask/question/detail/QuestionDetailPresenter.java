package cn.lawliex.ask.question.detail;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/29.
 */

public class QuestionDetailPresenter implements QuestionDetailContract.Presenter {
    QuestionDetailContract.View view;
    @Override
    public void loadQuestion(int questionId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("id",questionId + "");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showErrorView(e.getMessage());
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    Question question = jsonObject.getJSONObject("data").toJavaObject(Question.class);
                    view.showQuestionDetail(question);

                }
                else{
                    view.showErrorView("error");
                }
            }
        }).post(UrlContract.QUESTION_DETAIL, map);

    }

    @Override
    public void followQuestion(int questionId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityType","1");
        map.put("entityId",questionId+"");
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
                    view.changeFollowBn(true);
                }
            }
        }).post(UrlContract.FOLLOW,map);
    }

    @Override
    public void followUser(int userId) {

    }

    @Override
    public void unFollowQuestion(int questionId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityType","1");
        map.put("entityId",questionId+"");
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
                    view.changeFollowBn(false);
                }
            }
        }).post(UrlContract.UNFOLLOWER,map);
    }

    @Override
    public void checkIsFollower(int questionId) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("entityType","1");
        map.put("entityId",questionId+"");
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
                    view.changeFollowBn(true);
                }
            }
        }).post(UrlContract.ISFOLLOWER,map);
    }

    @Override
    public void start() {
        int id = view.getActivity().getIntent().getIntExtra("questionId", 0);
        loadQuestion(id);
        checkIsFollower(id);
    }

    public QuestionDetailPresenter(QuestionDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }
}
