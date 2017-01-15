package cn.lawliex.ask.message.detail;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.message.list.MessageListAdapter;
import cn.lawliex.ask.util.AskHelper;
import cn.lawliex.ask.util.GlideImageLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

public class MessageDetailActivity extends AppCompatActivity implements MessageDetailContract.View {
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final String TAG = "MessageDetailActivity";
    GalleryConfig galleryConfig;
    EditText inputEdit;
    MessageDetailContract.Presenter presenter;
    ListView listView;
    List<Message> messages;
    TextView sendBn;
    ImageView sendImg;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("消息");
        List<String> path = new ArrayList<>();
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .pathList(path)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
//                .crop(true)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
//                .crop(true, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
        MessageDetailContract.Presenter presenterx = new MessageDetailPresenter(this);
        listView = (ListView)findViewById(R.id.msg_detail_listview);
        sendBn = (TextView)findViewById(R.id.msg_detail_bn_send);
        inputEdit = (EditText)findViewById(R.id.msg_detail_edit_input);
        sendImg = (ImageView)findViewById(R.id.msg_detail_send_img);
        sendImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(getActivity());
            }
        });
        sendBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEdit.getText().toString().length() == 0)
                    return;
                presenter.sendMessage();
                inputEdit.setText("");
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "同意授权");
                GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
            } else {
                Log.i(TAG, "拒绝授权");
            }
        }
    }
    @Override
    public void showMessageDetail(List<Message> messageList) {
        messages = messageList;
        MessageDetailAdapter adapter = new MessageDetailAdapter(messageList,this);
        listView.setAdapter(adapter);
        listView.setSelection(messageList.size() - 1);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Message getMessageToSend() {
        Message message = new Message();
        message.setToId(getIntent().getIntExtra("toId",0));
        message.setContent(inputEdit.getText().toString());
        return message;
    }

    @Override
    public void setPresenter(MessageDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void onPause(){
        super.onPause();

        presenter.onDestroy();

    }
    @Override
    public Activity getActivity() {
        return this;
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
                HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
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
                            Toast.makeText(getActivity(), jsonObject.getString("img"), Toast.LENGTH_LONG).show();
                            //showHeadImage(headImg);
                            presenter.sendImg(jsonObject.getString("img"));
                        }
                    }
                }).sendImg(description, body, map);

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
