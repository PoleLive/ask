package cn.lawliex.ask.following;

import android.app.Activity;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.following.user.FollowUserContract;
import cn.lawliex.ask.following.user.FollowUserFragment;
import cn.lawliex.ask.following.user.FollowUserPresenter;
import cn.lawliex.ask.question.list.QuestionListFragment;

public class FollowingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        List<Fragment> fragments = new ArrayList<>();
        FollowUserFragment fragment = new FollowUserFragment();
        fragment.setActivity(this);

        FollowUserContract.Presenter presenter = new FollowUserPresenter(fragment);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.following_fragment, fragment).commit();

    }
}
