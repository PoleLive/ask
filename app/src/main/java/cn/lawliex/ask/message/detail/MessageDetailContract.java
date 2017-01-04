package cn.lawliex.ask.message.detail;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Message;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageDetailContract {
    public interface Presenter extends BasePresenter {
        void loadMessageDetail();
        void sendMessage();
    }
    public interface View extends BaseView<Presenter> {
        void showMessageDetail(List<Message> messageList);
        void showErrorMessage(String errMsg);
        Message getMessageToSend();

    }
}
