package cn.lawliex.ask.answer.list;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Answer;

/**
 * Created by Terence on 2016/12/29.
 */

public class AnswerListAdapter extends BaseAdapter {
    List<Answer> answers;
    Context context;

    public AnswerListAdapter(List<Answer> answers, Context context) {
        this.answers = answers;
        this.context = context;
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
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Answer answer = answers.get(position);
        //viewHolder.usernameTxt.setText(answer.getUserId());
        viewHolder.contentTxt.setText(answer.getContent());
        viewHolder.commentCountTxt.setText(""+ 0);
        viewHolder.likeCountTxt.setText(""+0);

        return convertView;
    }
    class ViewHolder{
        ImageView headImg;
        TextView contentTxt;
        TextView likeCountTxt;
        TextView commentCountTxt;
        TextView usernameTxt;
    }
}
