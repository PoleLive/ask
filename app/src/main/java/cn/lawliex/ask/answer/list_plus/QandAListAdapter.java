package cn.lawliex.ask.answer.list_plus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/31.
 */

public class QandAListAdapter extends BaseAdapter {
    QandAListFragment fragment;
    List<Answer> datas;

    public QandAListAdapter(QandAListFragment fragment, List<Answer> answerList) {
        this.fragment = fragment;
        this.datas = answerList;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Answer getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.q_and_a_list_item,parent,false);
            holder = new ViewHolder();
            holder.photo = (ImageView)convertView.findViewById(R.id.q_and_a_user_head);
            holder.nameTxt = (TextView)convertView.findViewById(R.id.q_and_a_user_name);
            holder.questionTxt = (TextView)convertView.findViewById(R.id.q_and_a_title);
            holder.answerTxt = (TextView)convertView.findViewById(R.id.q_and_a_answer);
            holder.commentTxt = (TextView)convertView.findViewById(R.id.q_and_a_comment_count);
            holder.followTxt = (TextView)convertView.findViewById(R.id.q_and_a_follow_question);
            holder.likeTxt = (TextView)convertView.findViewById(R.id.q_and_a_like);
            holder.likeLayout = (LinearLayout) convertView.findViewById(R.id.q_and_a_like_layout);
            holder.commentLayout = (LinearLayout) convertView.findViewById(R.id.q_and_a_comment_layout);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Answer a = datas.get(position);
        holder.nameTxt.setText(a.getAuthor());
        holder.questionTxt.setText(a.getQuestionTitle());
        holder.answerTxt.setText(a.getContent());
        holder.likeTxt.setText(a.getLikeCount() + "");
        holder.commentTxt.setText(a.getCommentCount() + "");
        Glide.with(fragment.getActivity()).load(UrlContract.USER_HEAD_URL + datas.get(position).getHeadUrl()).into(holder.photo );
        return convertView;
    }
    class ViewHolder{
        ImageView photo;
        TextView nameTxt, questionTxt, answerTxt, likeTxt, commentTxt, followTxt;
        LinearLayout likeLayout, commentLayout;

    }
}
