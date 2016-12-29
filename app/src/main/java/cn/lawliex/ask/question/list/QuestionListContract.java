package cn.lawliex.ask.question.list;

import android.content.Context;

import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;

/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionListContract {

    interface Presenter extends BasePresenter{
        void loadQuestionList();

    }
    interface View extends BaseView<Presenter>{
        Context getContext();
        void showErrorView(String errMsg);
        void showLoadSuccessView(String msg);
        void showQuestionList(Question question);
        void showLoadingView(int progress);
        void toDetailAct(int id);
        void setListDatas(List<Question> questions);

    }
}
