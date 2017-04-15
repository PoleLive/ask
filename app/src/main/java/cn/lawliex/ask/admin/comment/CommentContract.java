package cn.lawliex.ask.admin.comment;



import java.util.List;

import cn.lawliex.ask.BasePresenter;
import cn.lawliex.ask.BaseView;
import cn.lawliex.ask.data.Comment;

/**
 * Created by Terence on 2016/12/31.
 */

public class CommentContract {
    public interface Presenter extends BasePresenter{
        void loadComments();
        void addComment();

    }
    public interface View extends BaseView<Presenter>{
        void showComments(List<Comment> commentList);
        void showErrorMsg(String errMsg);
        String getSendContent();

    }
}
