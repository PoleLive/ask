package cn.lawliex.ask.following.user;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.followers.FollowersContract;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowUserAdapter extends BaseAdapter {
    FollowUserContract.View fragment;
    List<User> datas;

    public FollowUserAdapter(FollowUserContract.View fragment, List<User> datas) {
        this.fragment = fragment;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public User getItem(int position) {
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
            convertView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.followers_list_item, parent, false);

            holder.headImg = (ImageView)convertView.findViewById(R.id.followers_head_img);
            holder.followBn = (Button)convertView.findViewById(R.id.followers_follow);
            holder.mottoTxt = (TextView)convertView.findViewById(R.id.followers_motto);
            holder.nameTxt  = (TextView)convertView.findViewById(R.id.followers_name);
            holder.outLayout = (LinearLayout)convertView.findViewById(R.id.followers_item_out_layout);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        checkFollow(holder.followBn,position);
        User user = datas.get(position);
        Glide.with(fragment.getActivity()).load(UrlContract.USER_HEAD_URL + user.getHeadUrl()).into(holder.headImg);
        holder.nameTxt.setText(user.getName());
        holder.mottoTxt.setText("不知所云");
        holder.followBn.setOnClickListener(getOnClickListener(position));
        holder.outLayout.setOnClickListener(getOnClickListener(position));
        return convertView;
    }
    void checkFollow(final Button bn, int i){
        Map<String,String> map = AskHelper.getRequestMap(fragment.getActivity());
        map.put("entityType","0");
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
                    bn.setText("已关注");
                }else{
                    bn.setText("关注");
                }
            }
        }).post(UrlContract.ISFOLLOWER,map);
    }
    void follow(int userId){
        Map<String,String> map = AskHelper.getRequestMap(fragment.getActivity());
        map.put("entityType","0");
        map.put("entityId",userId + "");
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
        }).post(UrlContract.FOLLOW, map);
    }
    View.OnClickListener getOnClickListener(final int i){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.followers_follow:
                        int userId = datas.get(i).getId();
                        follow(userId);
                        break;
                    case R.id.followers_item_out_layout:
                        int id = datas.get(i).getId();
                        Intent intent = new Intent(fragment.getActivity(), UserInfoDetailActivity.class);
                        intent.putExtra("userId",id );
                        fragment.getActivity().startActivity(intent);
                        break;
                }
            }
        };
        return  listener;
    }

    class ViewHolder{
        TextView nameTxt;
        TextView mottoTxt;
        Button followBn;
        ImageView headImg;
        LinearLayout outLayout;
    }
}
