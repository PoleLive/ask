package cn.lawliex.ask.message.detail;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.message.list.MessageListAdapter;

public class MessageDetailActivity extends AppCompatActivity implements MessageDetailContract.View {

    EditText inputEdit;
    MessageDetailContract.Presenter presenter;
    ListView listView;
    List<Message> messages;
    Button sendBn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        MessageDetailContract.Presenter presenterx = new MessageDetailPresenter(this);
        listView = (ListView)findViewById(R.id.msg_detail_listview);
        sendBn = (Button)findViewById(R.id.msg_detail_bn_send);
        inputEdit = (EditText)findViewById(R.id.msg_detail_edit_input);
        sendBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputEdit.getText().toString().length() == 0)
                    return;
                presenter.sendMessage();
            }
        });
    }

    @Override
    public void showMessageDetail(List<Message> messageList) {
        messages = messageList;
        MessageDetailAdapter adapter = new MessageDetailAdapter(messageList,this);
        listView.setAdapter(adapter);
        listView.setSelection(messageList.size() - 1);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Message getMessageToSend() {
        Message message = new Message();
        message.setToId(getIntent().getIntExtra("toId",0));
        message.setContent(inputEdit.getText().toString());

        return message;
    }

    @Override
    public void setPresenter(MessageDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }
    @Override
    public void onPause(){
        super.onPause();

        presenter.onDestroy();

    }
    @Override
    public Activity getActivity() {
        return this;
    }
}
