package cn.lawliex.ask.admin.comment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Comment;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/31.
 */

public class CommentPresenter implements CommentContract.Presenter {
    CommentContract.View view;

    public CommentPresenter(CommentContract.View view) {
        this.view = view;
        view.setPresenter(this);
        start();
    }

    @Override
    public void loadComments() {
        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());

        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                view.showErrorMsg(e.getMessage());
            }
            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    JSONArray array = jsonObject.getJSONArray("comments");
                    List<Comment> comments = new ArrayList<Comment>();
                    for(int i = 0; i < array.size();i++){
                        Comment comment = array.getJSONObject(i).toJavaObject(Comment.class);
                        comments.add(comment);
                    }
                    view.showComments(comments);
                    return;
                }
                view.showErrorMsg("error");

            }
        }).post(UrlContract.COMMENT_ALL,map);

    }

    @Override
    public void addComment() {

        Map<String,String> map = AskHelper.getRequestMap(view.getActivity());

        map.put("content",view.getSendContent());
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showErrorMsg(e.getMessage());
            }

            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code") == 0){
                    view.showErrorMsg("评论成功");
                    loadComments();
                }
            }
        }).post(UrlContract.COMMENT_ADD,map);

    }

    @Override
    public void start() {

        loadComments();
    }
}
