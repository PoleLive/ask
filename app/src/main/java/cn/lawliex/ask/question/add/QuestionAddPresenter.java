package cn.lawliex.ask.question.add;

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

public class QuestionAddPresenter implements QuestionAddContract.Presenter {

    QuestionAddContract.View questionAddView;

    public QuestionAddPresenter(QuestionAddContract.View questionAddView){
        this.questionAddView = questionAddView;
        this.questionAddView.setPresenter(this);
    }

    @Override
    public void submitQuestion(Question question) {
        Map<String, String> map = AskHelper.getRequestMap(questionAddView.getActivity());
        map.put("title",question.getTitle());
        map.put("content",question.getContent());
        map.put("userId",question.getUserId() + "");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                questionAddView.showErrorView("error");
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    questionAddView.toDetailAct();
                    questionAddView.getActivity().finish();
                }else{
                    questionAddView.showErrorView("error");
                }

            }
        }).post(UrlContract.QUESTION_ADD, map);
    }

    @Override
    public void start() {
        Question question = questionAddView.getQuestion();
        submitQuestion(question);

    }
}
