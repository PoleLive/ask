package cn.lawliex.ask.question.list;

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
 * Created by Terence on 2016/12/28.
 */

public class QuestionListPresenter implements QuestionListContract.Presenter {
    QuestionListContract.View questionListView;

    @Override
    public void loadQuestionList() {
        Map<String,String> map = AskHelper.getRequestMap(questionListView.getContext());
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                questionListView.showErrorView(e.getMessage());
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
                    questionListView.setListDatas(list);
                    questionListView.showLoadSuccessView("加载完成");
                }
            }
        }).post(UrlContract.QUESTION_LIST,map );
    }

    @Override
    public void start() {
        loadQuestionList();
    }

    public QuestionListPresenter(QuestionListContract.View questionListView) {
        this.questionListView = questionListView;
        questionListView.setPresenter(this);
        //start();
    }
}
