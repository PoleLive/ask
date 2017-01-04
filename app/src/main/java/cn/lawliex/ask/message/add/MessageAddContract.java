package cn.lawliex.ask.message.add;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Message;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageAddContract {
    interface Presenter extends BasePresenter{
        void sendMessage();
    }
    interface View extends BaseView<Presenter> {
        Message getMessage();
        void toMessageDetail();
        void showErrorMessage(String errMsg);
    }
}
