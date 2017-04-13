package cn.lawliex.ask.admin.question;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2017/4/9.
 */

public interface AdminQuestionContract {

    interface Presenter extends BasePresenter{
        void loadQuestions();
    }
    interface View extends BaseView<Presenter>{
        void showQuestions(List<Question> questionList);
    }
}
