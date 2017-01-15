package cn.lawliex.ask.message.add;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.Message;
import cn.lawliex.ask.question.list.QuestionListActivity;

public class MessageAddActivity extends AppCompatActivity implements MessageAddContract.View {

    EditText editText;
    MessageAddContract.Presenter presenter;
    Button send;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_add);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("发送消息");
        final MessageAddContract.Presenter presenter = new MessageAddPresenter(this);
        editText = (EditText)findViewById(R.id.msg_add_edit);
        send = (Button)findViewById(R.id.msg_add_bn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length() != 0) {
                    presenter.start();
                    editText.setText("");
                }
            }
        });
    }

    @Override
    public Message getMessage() {
        Message message = new Message();
        message.setToId(getIntent().getIntExtra("toId", 0));
        message.setContent(editText.getText().toString());

        return message;
    }

    @Override
    public void toMessageDetail() {
        Intent intent = new Intent(this, QuestionListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String errMsg) {
        Toast.makeText(this,errMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(MessageAddContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
