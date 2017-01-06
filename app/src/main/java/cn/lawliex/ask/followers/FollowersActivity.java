package cn.lawliex.ask.followers;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.lawliex.ask.R;

public class FollowersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("粉丝列表");
        FollowersFragment fragment = (FollowersFragment) getFragmentManager().findFragmentById(R.id.followers_fragment);
        FollowersContract.Presenter presenter = new FollowersPresenter(fragment);
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
