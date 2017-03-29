package cn.lawliex.ask.answer.add;

import android.app.Activity;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerAddContract {
    interface Presenter extends BasePresenter{
        void submitAnswer(Answer answer);
    }
    interface View extends BaseView<Presenter>{
        Activity getActivity();
        Answer getAnswer();
        void showErrorMessage(String errMsg);
        void toQuestionDetail(int questionId);
    }
}
