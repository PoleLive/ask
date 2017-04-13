package cn.lawliex.ask.admin.answer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.admin.question.AdminQuestionContract;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/4/9.
 */

public class AdminAnswerPresenter implements AdminAnswerContract.Presenter {
    AdminAnswerContract.View view;

    public AdminAnswerPresenter(AdminAnswerContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        loadAnswers();
    }

    @Override
    public void loadAnswers() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        String path = UrlContract.Q_AND_A_LIST;
        doRequest(path,map);
    }
    public void doRequest(String path,Map<String,String> map){

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
                    List<Answer> list = new ArrayList<>();
                    for(int i = 0; i < array.size();i++){
                        Answer answer = array.getJSONObject(i).toJavaObject(Answer.class);
                        list.add(answer);
                    }
                    view.showAnswers(list);

                }
            }
        }).post(path,map );
    }


}
