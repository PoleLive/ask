package cn.lawliex.ask.message.detail;

import android.text.style.SubscriptSpan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageDetailPresenter implements MessageDetailContract.Presenter {
    MessageDetailContract.View view;

    public MessageDetailPresenter(MessageDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5000);
                        loadMessageDetail();
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            }
        }).start();
    }


    @Override
    public void loadMessageDetail() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("id", + view.getActivity().getIntent().getIntExtra("toId",0) + "");

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
                    List<Message> messageList = new ArrayList<Message>();
                    JSONArray array = jsonObject.getJSONArray("datas");
                    for(int i = 0; i < array.size(); i++){
                        Message m = array.getJSONObject(i).toJavaObject(Message.class);
                        messageList.add(m);
                    }
                    view.showMessageDetail(messageList);
                }else{
                    view.showErrorMessage("error");
                }
            }
        }).post(UrlContract.MESSAGE_DETAIL,map);
    }

    @Override
    public void sendMessage() {
        Message message = view.getMessageToSend();
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("toId",message.getToId() + "");
        map.put("content",message.getContent());
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
                    start();
                }
            }
        }).post(UrlContract.MESSAGE_ADD,map);
    }

    @Override
    public void start() {
        loadMessageDetail();
    }
}
