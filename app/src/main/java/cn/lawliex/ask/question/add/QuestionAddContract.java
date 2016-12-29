package cn.lawliex.ask.question.add;

import android.app.Activity;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionAddContract {
    interface Presenter extends BasePresenter{
        void submitQuestion(Question question);
    }
    interface View extends BaseView<Presenter>{
        Question getQuestion();
        void toDetailAct();
        void showCreatingView(int progress);
        void showErrorView(String errMsg);
        Activity getActivity();
    }
}
