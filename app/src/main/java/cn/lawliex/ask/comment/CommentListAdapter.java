package cn.lawliex.ask.comment;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Comment;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/31.
 */

public class CommentListAdapter extends BaseAdapter {
    List<Comment> datas;
    Context context;
    CommentFragment fragment;

    public CommentListAdapter(List<Comment> datas,CommentFragment fragment) {
        this.datas = datas;
        this.fragment = fragment;
        context = fragment.getActivity();
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
            viewHolder.likeImg =(ImageView)convertView.findViewById(R.id.comment_list_item_like_img) ;
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();
        Comment c = datas.get(position);
        viewHolder.nameTxt.setText(c.getAuthor());
        viewHolder.contentTxt.setText(c.getContent());
        viewHolder.likeTxt.setText(c.getLikeCount() + "");
        viewHolder.timeTxt.setText(AskHelper.format(c.getCreatedDate()));
        viewHolder.likeTxt.setOnClickListener(getOnClickListener(viewHolder.likeTxt,position));
        viewHolder.likeImg.setOnClickListener(getOnClickListener(viewHolder.likeImg,position));
        viewHolder.photo.setOnClickListener(getOnClickListener(viewHolder.photo,position));
        viewHolder.nameTxt.setOnClickListener(getOnClickListener(viewHolder.nameTxt,position));
        return convertView;
    }

    public View.OnClickListener getOnClickListener( View view,final int i){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.comment_list_item_photo:
                    case R.id.comment_list_item_name:
                        Comment c = datas.get(i);
                        Intent intent = new Intent(fragment.getActivity(), UserInfoDetailActivity.class);
                        intent.putExtra("userId",c.getUserId());
                        context.startActivity(intent);
                        break;
                    case R.id.comment_list_item_like_count:
                    case R.id.comment_list_item_like_img:
                        Map<String,String> map = AskHelper.getRequestMap(context);
                        map.put("entityType","3");
                        map.put("entityId",datas.get(i).getId() + "");
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
                }
            }
        };
        return listener;
    }

    class ViewHolder{
        ImageView photo;
        TextView nameTxt;
        TextView contentTxt;
        TextView timeTxt;
        TextView likeTxt;
        ImageView likeImg;
    }
}
