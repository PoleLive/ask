package cn.lawliex.ask.timeline;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.answer.detail.AnswerDetailActivity;
import cn.lawliex.ask.data.Feed;
import cn.lawliex.ask.question.detail.QuestionDetailActivity;

/**
 * Created by Terence on 2017/1/6.
 */

public class TimeLineFragment extends Fragment implements TimeLineContract.View {
    TimeLineContract.Presenter presenter;
    AppCompatActivity activity;
    ListView listView;
    List<Feed> feeds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);
        listView = (ListView)view.findViewById(R.id.frament_timeline_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Feed feed = feeds.get(position);
                Intent intent;
                if(feed.getEntityType() == 1){
                    intent = new Intent(getActivity(), QuestionDetailActivity.class);
                    intent.putExtra("questionId",feed.getInt("id"));
                    startActivity(intent);
                }else{
                    intent = new Intent(getActivity(), AnswerDetailActivity.class);
                    intent.putExtra("answerId",feed.getInt("id"));
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void showFeedsView(List<Feed> feeds) {
        this.feeds = feeds;
        TimeLineAdapter adapter = new TimeLineAdapter(this, feeds);
        listView.setAdapter(adapter);
    }

    @Override
    public void update() {
        presenter.start();
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg, Toast.LENGTH_LONG).show();
    }



    @Override
    public void setPresenter(TimeLineContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
