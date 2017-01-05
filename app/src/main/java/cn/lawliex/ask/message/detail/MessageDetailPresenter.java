package cn.lawliex.ask.message.detail;

import android.text.style.SubscriptSpan;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.local.MessageDbHelper;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageDetailPresenter implements MessageDetailContract.Presenter {
    MessageDetailContract.View view;
    Thread checkingNewMessage;
    MessageDbHelper dbHelper;
    List<Message> messageList = new ArrayList<Message>();
    public Boolean isAlive = false;
    public MessageDetailPresenter(MessageDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
        dbHelper = new MessageDbHelper(view.getActivity());
        start();
        checkingNewMessage=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(isAlive) {
                    isAlive = true;
                }
                while(isAlive){
                    try {
                        Thread.sleep(5000);
                        loadMessageDetail();
                    }catch (InterruptedException e){
                        e.getMessage();
                    }
                }
            }
        });
        checkingNewMessage.start();
    }

    @Override
    public void loadMessageDetail() {
        int userId = view.getActivity().getIntent().getIntExtra("toId",0);
        messageList =  dbHelper.getMessage(userId);
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("id",  userId + "");

        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {
                view.showMessageDetail(messageList);
            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMessage(e.getMessage());
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    JSONArray array = jsonObject.getJSONArray("datas");
                    for(int i = 0; i < array.size(); i++){
                        Message m = array.getJSONObject(i).toJavaObject(Message.class);
                        messageList.add(m);
                        dbHelper.insert(m);
                    }

                }else{
                    view.showErrorMessage("error");
                }
            }
        }).post(UrlContract.MESSAGE_DETAIL,map);
    }

    @Override
    public void sendMessage() {
        final Message message = view.getMessageToSend();
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
                    Message m = jsonObject.getJSONObject("data").toJavaObject(Message.class);
                    dbHelper.insert(m);
                    start();
                }
            }
        }).post(UrlContract.MESSAGE_ADD,map);
    }
    @Override
    public void onDestroy() {
        synchronized(isAlive){
            isAlive = false;
        }
    }
    @Override
    public void start() {
        loadMessageDetail();
    }
}
