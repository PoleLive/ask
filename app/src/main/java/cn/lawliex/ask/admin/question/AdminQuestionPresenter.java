package cn.lawliex.ask.admin.question;

import android.app.Activity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/4/9.
 */

public class AdminQuestionPresenter implements AdminQuestionContract.Presenter {
    AdminQuestionContract.View view;

    public AdminQuestionPresenter(AdminQuestionContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        start();
    }

    @Override
    public void start() {
        loadQuestions();
    }

    @Override
    public void loadQuestions() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        String path = UrlContract.QUESTION_ADMIN_LIST;
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
                    JSONArray array = jsonObject.getJSONArray("questions");
                    List<Question> list = new ArrayList<>();
                    for(int i = 0; i < array.size();i++){
                        Question question = array.getJSONObject(i).toJavaObject(Question.class);
                        list.add(question);
                    }
                    view.showQuestions(list);

                }
            }
        }).post(path,map );
    }
}
