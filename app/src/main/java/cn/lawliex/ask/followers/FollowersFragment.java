package cn.lawliex.ask.followers;

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
import cn.lawliex.ask.profile.other.detail.UserInfoDetailActivity;

/**
 * Created by Terence on 2017/1/3.
 */

public class FollowersFragment extends Fragment implements FollowersContract.View {
    FollowersContract.Presenter presenter;
    ListView listView;
    List<User> users;

    @Override
    public void showFollowers(List<User> users) {
        this.users = users;
        FollowersAdapter adapter = new FollowersAdapter(this,users);
        listView.setAdapter(adapter);
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
    public void setPresenter(FollowersContract.Presenter presenter) {
        this.presenter = presenter;
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
                Intent intent = new Intent(getActivity(), UserInfoDetailActivity.class);
                intent.putExtra("userId",users.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
