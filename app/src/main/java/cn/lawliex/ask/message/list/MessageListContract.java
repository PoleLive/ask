package cn.lawliex.ask.message.list;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Message;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageListContract {
    interface Presenter extends BasePresenter{
        void loadMessages();
    }
    interface View extends BaseView<Presenter> {
        void showMessagesView(List<Message> messageList);
        void showErrorMessage(String errMsg);
        void update();
    }
}
