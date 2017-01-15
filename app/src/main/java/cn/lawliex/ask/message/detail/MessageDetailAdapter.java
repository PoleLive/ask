package cn.lawliex.ask.message.detail;

import android.content.Context;
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
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;

/**
 * Created by Terence on 2017/1/4.
 */

public class MessageDetailAdapter extends BaseAdapter {
    List<Message> datas;
    Context context;
    int ownId;
    public MessageDetailAdapter(List<Message> datas, Context context) {
        this.datas = datas;
        this.context = context;
        ownId = UserLocalDataSource.getInstance(context).getInt("id",0);
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
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.msg_detail_item, parent, false);

            holder.leftLayout = (LinearLayout)convertView.findViewById(R.id.msg_detail_left_layout);
            holder.rightLayout = (LinearLayout)convertView.findViewById(R.id.msg_detail_right_layout);
            holder.rightTxt = (TextView)convertView.findViewById(R.id.msg_detail_right_txt);
            holder.leftTxt = (TextView)convertView.findViewById(R.id.msg_detail_left_txt);
            holder.leftImg = (ImageView)convertView.findViewById(R.id.msg_detail_left_img);
            holder.rightImg = (ImageView) convertView.findViewById(R.id.msg_detail_right_img);
            holder.leftMsgImg = (ImageView)convertView.findViewById(R.id.msg_detail_left_imgmsg);
            holder.rightMsgImg = (ImageView) convertView.findViewById(R.id.msg_detail_right_imgmsg);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        if(getItem(position).getFromId() == ownId ){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftTxt.setText("");
            if(getItem(position).getType() == 0) {
                holder.rightTxt.setVisibility(View.VISIBLE);
                holder.rightMsgImg.setVisibility(View.GONE);
                holder.rightMsgImg.setMaxHeight(0);
                holder.rightTxt.setText(getItem(position).getContent());

            }else{
                holder.rightTxt.setVisibility(View.GONE);
                holder.rightMsgImg.setVisibility(View.VISIBLE);
                holder.rightMsgImg.setMaxHeight(holder.rightMsgImg.getWidth());
                Glide.with(context).load(UrlContract.MSG_IMG + getItem(position).getContent()).into(holder.rightMsgImg);
            }
            Glide.with(context).load(UrlContract.USER_HEAD_URL + getItem(position).getFromUrl()).into(holder.rightImg);

        }else{
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.rightTxt.setText("");


            if(getItem(position).getType() == 0) {
                holder.leftTxt.setVisibility(View.VISIBLE);
                holder.leftMsgImg.setVisibility(View.GONE);
                holder.leftTxt.setText(getItem(position).getContent());

            }else{
                holder.leftTxt.setVisibility(View.GONE);
                holder.leftMsgImg.setVisibility(View.VISIBLE);
                Glide.with(context).load(UrlContract.MSG_IMG + getItem(position).getContent()).into(holder.leftMsgImg);
            }
            Glide.with(context).load(UrlContract.USER_HEAD_URL + getItem(position).getFromUrl()).into(holder.leftImg);


        }
        return convertView;
    }
    class ViewHolder{
        LinearLayout leftLayout, rightLayout;
        TextView rightTxt, leftTxt;
        ImageView leftImg, rightImg, leftMsgImg, rightMsgImg;

    }
}
