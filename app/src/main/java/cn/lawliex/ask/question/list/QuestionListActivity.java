package cn.lawliex.ask.question.list;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
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
import cn.lawliex.ask.data.UserInfo;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.data.source.remote.http.HttpRequests;
import cn.lawliex.ask.login.LoginActivity;
import cn.lawliex.ask.message.list.MessageListActivity;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;
import cn.lawliex.ask.question.add.QuestionAddActivity;
import cn.lawliex.ask.util.AskHelper;
import rx.Subscriber;

public class QuestionListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(QuestionListActivity.this, QuestionAddActivity.class);
                startActivity(intent);
            }
        });
        fab.setRippleColor(Color.RED);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        QuestionListFragment fragment = (QuestionListFragment) getFragmentManager().findFragmentById(R.id.question_list_fragment);
        QuestionListPresenter presenter = new QuestionListPresenter(fragment);
        //fragment.setPresenter(presenter);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView imageView = (ImageView) LayoutInflater.from(this).inflate(R.layout.nav_header_question_list,navigationView,false).findViewById(R.id.nav_head_img);
        String headUrl = UserLocalDataSource.getInstance(this).getString("headUrl","");
        Glide.with(this).load(UrlContract.SERVER_ADDRESS + "/" + headUrl).into(imageView);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.question_list, menu);
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
                        UserLocalDataSource.getInstance(QuestionListActivity.this).logout();
                        Intent intent = new Intent(QuestionListActivity.this, LoginActivity.class);
                        startActivity(intent);
                        QuestionListActivity.this.finish();
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

        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(this, UserInfoDetailActivity.class);
            UserLocalDataSource userLocalDataSource = UserLocalDataSource.getInstance(QuestionListActivity.this);

            intent.putExtra("userId",userLocalDataSource.getInt("id",0));
            startActivity(intent);
        } else if (id == R.id.collection) {

        } else if (id == R.id.message) {
            Intent intent = new Intent(this, MessageListActivity.class);
            startActivity(intent);
        } else if (id == R.id.followee) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
