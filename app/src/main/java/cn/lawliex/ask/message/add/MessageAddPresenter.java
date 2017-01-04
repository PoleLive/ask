package cn.lawliex.ask.message.add;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageAddPresenter implements MessageAddContract.Presenter {
    MessageAddContract.View view;

    public MessageAddPresenter(MessageAddContract.View view) {
        this.view = view;
        view.setPresenter(this);
        //start();
    }

    @Override
    public void sendMessage() {
        Message message = view.getMessage();
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());
        map.put("toId",message.getToId() + "");
        map.put("content",message.getContent());
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
                    view.showErrorMessage("success");
                    view.toMessageDetail();
                    view.getActivity().finish();
                }
            }
        }).post(UrlContract.MESSAGE_ADD,map);
    }

    @Override
    public void start() {
        sendMessage();
    }
}
