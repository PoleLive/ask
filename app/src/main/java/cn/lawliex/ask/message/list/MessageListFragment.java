package cn.lawliex.ask.message.list;

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
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.message.detail.MessageDetailActivity;

/**
 * Created by Terence on 2017/1/3.
 */

public class MessageListFragment extends Fragment implements MessageListContract.View {
    List<Message> messages;
    ListView listView;
    MessageListContract.Presenter presenter;

    @Override
    public void showMessagesView(List<Message> messageList) {
        this.messages = messageList;
        MessageListAdapter adapter = new MessageListAdapter(this,messages);
        listView.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(),errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void update() {
        presenter.start();
    }

    @Override
    public void setPresenter(MessageListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msg_list_fragment, container, false);
        listView = (ListView)view.findViewById(R.id.msg_list_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
                int userId = 0;
                Message m = messages.get(position);
                if(UserLocalDataSource.getInstance(getActivity()).getInt("id",0) == m.getFromId())
                    userId = m.getToId();
                else
                    userId = m.getFromId();
                intent.putExtra("toId",userId);
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
