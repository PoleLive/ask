package cn.lawliex.ask.timeline;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;

import cn.lawliex.ask.R;
import cn.lawliex.ask.UrlContract;
import cn.lawliex.ask.data.source.local.SharedPreferencesHelper;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.discover.DiscoverActivity;
import cn.lawliex.ask.follow.FollowActivity;
import cn.lawliex.ask.following.FollowingActivity;
import cn.lawliex.ask.login.LoginActivity;
import cn.lawliex.ask.message.list.MessageListActivity;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.question.list.QuestionListActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

public class TimeLineActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("首页");
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //加载抽屉头像
        View headLayout = navigationView.inflateHeaderView(R.layout.nav_header_time_line);
        ImageView photoView = (ImageView)headLayout.findViewById(R.id.nav_head_img);
        SharedPreferencesHelper sphelper = new SharedPreferencesHelper(this);
        String headUrl = sphelper.getString("headUrl");
        Glide.with(this).load(UrlContract.SERVER_ADDRESS + "/" + headUrl).into(photoView);

        navigationView.setNavigationItemSelectedListener(this);

        TimeLineFragment fragment = (TimeLineFragment) getFragmentManager().findFragmentById(R.id.activity_timeline_fragment);
        TimeLineContract.Presenter presenter = new TimeLinePresenter(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.time_line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            HttpRequests.getInstance().subscribe(new Subscriber<JSONObject>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(JSONObject jsonObject) {
                    if (jsonObject.getInteger("code") == 0){
                        UserLocalDataSource.getInstance(TimeLineActivity.this).logout();
                        Intent intent = new Intent(TimeLineActivity.this, LoginActivity.class);
                        startActivity(intent);
                        TimeLineActivity.this.finish();
                    }
                }
            }).post("logout", AskHelper.getRequestMap(this));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if(this instanceof  TimeLineActivity)
                return true;
            Intent intent = new Intent(this, TimeLineActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_follow) {
//            Intent intent = new Intent(this, FollowingActivity.class);
//            intent.putExtra("userId",UserLocalDataSource.getInstance(this).getInt("id",0));
//            startActivity(intent);
            Intent intent = new Intent(this, FollowingActivity.class);
            intent.putExtra("userId",UserLocalDataSource.getInstance(this).getInt("id",0));
            startActivity(intent);
        } else if (id == R.id.nav_discovery) {
            Intent intent = new Intent(this, DiscoverActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_collection) {
            Intent intent = new Intent(this, FollowingActivity.class);
            intent.putExtra("selection",3);
            intent.putExtra("userId", UserLocalDataSource.getInstance(this).getInt("id",0));
            startActivity(intent);
        } else if (id == R.id.nav_questions) {
            Intent intent = new Intent(this, QuestionListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(this, UserInfoDetailActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_msg){
            Intent intent = new Intent(this, MessageListActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
