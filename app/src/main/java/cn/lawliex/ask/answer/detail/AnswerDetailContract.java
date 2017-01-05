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
        void like(int commentId);
        void collect(int commentId);
        void comment(int commentId);
        void checkCollected();
    }
    public interface View extends BaseView<Presenter>{
        void showAnswer(Answer answer);
        void showErrorMessage(String errMsg);
        void toCommentActivity(int id);
        void changCollectionTxt(boolean collected);
    }
}
