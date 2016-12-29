package cn.lawliex.ask.answer.add;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerAddPresenter implements AnswerAddContract.Presenter {
    AnswerAddContract.View view;
    @Override
    public void submitAnswer(Answer answer) {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("questionId",answer.getEntityId()+"");
        map.put("userId",answer.getUserId()+"");
        map.put("content",answer.getContent());
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
                    view.toQuestionDetail(view.getAnswer().getEntityId());
                    view.getActivity().finish();
                }else{
                    view.showErrorMessage("error");
                }

            }
        }).post(UrlContract.ANSWER_ADD, map);
    }

    public AnswerAddPresenter(AnswerAddContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        submitAnswer(view.getAnswer());
    }
}
