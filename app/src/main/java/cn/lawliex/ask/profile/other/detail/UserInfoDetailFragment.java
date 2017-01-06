package cn.lawliex.ask.profile.other.detail;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.joooonho.SelectableRoundedImageView;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.answer.list_plus.QandAListActivity;
import cn.lawliex.ask.data.UserInfo;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.followers.FollowersActivity;
import cn.lawliex.ask.following.FollowingActivity;
import cn.lawliex.ask.message.add.MessageAddActivity;
import cn.lawliex.ask.message.detail.MessageDetailActivity;
import cn.lawliex.ask.question.list.QuestionListActivity;
import cn.lawliex.ask.util.AskHelper;
import cn.lawliex.ask.util.GlideImageLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfoDetailFragment extends Fragment implements UserInfoDetailContract.View, View.OnClickListener {
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final String TAG = "UserInfo";

    UserInfoDetailContract.Presenter presenter;
    SelectableRoundedImageView headImg;
    TextView nameTxt, mottoTxt, questionCountTxt, answerCountTxt, followerCountTxt, followeeCountTxt, likeCount;
    Button  followBn, sendMsgBn;
    GalleryConfig galleryConfig;
    LinearLayout questionLayout,answerLayout, followingLayout, followersLayout;
    UserInfo userInfo;
    @Override
    public void showUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        nameTxt.setText(userInfo.getName());
        mottoTxt.setText(userInfo.getMotto());
        questionCountTxt.setText(userInfo.getQuestionCount() + "");
        answerCountTxt.setText(userInfo.getAnswerCount() + "");
        followeeCountTxt.setText(userInfo.getFolloweeCount() + "");
        followerCountTxt.setText(userInfo.getFollowingCount() + "");
        likeCount.setText(userInfo.getLikeCount() + "");
        showHeadImage(headImg);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeFollowBnState(boolean follow) {
        if(follow){
            followBn.setText("已关注");
        }else{
            followBn.setText("关注");
        }
    }

    @Override
    public void setPresenter(UserInfoDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> path = new ArrayList<>();
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(true, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();

    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_detail_fragment,container, false);
        sendMsgBn = (Button)view.findViewById(R.id.user_info_detail_bn_send_message) ;
        nameTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_name);
        questionCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_question_count);
        answerCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_answer_count);
        followerCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_follower_count);
        followeeCountTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_followee_count);
        likeCount = (TextView)view.findViewById(R.id.user_info_detail_txt_like_count);
        mottoTxt = (TextView)view.findViewById(R.id.user_info_detail_txt_motto);
        followBn = (Button) view.findViewById(R.id.user_info_detail_txt_bn_follow_user);
        answerLayout = (LinearLayout)view.findViewById(R.id.user_info_detail_answer_layout);
        questionLayout = (LinearLayout)view.findViewById(R.id.user_info_detail_question_layout);
        headImg = (SelectableRoundedImageView)view.findViewById(R.id.image);
        followersLayout = (LinearLayout)view.findViewById(R.id.user_info_detail_followers_layout);
        followingLayout = (LinearLayout)view.findViewById(R.id.user_info_detail_following_layout);
        followersLayout.setOnClickListener(this);

        sendMsgBn.setOnClickListener(this);
        headImg.setOnClickListener(this);
        answerLayout.setOnClickListener(this);
        questionLayout.setOnClickListener(this);
        followBn.setOnClickListener(this);
        sendMsgBn.setOnClickListener(this);
        followingLayout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_info_detail_following_layout:
                Intent followingIntent = new Intent(getActivity(), FollowingActivity.class);
                followingIntent.putExtra("userId",userInfo.getId());
                startActivity(followingIntent);
                break;
            case R.id.user_info_detail_answer_layout:
                Intent intenta = new Intent(getActivity(), QandAListActivity.class);
                intenta.putExtra("userId",userInfo.getId());
                startActivity(intenta);
                break;
            case R.id.user_info_detail_question_layout:
                Intent intent = new Intent(getActivity(), QuestionListActivity.class);
                intent.putExtra("userId",userInfo.getId());
                startActivity(intent);
                break;
            case R.id.user_info_detail_bn_send_message:
                Intent intent1 = new Intent(getActivity(), MessageDetailActivity.class);
                intent1.putExtra("toId",userInfo.getId());
                startActivity(intent1);
                break;
            case R.id.user_info_detail_txt_bn_follow_user:
                presenter.follow(userInfo.getId());
                break;
            case R.id.user_info_detail_followers_layout:
                Intent intentb = new Intent(getActivity(), FollowersActivity.class);
                intentb.putExtra("userId",userInfo.getId());
                startActivity(intentb);
                break;
            case R.id.image:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "需授权");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Log.i(TAG, "拒绝过了");
                        Toast.makeText(getActivity(), "请在设置-应用管理中开启应用的存储授权", Toast.LENGTH_LONG).show();
                    } else {
                        Log.i(TAG, "进行授权");
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                } else {
                    Log.i(TAG, "不需要授权");
                }
                selectPhoto(galleryConfig);

                break;
        }
    }
    @Override
    public void showHeadImage(ImageView imageView){
        Glide.with(getActivity()).load(UrlContract.SERVER_ADDRESS+ "/" + userInfo.getHeadUrl()).into(imageView);
    }
    public void selectPhoto(GalleryConfig galleryConfig){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getActivity()).clearDiskCache();
            }
        }).start();
        Glide.get(getActivity()).clearMemory();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
    }
    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
            Log.i(TAG, "onStart: 开启");
        }
        @Override
        public void onSuccess(List<String> photoList) {
            Log.i(TAG, "onSuccess: 返回数据");
            for (String s : photoList) {
                Log.i(TAG, s);
                File file = new File(s);
                //String ticket = "724c8b4ebbd4476ea0fede3f132e36a2";
                Map<String, String> map = AskHelper.getRequestMap(getActivity());
                //map.put("ticket", ticket);
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();

                // 创建 RequestBody，用于封装构建RequestBody
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

// MultipartBody.Part  和后端约定好Key，这里的partName是用image
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);


// 添加描述

                RequestBody description =
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"), "nothing");


// 执行请求
                HttpRequests.getInstance().baseUrl("http://10.21.27.19:8080").subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        if (jsonObject.getInteger("code") == 0) {
                            Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();
                            showHeadImage(headImg);

                        }
                    }
                }).post(description, body, map);

            }

        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onError() {

        }
    };
}
