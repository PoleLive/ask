package cn.lawliex.ask.question.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Question;

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
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
        Question q = datas.get(position);
        holder.titleTxt.setText(q.getTitle());
        holder.answerTxt.setText(q.getContent());
        holder.commentCountTxt.setText(q.getCommentCount()+"");
        holder.usernameTxt.setText(q.getName());
        return convertView;
    }
    class ViewHolder{
        ImageView photo;
        TextView usernameTxt;
        TextView titleTxt;
        TextView answerTxt;
        TextView likeTxt;
        TextView commentCountTxt;
    }
}
