package cn.lawliex.ask.question.simplelist;

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
 * Created by Terence on 2017/4/2.
 */

public class QuestionSimpleListPresenter implements QuestionSimpleListContract.Presenter {
    QuestionSimpleListContract.View view;

    public QuestionSimpleListPresenter(QuestionSimpleListContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void loadQuestionList(int userId, Map<String, String> map) {
        String path = UrlContract.MY_QUESTION_LIST;
        map.put("userId",userId + "");
        doRequest(path,map);
    }

    @Override
    public void start() {
        int userId = view.getAct().getIntent().getIntExtra("userId",0);
        Map<String,String> map = AskHelper.getRequestMap(view.getContext());
        loadQuestionList(userId,map);
    }
    public void doRequest(String path,Map<String,String> map){

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
                    JSONArray array = jsonObject.getJSONArray("questions");
                    List<Question> list = new ArrayList<>();
                    for(int i = 0; i < array.size();i++){
                        Question question = array.getJSONObject(i).toJavaObject(Question.class);
                        list.add(question);
                    }
                    view.setListDatas(list);
                    view.showLoadSuccessView("加载完成");
                }
            }
        }).post(path,map );
    }
}
