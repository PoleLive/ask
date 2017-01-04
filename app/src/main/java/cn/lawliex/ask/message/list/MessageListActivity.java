package cn.lawliex.ask.message.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class MessageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        MessageListFragment fragment = (MessageListFragment) getFragmentManager().findFragmentById(R.id.msg_list_fragment);
        MessageListContract.Presenter presenter = new MessageListPresenter(fragment);
    }
}
