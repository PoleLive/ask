package cn.lawliex.ask.message.list;

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

public class MessageListPresenter implements MessageListContract.Presenter {
    MessageListContract.View view;

    public MessageListPresenter(MessageListContract.View view) {
        this.view = view;
        view.setPresenter(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(3000);
                        loadMessages();
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }
        }).start();

    }

    @Override
    public void loadMessages() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
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
                    view.showMessagesView(messageList);
                }else{
                    view.showErrorMessage("error");
                }
            }
        }).post(UrlContract.MESSAGE_LIST,map);
    }

    @Override
    public void start() {
        loadMessages();
    }
}
