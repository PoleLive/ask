package cn.lawliex.ask.message.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.local.MessageDbHelper;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.util.AskHelper;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageListAdapter extends BaseAdapter {
    MessageListFragment fragment;
    List<Message> datas;
    MessageDbHelper dbHelper;
    public MessageListAdapter(MessageListFragment fragment, List<Message> datas) {
        this.fragment = fragment;
        this.datas = datas;
        dbHelper = new MessageDbHelper(fragment.getActivity());

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Message getItem(int position) {
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
            h = new ViewHolder();
            convertView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.msg_list_item,parent, false);
            h.contentTxt = (TextView)convertView.findViewById(R.id.msg_list_content);
            h.nameTxt = (TextView)convertView.findViewById(R.id.msg_list_name_txt);
            h.timeTxt = (TextView)convertView.findViewById(R.id.msg_list_time_txt);
            h.headImg = (ImageView)convertView.findViewById(R.id.msg_list_head_img);
            h.msgHintTxt = (TextView)convertView.findViewById(R.id.msg_list_new_msg_count);

            convertView.setTag(h);
        }else{
            h = (ViewHolder)convertView.getTag();
        }
        Message m = getItem(position);
        if(UserLocalDataSource.getInstance(fragment.getActivity()).getInt("id",0) == m.getFromId()){
            h.nameTxt.setText(m.getToName());
            Glide.with(fragment.getActivity()).load(UrlContract.USER_HEAD_URL + m.getToUrl()).into(h.headImg);
        }else{
            h.nameTxt.setText(m.getFromName());
            Glide.with(fragment.getActivity()).load(UrlContract.USER_HEAD_URL + m.getFromUrl()).into(h.headImg);
        }
        int maxId = dbHelper.getLastestMessageId(m.getConversationId());
        if(m.getUnReadCount() > 0) {
            h.msgHintTxt.setVisibility(View.VISIBLE);
            h.msgHintTxt.setText(m.getUnReadCount() + "");
        }else{
            h.msgHintTxt.setVisibility(View.INVISIBLE);
        }
        h.contentTxt.setText(m.getContent());
        h.timeTxt.setText(AskHelper.format(m.getCreatedDate()));
        return convertView;
    }
    class ViewHolder{
        ImageView headImg;
        TextView nameTxt;
        TextView contentTxt;
        TextView timeTxt;
        TextView msgHintTxt;
    }
}
