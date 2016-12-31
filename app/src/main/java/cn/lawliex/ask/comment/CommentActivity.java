package cn.lawliex.ask.comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        CommentFragment fragment = (CommentFragment) getFragmentManager().findFragmentById(R.id.comment_fragment);
        CommentContract.Presenter presenter = new CommentPresenter(fragment);

    }
}
