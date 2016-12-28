package cn.lawliex.ask.question.add;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionAddContract {
    interface QuestionSubmitCallback{
        void onSubmitSuccess(Question question);
        void onSubmitFail(String errMsg);
    }
    interface Presenter extends BasePresenter{
        void submitQuestion(Question question, QuestionSubmitCallback callback);
    }
    interface View extends BaseView<Presenter>{
        Question getQuestion();
        void toDetailAct();
        void showCreatingView(int progress);
        void showErrorView(String errMsg);
    }
}
