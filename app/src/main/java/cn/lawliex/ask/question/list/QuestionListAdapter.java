package cn.lawliex.ask.question.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.login.LoginContract;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/28.
 */

public class QuestionListAdapter extends BaseAdapter {
    private Context context;
    private List<Question> datas;
    public QuestionListAdapter(Context context,List<Question> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Question getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.question_list_item, parent, false);
            holder.photo = (ImageView) convertView.findViewById(R.id.question_list_user_head);
            holder.usernameTxt = (TextView) convertView.findViewById(R.id.question_list_user_id);
            holder.titleTxt = (TextView) convertView.findViewById(R.id.question_list_title);
            holder.answerTxt = (TextView) convertView.findViewById(R.id.question_list_answer);
            holder.likeTxt = (TextView) convertView.findViewById(R.id.question_list_like);
            holder.commentCountTxt = (TextView) convertView.findViewById(R.id.question_list_comment_count);
            holder.likeStrTxt = (TextView)convertView.findViewById(R.id.question_list_like_str);
            holder.followQuestionTxt = (TextView)convertView.findViewById(R.id.question_list_follow_question);
            checkIsFollower(holder.followQuestionTxt,position);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        final Question q = datas.get(position);
        holder.titleTxt.setText(q.getTitle());
        holder.answerTxt.setText(q.getContent());
        holder.commentCountTxt.setText(q.getCommentCount()+"");
        holder.usernameTxt.setText(q.getName());
        holder.likeTxt.setText(q.getLikeCount()+"");
        holder.likeTxt.setOnClickListener(getOnClickListener(holder.likeTxt,position));
        holder.likeStrTxt.setOnClickListener(getOnClickListener(holder.likeTxt,position));
        holder.followQuestionTxt.setOnClickListener(getOnClickListener(holder.followQuestionTxt,position));
        holder.photo.setOnClickListener(getOnClickListener( holder.photo,position));
        return convertView;
    }
    void checkIsFollower(final TextView txt, int i){
        Question q = datas.get(i);
        Map<String, String> map = AskHelper.getRequestMap(context);
        map.put("entityType","1");
        map.put("entityId",q.getId() + "");
        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(JSONObject jsonObject) {
                if(jsonObject.getInteger("code")==0){
                    txt.setText("已关注");
                }
            }
        }).post(UrlContract.ISFOLLOWER,map);
    }
    View.OnClickListener getOnClickListener(final View view, final int i){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Question q = datas.get(i);
                Map<String, String> map = AskHelper.getRequestMap(context);
                map.put("entityType","1");
                map.put("entityId",q.getId() + "");
                String path = "";
                switch (v.getId()){
                    case R.id.question_list_user_head:
                        Intent intent = new Intent(context, UserInfoDetailActivity.class);
                        intent.putExtra("userId",datas.get(i).getUserId());
                        context.startActivity(intent);
                        return;
                    case R.id.question_list_like:case R.id.question_list_like_str:
                        path = UrlContract.LIKE_LIKE;
                        break;
                    case R.id.question_list_follow_question:
                        path = UrlContract.ISFOLLOWERED;
                        break;
                }
                HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(JSONObject jsonObject) {
                        if(jsonObject.getInteger("code")== 1){

                            ((TextView)view).setText("已关注");
                        }else if(jsonObject.getInteger("code") == 0){

                            switch (v.getId()){
                                case R.id.question_list_like:case R.id.question_list_like_str:
                                    TextView txt = (TextView)view;
                                    if(jsonObject.getInteger("code")==0){
                                        txt.setText(""+jsonObject.getInteger("count"));
                                    }
                                    break;
                                case R.id.question_list_follow_question:
                                    ((TextView)view).setText("关注问题");
                                    break;
                            }
                        }

                    }
                }).post(path,map);
            }
        };
        return listener;
    }

    public void update(String s){

    }
    class ViewHolder{
        ImageView photo;
        TextView usernameTxt;
        TextView titleTxt;
        TextView answerTxt;
        TextView likeTxt;
        TextView commentCountTxt;
        TextView likeStrTxt;
        TextView followQuestionTxt;
    }
}
