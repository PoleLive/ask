package cn.lawliex.ask.answer.list;

import android.bluetooth.BluetoothAssignedNumbers;
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
 * Created by Terence on 2016/12/29.
 */

public class AnswerListPresenter implements AnswerListContract.Presenter {
    AnswerListContract.View view;

    public AnswerListPresenter(AnswerListContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void loadAnswerList(int questionId) {
        Map<String, String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("questionId",questionId+"");
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
                    List<Answer> answers = new ArrayList<Answer>();
                    JSONArray array = jsonObject.getJSONArray("comments");
                    for(int i = 0; i < array.size(); i++){
                        Answer answer = array.getJSONObject(i).toJavaObject(Answer.class);
                        answers.add(answer);
                    }
                    view.showAnswerList(answers);
                }
            }
        }).post(UrlContract.ANSWER_LIST, map);
    }

    @Override
    public void start() {
        int questionId = view.getActivity().getIntent().getIntExtra("questionId",0);
        loadAnswerList(questionId);
    }
}
