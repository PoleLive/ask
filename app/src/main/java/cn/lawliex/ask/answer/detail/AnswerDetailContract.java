package cn.lawliex.ask.answer.detail;

import android.app.Activity;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerDetailContract {
    public interface Presenter extends BasePresenter{
        void loadAnswer();
    }
    public interface View extends BaseView<Presenter>{
        Activity getActivity();
        void showAnswer(Answer answer);
    }
}
