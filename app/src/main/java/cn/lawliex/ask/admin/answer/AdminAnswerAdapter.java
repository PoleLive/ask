package cn.lawliex.ask.admin.answer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.Answer;
import cn.lawliex.ask.data.Question;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

/**
 * Created by Terence on 2017/4/9.
 */

public class AdminAnswerAdapter extends BaseAdapter {
    List<Answer> datas;
    Context context;
    String path = "";
    public List<Answer> getDatas(){
        return datas;
    }
    public AdminAnswerAdapter(List<Answer> datas, Context context) {
        this.datas = datas;
        this.context = context;
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
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_admin_list_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.question_list_title);
            holder.name = (TextView) convertView.findViewById(R.id.question_list_user_name);
            holder.head = (ImageView) convertView.findViewById(R.id.question_list_user_head);
            holder.answerCount = (TextView)convertView.findViewById(R.id.question_list_comment_count);
            holder.likeCount = (TextView)convertView.findViewById(R.id.question_list_like);
            holder.content = (TextView)convertView.findViewById(R.id.question_list_answer);
            holder.deleteTxt = (TextView)convertView.findViewById(R.id.question_list_delete_txt);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(datas.get(position).getQuestionTitle());
        holder.content.setText(datas.get(position).getContent());
        holder.answerCount.setText(""+getItem(position).getCommentCount());
        holder.likeCount.setText(""+getItem(position).getLikeCount());
        if(getItem(position).getStatus() == 0)
            holder.deleteTxt.setText("删除");
        else
            holder.deleteTxt.setText("撤销删除");

        holder.name.setText(getItem(position).getAuthor());
        Glide.with(context).load(UrlContract.SERVER_ADDRESS + "/" + getItem(position).getHeadUrl()).into(holder.head);
        holder.deleteTxt.setOnClickListener(getOnClickListener(holder.deleteTxt,position));
        return convertView;
    }
    View.OnClickListener getOnClickListener(final View view, final int i){
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Answer q = datas.get(i);
                Map<String, String> map = AskHelper.getRequestMap(context);
                map.put("entityType","1");
                map.put("entityId",q.getId() + "");
                map.put("id",q.getId() + "");
                switch (v.getId()){
                    case R.id.question_list_user_head:
                    case R.id.question_list_user_name:
                        Intent intent = new Intent(context, UserInfoDetailActivity.class);
                        intent.putExtra("userId",datas.get(i).getUserId());
                        context.startActivity(intent);
                        return;
                    case R.id.question_list_like:case R.id.question_list_like_str:
                        path = UrlContract.LIKE_LIKE;
                        break;
                    case R.id.question_list_delete_txt:
                        if(datas.get(i).getStatus() == 0)
                            path = UrlContract.QUSTIION_DELETE;
                        else
                            path = UrlContract.QUSTION_CANCEL_DELETE;
                        break;
                }
                HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(JSONObject jsonObject) {

                        if(jsonObject.getInteger("code")== 1){

                            ((TextView)view).setText("删除");
                        }else if(jsonObject.getInteger("code") == 0){

                            switch (v.getId()){
                                case R.id.question_list_like:case R.id.question_list_like_str:
                                    TextView txt = (TextView)view;
                                    if(jsonObject.getInteger("code")==0){
                                        txt.setText(""+jsonObject.getInteger("count"));
                                    }
                                    break;

                                case R.id.question_list_delete_txt:
                                    if(path.equals(UrlContract.QUSTIION_DELETE)) {
                                        ((TextView) view).setText("撤销删除");
                                        datas.get(i).setStatus(1);
                                    }
                                    else {
                                        ((TextView) view).setText("删除");
                                        datas.get(i).setStatus(0);
                                    }
                                    break;
                            }
                        }

                    }
                }).post(path,map);
            }
        };
        return listener;
    }
    static class ViewHolder{
        ImageView head;
        TextView title, content, name, likeCount, answerCount, deleteTxt;
    }
}
