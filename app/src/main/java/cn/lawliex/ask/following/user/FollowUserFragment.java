package cn.lawliex.ask.following.user;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.followers.FollowersAdapter;
import cn.lawliex.ask.followers.FollowersContract;
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowUserFragment extends Fragment implements FollowUserContract.View {
    FollowUserContract.Presenter presenter;
    ListView listView;
    List<User> users;
    Activity activity;
    @Override
    public void showFollowers(List<User> users) {
        this.users = users;
        FollowUserAdapter adapter = new FollowUserAdapter(this,users);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showErrorMessage("xxxx");
            }
        });
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }


    @Override
    public void update() {
        presenter.start();
    }

    @Override
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public Activity getAct() {
        return activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.followers_fragment, container, false);
        listView  = (ListView)view.findViewById(R.id.followers_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showErrorMessage("hahah");
                Intent intent = new Intent(getActivity(), UserInfoDetailActivity.class);
                intent.putExtra("userId",users.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setPresenter(FollowUserContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
