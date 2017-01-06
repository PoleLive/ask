package cn.lawliex.ask.profile.other.detail;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;

import cn.lawliex.ask.BaseActivity;
import cn.lawliex.ask.R;
import cn.lawliex.ask.profile.other.list.UserInfoListContract;

public class UserInfoDetailActivity extends AppCompatActivity {
    GalleryConfig galleryConfig;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final String TAG = "UserInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("个人信息");
        //init("个人信息",this);
        UserInfoDetailFragment fragment = (UserInfoDetailFragment) getFragmentManager().findFragmentById(R.id.user_info_detail_fragment);
        UserInfoDetailContract.Presenter presenter = new UserInfoDetailPresenter(fragment);
    }
    @Override
    public void onStart(){
        super.onStart();
        Glide.get(this).clearMemory();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(UserInfoDetailActivity.this).clearDiskCache();;
            }
        }).start();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
