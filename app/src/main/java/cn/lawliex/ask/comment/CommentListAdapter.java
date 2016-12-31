package cn.lawliex.ask.comment;

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
import cn.lawliex.ask.data.Comment;

/**
 * Created by Terence on 2016/12/31.
 */

public class CommentListAdapter extends BaseAdapter {
    List<Comment> datas;
    Context context;

    public CommentListAdapter(List<Comment> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Comment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.photo = (ImageView)convertView.findViewById(R.id.comment_list_item_photo);
            viewHolder.nameTxt = (TextView)convertView.findViewById(R.id.comment_list_item_name);
            viewHolder.contentTxt = (TextView)convertView.findViewById(R.id.comment_list_item_content);
            viewHolder.timeTxt = (TextView)convertView.findViewById(R.id.comment_list_item_time);
            viewHolder.likeTxt = (TextView)convertView.findViewById(R.id.comment_list_item_like_count);
        }
        Comment c = datas.get(position);
        viewHolder.nameTxt.setText(c.getAuthor());
        viewHolder.contentTxt.setText(c.getContent());



        return convertView;
    }
    class ViewHolder{
        ImageView photo;
        TextView nameTxt;
        TextView contentTxt;
        TextView timeTxt;
        TextView likeTxt;
    }
}
