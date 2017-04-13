package cn.lawliex.ask.admin.answer;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2017/4/9.
 */

public interface AdminAnswerContract {

    interface Presenter extends BasePresenter{
        void loadAnswers();
    }
    interface View extends BaseView<Presenter>{
        void showAnswers(List<Answer> questionList);
    }
}
