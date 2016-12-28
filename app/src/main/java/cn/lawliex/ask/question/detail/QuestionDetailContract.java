package cn.lawliex.ask.question.detail;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Question;


/**
 * Created by Terence on 2016/12/28.
 */

public interface QuestionDetailContract {
    interface LoadQuestionCallback{
        void onLoadSuccess(Question question);
        void onLoadFail(String errMsg);
    }
    interface FollowCallback{
        void onFollowSuccess(int entityId, int entityType);
        void onFollowFail(String errMsg);
    }
    interface Presenter extends BasePresenter{
        void loadQuestion(LoadQuestionCallback callback);
        void followQuestion(FollowCallback callback);
        void followUser(FollowCallback callback);
    }
    interface View extends BaseView<Presenter>{
        void showErrorView(String errMsg);
        void showQuestionDetail(Question question);
        void showLoadingView(int progress);
    }
}
