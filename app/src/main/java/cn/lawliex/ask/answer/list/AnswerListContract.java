package cn.lawliex.ask.answer.list;

import android.app.Activity;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerListContract {
    public interface Presenter extends BasePresenter{
        void loadAnswerList(int questionId);
    }
    public interface View extends BaseView<Presenter>{
        Activity getActivity();
        void showErrorMessage(String errMsg);
        void showAnswerList(List<Answer> answerList);
    }
}
