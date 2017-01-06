package cn.lawliex.ask.timeline;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.EventType;
import cn.lawliex.ask.data.Feed;
import cn.lawliex.ask.data.Question;

import static cn.lawliex.ask.data.EventType.ANSWER;

/**
 * Created by Terence on 2017/1/6.
 */

public class TimeLineAdapter extends BaseAdapter {
    TimeLineFragment fragment;
    List<Feed> datas;

    public TimeLineAdapter(TimeLineFragment fragment, List<Feed> datas) {
        this.fragment = fragment;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Feed getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder h = null;
        if(convertView == null){
            convertView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.adapter_time_line_item, parent, false);
            h = new ViewHolder();
            h.likeCount = (TextView)convertView.findViewById(R.id.adapter_feed_like_count);
            h.commentCount = (TextView)convertView.findViewById(R.id.adapter_feed_comment_count);
            h.follow = (TextView)convertView.findViewById(R.id.adapter_feed_follow);
            h.title = (TextView)convertView.findViewById(R.id.adapter_feed_title);
            h.info = (TextView)convertView.findViewById(R.id.adapter_feed_info);
            h.subInfo = (TextView)convertView.findViewById(R.id.adapter_feed_sub_info);
            h.likeLayout = (LinearLayout) convertView.findViewById(R.id.adapter_feed_like_layout);
            h.head = (ImageView) convertView.findViewById(R.id.adapter_feed_head_img);
            h.commentLayout = (LinearLayout)convertView.findViewById(R.id.adapter_feed_comment_layout);
            h.content = (TextView) convertView.findViewById(R.id.adapter_feed_content);
            h.txt1 = (TextView)convertView.findViewById(R.id.adapter_feed_txt1);
            h.txt2 = (TextView)convertView.findViewById(R.id.adapter_feed_txt2);
            convertView.setTag(h);
        }else{
            h = (ViewHolder) convertView.getTag();
        }
        Feed feed = getItem(position);
        String url = UrlContract.USER_HEAD_URL;
        String info = "", subInfo = "", title = "", content = "", count1 = "", count2 = "", txt1 = "", txt2 = "", txt3 = "";
        JSONObject object = JSON.parseObject(feed.getData());
        if(object == null)
            return convertView;
        Answer answer = null;
        Question question = null;
        switch (feed.getType()){
            case 0:
                answer = JSONObject.toJavaObject(object,Answer.class);
                url += answer.getHeadUrl();
                info += feed.getName();
                subInfo += "赞同了回答";
                title += answer.getQuestionTitle();
                content += answer.getContent();
                count1 += answer.getLikeCount();
                count2 += answer.getCommentCount();
                txt1 += "赞同-";
                txt2 += "评论-";
                txt3 += "收藏回答";
                break;
            case 1:
                break;
            case 2:
                if(object.getInteger("entityType") == null){
                    question = JSONObject.toJavaObject(object,Question.class);
                    url += question.getHeadUrl();
                    info += feed.getName();
                    subInfo += "关注了问题";
                    title += question.getTitle();
                    content += question.getContent();
                    count1 += question.getLikeCount();
                    count2 += question.getCommentCount();
                    txt1 += "赞同-";
                    txt2 += "回答-";
                    txt3 += "关注问题";
                }else{
                    answer = JSONObject.toJavaObject(object,Answer.class);
                    url += answer.getHeadUrl();
                    info += feed.getName();
                    subInfo += "收藏了回答";
                    title += answer.getQuestionTitle();
                    content += answer.getContent();
                    count1 += answer.getLikeCount();
                    count2 += answer.getCommentCount();
                    txt1 += "赞同-";
                    txt2 += "评论-";
                    txt3 += "收藏回答";
                }
                break;
            case 3:
                question = JSONObject.toJavaObject(object,Question.class);
                url += question.getHeadUrl();
                info += feed.getName();
                subInfo += "关注了问题";
                title += question.getTitle();
                content += question.getContent();
                count1 += question.getLikeCount();
                count2 += question.getCommentCount();
                txt1 += "赞同-";
                txt2 += "回答-";
                txt3 += "关注问题";
                break;
            case 4:
                answer = JSONObject.toJavaObject(object,Answer.class);
                url += answer.getHeadUrl();
                info += feed.getName();
                subInfo += "回答了问题";
                title += answer.getQuestionTitle();
                content += answer.getContent();
                count1 += answer.getLikeCount();
                count2 += answer.getCommentCount();
                txt1 += "赞同-";
                txt2 += "评论-";
                txt3 += "收藏回答";
                break;
        }
        Glide.with(fragment.getActivity()).load(UrlContract.USER_HEAD_URL + feed.getHeadUrl()).into(h.head);
        h.info.setText(feed.getName());
        h.subInfo.setText(subInfo);
        h.title.setText(title);
        h.content.setText(content);
        h.commentCount.setText(count2);
        h.likeCount.setText(count1);
        h.txt1.setText(txt1);
        h.txt2.setText(txt2);
        return convertView;
    }
    class ViewHolder{
        TextView likeCount, commentCount, follow, title, content, info, subInfo, txt1, txt2;
        ImageView head;
        LinearLayout likeLayout, commentLayout;
    }

}
