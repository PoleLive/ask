package cn.lawliex.ask.question.list;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionListContract {
    interface LoadQuestionsCallback{
        void onLoadSuccess(List<Question> questions);
        void onLoadFail(String errMsg);
    }
    interface Presenter extends BasePresenter{
        void loadQuestionList(LoadQuestionsCallback callback);

    }
    interface View extends BaseView<Presenter>{
        void showErrorView(String errMsg);
        void showQuestionList(Question question);
        void showLoadingView(int progress);
        void toDetailAct(int id);

    }
}
