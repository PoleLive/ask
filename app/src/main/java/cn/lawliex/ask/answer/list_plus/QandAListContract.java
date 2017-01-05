package cn.lawliex.ask.answer.list_plus;

import android.app.Activity;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/31.
 */

public interface QandAListContract {
    interface Presenter extends BasePresenter{
        void loadAnswers();
        void loadUserAnswers(int userId);
    }
    interface View extends BaseView<Presenter>{
        void update();
        void showAnswers(List<Answer> answerList);
        void setAct(Activity act);
        Activity getAct();
    }
}
