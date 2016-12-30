package cn.lawliex.ask.question.detail;

import android.app.Activity;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;


/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionDetailContract {

    interface Presenter extends BasePresenter{
        void loadQuestion(int questionId);
        void followQuestion(int questionId);
        void followUser(int userId);
        void unFollowQuestion(int questionId);
        void checkIsFollower(int questionId);
    }
    interface View extends BaseView<Presenter>{
        void showErrorView(String errMsg);
        void showQuestionDetail(Question question);
        void showLoadingView(int progress);
        void changeFollowBn(boolean follow);
        boolean getFollowBnStatus();
        Activity getActivity();
    }
}
