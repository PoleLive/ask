package cn.lawliex.ask.answer.list;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.comment.CommentActivity;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerListAdapter extends BaseAdapter {
    List<Answer> answers;
    Context context;
    AnswerListFragment fragment;
    public AnswerListAdapter(List<Answer> answers, AnswerListFragment fragment) {
        this.answers = answers;
        this.fragment = fragment;
        this.context = fragment.getActivity();
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Answer getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.answer_list_item, parent, false);
            viewHolder.headImg = (ImageView)convertView.findViewById(R.id.answer_list_img_head);
            viewHolder.usernameTxt = (TextView)convertView.findViewById(R.id.answer_list_txt_name);
            viewHolder.contentTxt = (TextView)convertView.findViewById(R.id.answer_list_txt_content);
            viewHolder.likeCountTxt = (TextView)convertView.findViewById(R.id.answer_list_like_count);
            viewHolder.commentCountTxt = (TextView)convertView.findViewById(R.id.answer_list_comment_count);
            viewHolder.commentLayout = (LinearLayout)convertView.findViewById(R.id.answer_list_commetn_layout);
            viewHolder.likeLayout=(LinearLayout)convertView.findViewById(R.id.answer_list_like_layout);
            viewHolder.dateTxt = (TextView)convertView.findViewById(R.id.answer_list_date);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Answer answer = answers.get(position);
        viewHolder.usernameTxt.setText(answer.getAuthor());
        viewHolder.contentTxt.setText(answer.getContent());
        viewHolder.commentCountTxt.setText(""+ answer.getCommentCount());
        viewHolder.likeCountTxt.setText(""+answer.getLikeCount());
        viewHolder.likeLayout.setOnClickListener(getOnClickListener(position));
        viewHolder.commentLayout.setOnClickListener(getOnClickListener(position));
        viewHolder.commentCountTxt.setOnClickListener(getOnClickListener(position));
        viewHolder.likeCountTxt.setOnClickListener(getOnClickListener(position));
        viewHolder.dateTxt.setText(AskHelper.format(answer.getCreatedDate()));
        return convertView;
    }
    public View.OnClickListener getOnClickListener(final int i){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer answer;
                switch (v.getId()){
                    case R.id.answer_list_like_count:
                    case R.id.answer_list_like_layout:
                        Map<String,String> map = AskHelper.getRequestMap(context);
                        answer = answers.get(i);
                        map.put("entityType","2");
                        map.put("entityId",answer.getId() + "");
                        HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(JSONObject jsonObject) {
                                if(jsonObject.getInteger("code") == 0){
                                    fragment.update();
                                }
                            }
                        }).post(UrlContract.LIKE_LIKE,map);
                        break;
                    case R.id.answer_list_commetn_layout:
                    case R.id.answer_list_comment_count:
                        Intent intent = new Intent(context, CommentActivity.class);
                        answer = answers.get(i);
                        intent.putExtra("answerId",answer.getId());
                        context.startActivity(intent);
                        break;
                }
            }
        };
        return listener;
    }
    class ViewHolder{
        ImageView headImg;
        TextView contentTxt;
        TextView likeCountTxt;
        TextView commentCountTxt;
        TextView usernameTxt;
        LinearLayout likeLayout;
        LinearLayout commentLayout;
        TextView dateTxt;
    }
}
